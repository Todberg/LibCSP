package com;
import com.jopdesign.io.IIC;
import com.jopdesign.io.IICFactory;
import com.jopdesign.sys.Const;
import com.jopdesign.sys.Native;
import javax.safetycritical.*;
import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.RelativeTime;


public class IICSafelet implements Safelet {
	@Override
	public MissionSequencer<Mission> getSequencer() {
		PriorityParameters pp = new PriorityParameters(11);
		StorageParameters sp = new StorageParameters(1000, null, 0, 0);
		return new BlinkerMissionSequencer(pp, sp);
	}

	@Override
	public long immortalMemorySize() {
		return 5000;
	}
}


class BlinkerMissionSequencer extends MissionSequencer<Mission> {
	BlinkerMissionSequencer(PriorityParameters pp, StorageParameters sp) {
		super(pp, sp);
	}

	@Override
	protected Mission getNextMission() {
		return new BlinkerMission();
	}
}

class BlinkerMission extends Mission {
	@Override
	protected void initialize() {
		initializePeriodicHandlerForTurningLEDsOn();
	}

	private void initializePeriodicHandlerForTurningLEDsOn() {
		final int handlerPriority = 11;
		final int handlerPeriodInMs = 1000;
		final int handlerTotalStorageInBytes = 50;
		final int handlerScopeStorageInBytes = 50;

		PeriodicEventHandler peh = new PeriodicEventHandler(
				new PriorityParameters(handlerPriority), new PeriodicParameters(
				new RelativeTime(0, 0), new RelativeTime(handlerPeriodInMs, 0)),
				new StorageParameters(handlerTotalStorageInBytes, new long[] { handlerScopeStorageInBytes } ), 0) {

			public void handleAsyncEvent() {
				IICFactory fact = IICFactory.getIICFactory();
				IIC iic = fact.getIIC();
				
				System.out.print("state");
				System.out.print(":");
				System.out.println(iic.TXR_RXR);
			}
		};

		peh.register();
	}

	@Override
	public long missionMemorySize() {
		return 1000;
	}

	@Override
	public Runnable start() {
		return null;
	}
}
