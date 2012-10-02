package sw901e12.comm;

import sw901e12.comm.modules.OneWayDirectionPinger;
import sw901e12.comm.modules.TwoWayDirectionPinger;

public class BoardModulePingerFactory extends ModulePingerFactory {
	protected final int UltraSonicSensorI2CAddress = 0x01;
	protected final int UltraSonicSensorMemoryAddress = 0x00;
	
	@Override
	public ModulePinger CreateModulePingerOnI2CAddress(int moduleAddressOnBus) {
		if (moduleAddressOnBus == UltraSonicSensorI2CAddress) {
			return new TwoWayDirectionPinger(UltraSonicSensorMemoryAddress);
		}
		
		return null;
	}

}
