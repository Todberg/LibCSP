package sw901e12.csp.interfaces;

import sw901e12.csp.Packet;

public interface IMACProtocol {
	
	public void initialize(int nodeAddress);
	
	public void transmitPacket(Packet packet);
	
	public void receiveFrame();
}
