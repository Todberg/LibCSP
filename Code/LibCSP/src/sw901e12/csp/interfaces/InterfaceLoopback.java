package sw901e12.csp.interfaces;

import sw901e12.csp.Packet;
import sw901e12.csp.handlers.RouteHandler;

public class InterfaceLoopback implements IMACProtocol {

	@Override
	public void initialize(int nodeAddress) { }

	@Override
	public void transmitPacket(Packet packet) {
		RouteHandler.packetsToBeProcessed.enqueue(packet);
	}

	@Override
	public void receiveFrame() { }
}
