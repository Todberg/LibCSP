package sw901e12.sys;

import javax.realtime.RelativeTime;

public class Config {
	
	// Timeout
	public static final RelativeTime timeout = new RelativeTime(3000, 0);
	
	// I2C address of master
	public static final int MasterI2CAddress = 0x000F;
	
	// Type of recovery
	
}
