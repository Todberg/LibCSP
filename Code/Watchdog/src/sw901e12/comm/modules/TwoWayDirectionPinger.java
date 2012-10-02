package sw901e12.comm.modules;

import sw901e12.comm.ModulePinger;

public class TwoWayDirectionPinger extends ModulePinger {
	private int memoryAddressOnDeviceToRequest;
	
	public TwoWayDirectionPinger(int memoryAddressOnDeviceToRequest) {
		this.memoryAddressOnDeviceToRequest = memoryAddressOnDeviceToRequest;
	}
	
	@Override
	public void Ping() {
		// TODO Auto-generated method stub
		
	}

}
