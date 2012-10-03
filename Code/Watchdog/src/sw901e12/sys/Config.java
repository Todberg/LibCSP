package sw901e12.sys;

import javax.realtime.RelativeTime;

public class Config {
	public static final RelativeTime WD_MODULE_RESPONSE_TIMEOUT
		= new RelativeTime(3000, 0);
	
	public static final int WD_MASTER_I2C_ADDRESS = 0x000F;
}
