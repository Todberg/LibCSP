package sw901e12.comm;

import com.jopdesign.io.I2Cport;

/*
 * Pinging each module via. I2C is done differently depending on the device.
 * Therefore each module will have its own pinger that knows how to communicate with that particular device.
 */
public abstract class ModulePinger {
	
	protected volatile boolean receivedResponseOnLastPing;
	
	protected int memoryAddressOnDeviceToRequest;
	protected I2Cport i2cPort;
	private long timeout;
	
	public ModulePinger(int memoryAddressOnDeviceToRequest, I2Cport i2cPort) {
		this.memoryAddressOnDeviceToRequest = memoryAddressOnDeviceToRequest;
		this.i2cPort = i2cPort;
		this.receivedResponseOnLastPing = false;
	}
	
	private boolean isDataAvailable() {
		if(i2cPort != null) {
			return ((i2cPort.status & I2Cport.DATA_VALID) == 1);
		}
		
		return false;
	}
	
	protected final void timeoutBasedWaitForModuleResponse() {
		timeout = System.currentTimeMillis() + 10;
		
		while(System.currentTimeMillis() < timeout) {
			if(isDataAvailable())
				break;
		}
	}
	
	public abstract void ping();
	
	public final void resetDidReceiveResponseFlag() {
		receivedResponseOnLastPing = false;
	}
	
	public final boolean didReceiveResponseFromModule() {
		return receivedResponseOnLastPing;
	}
}
