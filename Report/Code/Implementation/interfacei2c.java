public class InterfaceI2C implements IMACProtocol {
	@Override
	public void initialize(int MACAddress) {	
		// JOP specific factory to get HW objects
		I2CPort = I2CFactory.getFactory().getI2CportA();
		I2CPort.initialize(MACAddress, true);
	}

	@Override
	public void transmitPacket(PacketCore packet) {
		// Make frame and send using HW object
		...
		CSPManager.resourcePool.putPacket(packet);
	}
	@Override
	public void receiveFrame() {		
		PacketCore packet = CSPManager.resourcePool.getPacket(CSPManager.TIMEOUT_SINGLE_ATTEMPT);
		// Get frame using HW object and extract packet
		..
		RouteHandler.packetsToBeProcessed.enqueue(packet);		
	}
}
