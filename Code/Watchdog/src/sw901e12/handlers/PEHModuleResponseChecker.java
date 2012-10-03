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
	private APEHFailedModuleRoutine noModuleResponseHandler;

	public PEHModuleResponseChecker(PriorityParameters priority,
			PeriodicParameters parameters, StorageParameters scp,
			long scopeSize, Module[] slaves, SimplePrintStream console,
			APEHFailedModuleRoutine noModuleResponseHandler) {
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

			if (slaveModulePinger.DidReceiveResponseFromModule())
				slaveModulePinger.ResetDidReceiveResponseFlag();
			else
				noModuleResponseHandler.release();
		}
	}
}
