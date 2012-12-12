package sw901e12.handlers;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;
import javax.safetycritical.io.SimplePrintStream;

import sw901e12.Module;
import sw901e12.WatchdogMission;
import sw901e12.sys.Config;

public class PEHModuleResponseChecker extends PeriodicEventHandler {

	protected SimplePrintStream console;
	protected Module[] slaves;
	protected WatchdogMission mission;

	public PEHModuleResponseChecker(PriorityParameters priority,
				PeriodicParameters parameters, StorageParameters scp,
				long scopeSize, SimplePrintStream console, Module[] slaves, 
				WatchdogMission mission) {
		
		super(priority, parameters, scp, scopeSize);

		this.console = console;
		this.slaves = slaves;
		this.mission = mission;
	}

	@Override
	@SCJAllowed(Level.SUPPORT)
	public void handleAsyncEvent() {
		if(Config.DEBUG) {
			console.println("PEHModuleResponseChecker");
		}
		
		for (Module slave : slaves) { // @WCA loop<=10
			if(slave.getResponse() == true) {
				slave.resetResponse();
			} else {
				mission.executeRecovery = true;
				break;
			}
		}
	}
}
