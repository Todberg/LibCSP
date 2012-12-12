package sw901e12.handlers;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.io.SimplePrintStream;

import sw901e12.IRecoveryRoutine;
import sw901e12.WatchdogMission;
import sw901e12.sys.Config;

public class PEHSystemRecovery extends PeriodicEventHandler {
	
	protected SimplePrintStream console;
	protected IRecoveryRoutine recovery;
	protected WatchdogMission mission;
	
	public PEHSystemRecovery(PriorityParameters priority,
			PeriodicParameters release, StorageParameters scp, 
			long scopeSize,  SimplePrintStream console, WatchdogMission mission, IRecoveryRoutine recovery) {
		
		super(priority, release, scp, scopeSize);
		
		this.console = console;
		this.mission = mission;
		this.recovery = recovery;
	}

	@Override
	public void handleAsyncEvent() {
		if(Config.DEBUG) {
			console.println("PEHSystemRecovery");
		}
		
		if(mission.executeRecovery) {
			if(Config.DEBUG) {
				console.println("Initiating system recovery...");
			}
			recovery.executeRecovery();
		}
	}
}
