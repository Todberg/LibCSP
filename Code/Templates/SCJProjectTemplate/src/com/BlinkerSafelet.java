package com;
import com.jopdesign.sys.Const;
import com.jopdesign.sys.Native;
import javax.safetycritical.*;
import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.RelativeTime;


public class BlinkerSafelet implements Safelet {
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
		initializePeriodicHandlerForTurningLEDsOff();
	}

	private void initializePeriodicHandlerForTurningLEDsOn() {
		final int handlerPriority = 11;
		final int handlerPeriodInMs = 1000;
		final int handlerTotalStorageInBytes = 50;
		final int handlerScopeStorageInBytes = 50;
		final int integerRepresentingBitPatternForTurningLEDsOn = 1;

		PeriodicEventHandler peh = new PeriodicEventHandler(
				new PriorityParameters(handlerPriority), new PeriodicParameters(
				new RelativeTime(0, 0), new RelativeTime(handlerPeriodInMs, 0)),
				new StorageParameters(handlerTotalStorageInBytes, new long[] { handlerScopeStorageInBytes } ), 0) {

			public void handleAsyncEvent() {
				System.out.println("On");
				Native.wr(integerRepresentingBitPatternForTurningLEDsOn, Const.IO_WD);
			}
		};

		peh.register();
	}

	private void initializePeriodicHandlerForTurningLEDsOff() {
		final int handlerPriority = 9;
		final int handlerPeriodInMs = 1000;
		final int handlerInitialStartDelayInMs = 500;		
		final int handlerTotalStorageInBytes = 50;
		final int handlerScopeStorageInBytes = 50;
		final int integerRepresentingBitPatternForTurningLEDsOff = 0;

		PeriodicEventHandler peh = new PeriodicEventHandler(
				new PriorityParameters(handlerPriority), new PeriodicParameters(
				new RelativeTime(handlerInitialStartDelayInMs, 0), new RelativeTime(handlerPeriodInMs, 0)),
				new StorageParameters(handlerTotalStorageInBytes, new long[] { handlerScopeStorageInBytes } ), 0) {

			public void handleAsyncEvent() {
				System.out.println("Off");
				Native.wr(integerRepresentingBitPatternForTurningLEDsOff, Const.IO_WD);
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
