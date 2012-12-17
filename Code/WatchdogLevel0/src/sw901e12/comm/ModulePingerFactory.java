package sw901e12.comm;

import com.jopdesign.sys.Const;
import com.jopdesign.sys.Native;

/* 
 * All pinger objects are created through a factory that determines the correct pinger for a given address on the bus
 * An Abstract Factory is implemented here to provide a factory on run time depending on whether the application runs
 * in the simulator or on actual hardware. 
 */
public abstract class ModulePingerFactory {
	
	public abstract ModulePinger createModulePingerOnI2CAddress(int moduleAddressOnBus);
	
	public final static ModulePingerFactory createEnvironmentSpecificModuleFactory() {
		if (isApplicationRunningInSimulator()) {
			return new SimulatorModulePingerFactory();
		} else {
			return new BoardModulePingerFactory();
		}
	}
	
	private final static boolean isApplicationRunningInSimulator() {
		return (Native.rd(Const.IO_RAMCNT) == 0);
	}
}
