package sw901e12.handlers;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;
import javax.safetycritical.io.SimplePrintStream;

import sw901e12.Module;
import sw901e12.Recovery;
import sw901e12.comm.ModulePinger;
import sw901e12.sys.Config;

public class PEHModuleResponseChecker extends PeriodicEventHandler {

	private SimplePrintStream console;
	private Module[] slaves;
	private Recovery recovery;

	public PEHModuleResponseChecker(PriorityParameters priority,
				PeriodicParameters parameters, StorageParameters scp,
				long scopeSize, SimplePrintStream console, Module[] slaves, 
				Recovery recovery) {
		
		super(priority, parameters, scp, scopeSize);

		this.console = console;
		this.slaves = slaves;
		this.recovery = recovery;
	}

	@Override
	@SCJAllowed(Level.SUPPORT)
	public void handleAsyncEvent() {
		if(Config.DEBUG) {
			console.println("PEHModuleResponseChecker");
		}
		
		ModulePinger slaveModulePinger;
		
		for (Module slave : slaves) { // @WCA loop<=10
			slaveModulePinger = slave.getModulePinger();
			if (slaveModulePinger.didReceiveResponseFromModule()) {
				slaveModulePinger.resetDidReceiveResponseFlag();
			} else {
				recovery.executeRecovery = true;
				break;
			}
		}
	}
}
