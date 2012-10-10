package sw901e12.comm.modules;

import com.jopdesign.io.I2Cport;

import sw901e12.comm.ModulePinger;

public class DummySimulatorModule extends ModulePinger {

	public DummySimulatorModule(int memoryAddressOnDeviceToRequest, I2Cport i2cPort) {
		super(memoryAddressOnDeviceToRequest, i2cPort);
	}
	
	@Override
	public void ping() {
		timeoutBasedWaitForModuleResponse();
		this.receivedResponseOnLastPing = true;
	}
}