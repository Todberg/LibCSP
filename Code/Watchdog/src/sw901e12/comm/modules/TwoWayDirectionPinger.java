package sw901e12.comm.modules;

import com.jopdesign.io.I2Cport;

import sw901e12.comm.ModulePinger;

public class TwoWayDirectionPinger extends ModulePinger {
	
	public TwoWayDirectionPinger(int memoryAddressOnDeviceToRequest, I2Cport i2cPort) {
		super(memoryAddressOnDeviceToRequest, i2cPort);
	}
	
	@Override
	public final void ping() {
		// write		
		timeoutBasedWaitForModuleResponse();
		// read
	}
}
