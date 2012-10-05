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
			long scopeSize, Module[] slaves, SimplePrintStream console) {
		super(priority, parameters, scp, scopeSize);

		this.slaves = slaves;
		this.console = console;
	}

	@Override
	@SCJAllowed(Level.SUPPORT)
	public void handleAsyncEvent() {
		if (Config.DEBUG) {
			console.println("Pinging modules");
		}

		for (Module slave : slaves) { // @WCA loop<=10
			slave.getModulePinger().ping();
		}
	}
}
