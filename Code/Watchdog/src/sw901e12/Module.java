package sw901e12;

import sw901e12.comm.ModulePinger;

public class Module {
	private int addressOnBus;
	private ModulePinger modulePinger;
	
	private Module(int addressOnBus, ModulePinger modulePinger) {
		this.setAddressOnBus(addressOnBus);
		this.setModulePinger(modulePinger);
	}
	
	public static Module ModuleWithAddressAndPinger(int addressOnBus, ModulePinger modulePinger) {
		return new Module(addressOnBus, modulePinger);
	}

	public int getAddressOnBus() {
		return addressOnBus;
	}

	public void setAddressOnBus(int addressOnBus) {
		this.addressOnBus = addressOnBus;
	}

	public ModulePinger getModulePinger() {
		return modulePinger;
	}

	public void setModulePinger(ModulePinger modulePinger) {
		this.modulePinger = modulePinger;
	}
	
}
