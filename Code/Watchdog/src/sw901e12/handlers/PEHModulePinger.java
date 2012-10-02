package sw901e12.handlers;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;
import javax.safetycritical.io.SimplePrintStream;

import sw901e12.Module;

public class PEHModulePinger extends PeriodicEventHandler {

	private Module[] slaves;
	private SimplePrintStream console;
	
	public PEHModulePinger(PriorityParameters priority,
			PeriodicParameters parameters, StorageParameters scp, long scopeSize, 
				Module[] slaves, SimplePrintStream console) {
		super(priority, parameters, scp, scopeSize);
		
		this.slaves = slaves;
		this.console = console;
	}
	
	@Override
	@SCJAllowed(Level.SUPPORT)
	public void handleAsyncEvent() {
		console.println("Pinging modules");
		
		for(Module slave : slaves)
			slave.getModulePinger().Ping();
	}
}
