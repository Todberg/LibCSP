package sw901e12.comm.modules;

import com.jopdesign.io.I2Cport;

import sw901e12.comm.ModulePinger;

public class TwoWayDirectionPinger extends ModulePinger {
	private int memoryAddressOnDeviceToRequest;
	
	public TwoWayDirectionPinger(int memoryAddressOnDeviceToRequest, I2Cport i2cPort) {
		super(i2cPort);
		this.memoryAddressOnDeviceToRequest = memoryAddressOnDeviceToRequest;
	}
	
	@Override
	public void Ping() {
		// write		
		TimeoutBasedWaitForModuleResponse();
		// read
	}
}
