package sw901e12.csp.interfaces;

import sw901e12.csp.core.PacketCore;
import sw901e12.csp.handlers.RouteHandler;

public class InterfaceLoopback implements IMACProtocol {

	private static InterfaceLoopback instance;
	
	private InterfaceLoopback() { }
	
	public static InterfaceLoopback getInterface() {
		if(instance == null) {
			instance = new InterfaceLoopback();
		}
		
		return instance;
	}
	
	@Override
	public void initialize(int nodeAddress) { }

	@Override
	public void transmitPacket(PacketCore packet) {
		RouteHandler.packetsToBeProcessed.enqueue(packet);
	}

	@Override
	public void receiveFrame() { }
}