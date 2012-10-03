package sw901e12;

import sw901e12.comm.ModulePinger;

public class Module {
	private String name;
	private int addressOnBus;
	private ModulePinger modulePinger;
	
	private Module(String name, int addressOnBus, ModulePinger modulePinger) {
		this.setName(name);
		this.setAddressOnBus(addressOnBus);
		this.setModulePinger(modulePinger);
	}
	
	public static Module CreateWithNameAddressAndPinger(String name, int addressOnBus, ModulePinger modulePinger) {
		return new Module(name, addressOnBus, modulePinger);
	}
	
	public static Module ModuleWithAddressAndPinger(int addressOnBus, ModulePinger modulePinger) {
		return new Module("", addressOnBus, modulePinger);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
