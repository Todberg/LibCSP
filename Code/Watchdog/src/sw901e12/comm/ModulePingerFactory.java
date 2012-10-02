package sw901e12.comm;

import com.jopdesign.sys.Const;
import com.jopdesign.sys.Native;

public abstract class ModulePingerFactory {
	
	public abstract ModulePinger CreateModulePingerOnI2CAddress(int moduleAddressOnBus);
	
	public final static ModulePingerFactory CreateEnvironmentSpecificModuleFactory() {
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
