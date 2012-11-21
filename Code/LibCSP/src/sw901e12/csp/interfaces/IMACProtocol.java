package sw901e12.csp.interfaces;

import sw901e12.csp.core.PacketCore;

public interface IMACProtocol {
	
	public void initialize(int nodeAddress);
	
	public void transmitPacket(PacketCore packet);
	
	public void receiveFrame();
}
