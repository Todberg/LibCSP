package sw901e12.comm;

import javax.realtime.AbsoluteTime;
import javax.realtime.Clock;

import com.jopdesign.io.I2Cport;

import sw901e12.sys.Config;

/*
 * Pinging each module via. I2C is done differently depending on the device
 * Therefore each module will have its own pinger that knows how to communicate with that particular device
 */
public abstract class ModulePinger {
	protected boolean receivedResponseOnLastPing;
	protected Clock clock;
	protected I2Cport i2cPort;
	protected int memoryAddressOnDeviceToRequest;
	
	public ModulePinger(int memoryAddressOnDeviceToRequest, I2Cport i2cPort) {
		this.memoryAddressOnDeviceToRequest = memoryAddressOnDeviceToRequest;
		this.i2cPort = i2cPort;
		this.receivedResponseOnLastPing = false;
		this.clock = Clock.getRealtimeClock();
	}
	
	private boolean IsTimeout(AbsoluteTime timeout) {		
		return (clock.getTime().compareTo(timeout) < 0 ? false : true);
	}
	
	private boolean IsDataAvailable() {
		return ((i2cPort.status & I2Cport.DATA_VALID) == 1);
	}
	
	protected final void TimeoutBasedWaitForModuleResponse() {
		AbsoluteTime timeout = clock.getTime().add(Config.WD_MODULE_RESPONSE_TIMEOUT);
		
		while(!IsTimeout(timeout)) {
			if(IsDataAvailable())
				break;
		}
	}
	
	public abstract void Ping();
	
	public final void ResetDidReceiveResponseFlag() {
		receivedResponseOnLastPing = false;
	}
	
	public final boolean DidReceiveResponseFromModule() {
		return receivedResponseOnLastPing;
	}
}
