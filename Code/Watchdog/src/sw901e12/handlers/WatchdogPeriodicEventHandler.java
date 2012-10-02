package sw901e12.handlers;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;

import joprt.RtThread;

import com.jopdesign.io.I2CFactory;
import com.jopdesign.io.I2Cport;

public class WatchdogPeriodicEventHandler extends PeriodicEventHandler {

	private I2Cport i2cPort;
	private int[] slaves;
	
	public WatchdogPeriodicEventHandler(PriorityParameters priority,
			PeriodicParameters parameters, StorageParameters scp, long scopeSize, int[] slaves) {
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
		System.out.println("entering handler");
		for(int slave : slaves) {
			i2cPort.masterTX();
			System.out.println("writing slave");
			i2cPort.write(slave, 0x08); // 0
			System.out.println("slave written");
			
			i2cPort.flushFifo();
			i2cPort.masterRX();

			System.out.println("reading buffer");
			int[] buffer = i2cPort.read(slave, 0x05); // Read 5 bytes into buffer
			System.out.println("buffer read");
			printBuffer(buffer);
						
		}
		System.out.println("exiting handler");
	}
}
