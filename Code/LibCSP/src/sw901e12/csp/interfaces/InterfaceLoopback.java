package sw901e12.csp.interfaces;

import sw901e12.csp.Packet;

public class InterfaceLoopback implements IMACProtocol {

	@Override
	public void initialize(int nodeAddress) {		
	}

	@Override
	public void transmitPacket(Packet packet) {
	}

	@Override
	public void receiveFrame() {
	}
}
