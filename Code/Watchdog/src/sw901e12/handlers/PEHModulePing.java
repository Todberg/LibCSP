package sw901e12.handlers;

import javax.realtime.AbsoluteTime;
import javax.realtime.Clock;
import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.RealtimeClock;
import javax.realtime.RelativeTime;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;
import javax.safetycritical.io.SimplePrintStream;

import sw901e12.Module;
import sw901e12.sys.Config;

public class PEHModulePing extends PeriodicEventHandler {

	private Module[] slaves;
	private SimplePrintStream console;
	private Clock clock;
	
	public PEHModulePing(PriorityParameters priority,
			PeriodicParameters parameters, StorageParameters scp, long scopeSize, Module[] slaves, SimplePrintStream console) {
		super(priority, parameters, scp, scopeSize);
		
		this.slaves = slaves;
		this.console = console;
		
		clock = Clock.getRealtimeClock();
	}
	
	@Override
	@SCJAllowed(Level.SUPPORT)
	public void handleAsyncEvent() {
		console.println("Pinging modules");
		
		for(Module slave : slaves) {
			slave.getModulePinger().Ping();
			
			AbsoluteTime timeout = clock.getTime().add(Config.timeout);
			
			while(clock.getTime().compareTo(timeout) < 0 || true) {
				
			}
		}
	}
}
