package sw901e12.handlers;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;
import javax.safetycritical.io.SimplePrintStream;

import sw901e12.Module;
import sw901e12.comm.ModulePinger;

public class PEHModuleResponseChecker extends PeriodicEventHandler {

	private Module[] slaves;
	private SimplePrintStream console;
	private APEHModuleFailedRoutine noModuleResponseHandler;

	public PEHModuleResponseChecker(PriorityParameters priority,
			PeriodicParameters parameters, StorageParameters scp,
			long scopeSize, Module[] slaves, SimplePrintStream console,
			APEHModuleFailedRoutine noModuleResponseHandler) {
		super(priority, parameters, scp, scopeSize);

		this.slaves = slaves;
		this.console = console;
		this.noModuleResponseHandler = noModuleResponseHandler;
	}

	@Override
	@SCJAllowed(Level.SUPPORT)
	public void handleAsyncEvent() {
		console.println("Checking module responses");
		ModulePinger slaveModulePinger;

		for (Module slave : slaves) {
			slaveModulePinger = slave.getModulePinger();

			if (slaveModulePinger.didReceiveResponseFromModule())
				slaveModulePinger.resetDidReceiveResponseFlag();
			else
				noModuleResponseHandler.release();
		}
	}
}
