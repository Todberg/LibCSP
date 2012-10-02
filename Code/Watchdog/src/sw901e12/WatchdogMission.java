package sw901e12;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.RelativeTime;
import javax.safetycritical.Mission;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;

import sw901e12.handlers.WatchdogPeriodicEventHandler;

public class WatchdogMission extends Mission {

	@Override
	@SCJAllowed(Level.SUPPORT)
	protected void initialize() {
		PriorityParameters priorityParam = new PriorityParameters(10);
		PeriodicParameters releaseParam = new PeriodicParameters(new RelativeTime(0, 0), new RelativeTime(1500, 0));
		StorageParameters storageParam = new StorageParameters(10000, new long[] { 500 }, 0, 0);

		// Slave address of ultrasonic sensor
		int[] slaves = { 0x01 }; // 1 
		
		WatchdogPeriodicEventHandler WDHandler = new WatchdogPeriodicEventHandler(
				priorityParam,
				releaseParam, 
				storageParam, 
				0,
				slaves);
		
		WDHandler.register();	
	}

	@Override
	@SCJAllowed
	public long missionMemorySize() {
		return 100000;
	}

}
