package sw901e12;

public class Module {
	private String name;
	private int MACAddress;
	private int CSPAddress;
	private int CSPPort;
	
	private boolean response;
	
	private Module(String name, int MACAddress, int CSPAddress, int CSPPort) {
		this.setName(name);
		this.setMACAddress(MACAddress);
		this.setCSPAddress(CSPAddress);
		this.setCSPPort(CSPPort);
		
		this.response = false;
	}
	
	public static Module create(String name, int MACAddress, int CSPAddress, int CSPPort) {
		return new Module(name, MACAddress, CSPAddress, CSPPort);
	}
	
	public static Module create(int MACAddress, int CSPAddress, int CSPPort) {
		return new Module("", MACAddress, CSPAddress, CSPPort);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getMACAddress() {
		return MACAddress;
	}

	public void setMACAddress(int MACAddress) {
		this.MACAddress = MACAddress;
	}
	
	public int getCSPAddress() {
		return CSPAddress;
	}
	
	public void setCSPAddress(int CSPAddress) {
		this.CSPAddress = CSPAddress;
	}
	
	public int getCSPPort() {
		return CSPPort;
	}

	public void setCSPPort(int CSPPort) {
		this.CSPPort = CSPPort;
	}
	
	public void setResponse(boolean response) {
		this.response = response;
	}
	
	public boolean getResponse() {
		return response;
	}
	
	public void resetResponse() {
		this.response = false;
	}
}
