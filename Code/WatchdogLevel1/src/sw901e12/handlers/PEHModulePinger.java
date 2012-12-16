package sw901e12.handlers;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;
import javax.safetycritical.io.SimplePrintStream;

import sw901e12.Module;
import sw901e12.sys.Config;

public class PEHModulePinger extends PeriodicEventHandler {

	protected Module[] slaves;
	protected SimplePrintStream console;

	public PEHModulePinger(PriorityParameters priority,
			PeriodicParameters parameters, StorageParameters scp,
			long scopeSize, SimplePrintStream console, Module[] slaves) {
		
		super(priority, parameters, scp, scopeSize);

		this.console = console;
		this.slaves = slaves;
	}

	@Override
	@SCJAllowed(Level.SUPPORT)
	public void handleAsyncEvent() {
		if (Config.DEBUG) {
			console.println("PEHModulePinger");
		}
		for (Module slave : slaves) {
			slave.getModulePinger().ping();
		}
	}
}
