package sw901e12.comm;

import sw901e12.comm.modules.DummySimulatorModule;

public class SimulatorModulePingerFactory extends ModulePingerFactory {

	@Override
	public ModulePinger CreateModulePingerOnI2CAddress(int moduleAddressOnBus) {
		return new DummySimulatorModule(0, null);
	}
}
