package sw901e12.handlers;

import javax.safetycritical.io.SimplePrintStream;

import sw901e12.Module;
import sw901e12.WatchdogMission;
import sw901e12.comm.ModulePinger;
import sw901e12.sys.Config;

public class PEHModuleResponseChecker implements Runnable {

	protected SimplePrintStream console;
	protected Module[] slaves;
	protected WatchdogMission mission;
	
	public PEHModuleResponseChecker(SimplePrintStream console, Module[] slaves, WatchdogMission mission) {
		this.console = console;
		this.slaves = slaves;
		this.mission = mission;
	}
	
	@Override
	public void run() {
		if(Config.DEBUG) {
			console.println("PEHModuleResponseChecker");
		}
		
		ModulePinger slaveModulePinger;
		
		for (Module slave : slaves) { // @WCA loop<=10
			slaveModulePinger = slave.getModulePinger();
			if (slaveModulePinger.didReceiveResponseFromModule()) {
				slaveModulePinger.resetDidReceiveResponseFlag();
			} else {
				mission.executeRecovery = true;
				break;
			}
		}	
	}
}
