package sw901e12.sys;

import javax.realtime.RelativeTime;

public class Config {
	public static final RelativeTime WD_MODULE_RESPONSE_TIMEOUT = new RelativeTime(10, 0);
	
	public static final int WD_MASTER_I2C_ADDRESS = 0x000F;
	
	public static final boolean MEASURE_WCET = false;
	
	public static final boolean DEBUG = false;
}
