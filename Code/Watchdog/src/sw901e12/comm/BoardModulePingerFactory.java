package sw901e12.comm;

import com.jopdesign.io.I2CFactory;
import com.jopdesign.io.I2Cport;

import sw901e12.comm.modules.TwoWayDirectionPinger;
import sw901e12.sys.Config;

public class BoardModulePingerFactory extends ModulePingerFactory {
	protected final int UltraSonicSensorI2CAddress = 0x01;
	protected final int UltraSonicSensorMemoryAddress = 0x00;
	protected final I2Cport i2cPort;
	
	public BoardModulePingerFactory() {
		this.i2cPort = I2CFactory.getFactory().getI2CportA();
		this.i2cPort.initConf(Config.WD_MASTER_I2C_ADDRESS);
	}
	
	@Override
	public ModulePinger CreateModulePingerOnI2CAddress(int moduleAddressOnBus) {
		if (moduleAddressOnBus == UltraSonicSensorI2CAddress) {
			return new TwoWayDirectionPinger(UltraSonicSensorMemoryAddress, i2cPort);
		}
		
		return null;
	}
}
