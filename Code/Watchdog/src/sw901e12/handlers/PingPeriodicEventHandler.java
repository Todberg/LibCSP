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

import sw901e12.Module;
import sw901e12.sys.Config;

import com.jopdesign.io.I2CFactory;
import com.jopdesign.io.I2Cport;

public class PingPeriodicEventHandler extends PeriodicEventHandler {

	private Module[] slaves;
	private Clock clock;
	
	public PingPeriodicEventHandler(PriorityParameters priority,
			PeriodicParameters parameters, StorageParameters scp, long scopeSize, Module[] slaves) {
		super(priority, parameters, scp, scopeSize);
		
		this.slaves = slaves;
		clock = Clock.getRealtimeClock();
	}
	
	@Override
	@SCJAllowed(Level.SUPPORT)
	public void handleAsyncEvent() {
		System.out.println("New round");
		
		for(Module slave : slaves) {
			
			AbsoluteTime currentTime = clock.getTime();
			AbsoluteTime deadline = currentTime.add(Config.timeout);
			
			//System.out.println(deadline.compareTo(clock.getTime()));
			
			slave.getModulePinger().Ping();
			System.out.println("I did a ping!");
		}
	}
}
