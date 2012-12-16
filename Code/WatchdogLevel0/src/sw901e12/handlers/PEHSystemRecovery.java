package sw901e12.handlers;

import javax.safetycritical.io.SimplePrintStream;

import sw901e12.IRecoveryRoutine;
import sw901e12.WatchdogMission;
import sw901e12.sys.Config;

public class PEHSystemRecovery implements Runnable {
	
	protected SimplePrintStream console;
	protected IRecoveryRoutine recovery;
	protected WatchdogMission mission;
	
	public PEHSystemRecovery(SimplePrintStream console, IRecoveryRoutine recovery, WatchdogMission mission) {
		this.console = console;
		this.recovery = recovery;
		this.mission = mission;
	}

	@Override
	public void run() {
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
