package sw901e12.handlers;

import javax.realtime.AbsoluteTime;
import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;

import sw901e12.Module;

import com.jopdesign.io.I2CFactory;
import com.jopdesign.io.I2Cport;

public class WatchdogPeriodicEventHandler extends PeriodicEventHandler {

	private I2Cport i2cPort;
	private Module[] slaves;
	
	public WatchdogPeriodicEventHandler(PriorityParameters priority,
			PeriodicParameters parameters, StorageParameters scp, long scopeSize, Module[] slaves) {
		super(priority, parameters, scp, scopeSize);
		
		this.slaves = slaves;
		
		initialize();
	}

	private void initialize() {
		I2CFactory fact = I2CFactory.getFactory();
		i2cPort = fact.getI2CportA();
		i2cPort.initConf(0x0F); // 15
	}
	
	private void printBuffer(int[] buffer) {
		for(int i : buffer) {
			System.out.print((char)i);
		}
		System.out.println();
	}

	@Override
	@SCJAllowed(Level.SUPPORT)
	public void handleAsyncEvent() {
		System.out.println("New round");
		
		for(Module slave : slaves) {
			slave.getModulePinger().Ping();
			System.out.println("I did a ping!");
			

			
			System.out.println();
		}
	}
}
