public class InterfaceI2C implements IMACProtocol {
	@Override
	public void initialize(int MACAddress) {	
		I2CPort = I2CFactory.getFactory().getI2CportA();
		I2CPort.initialize(MACAddress, true);
	}

	@Override
	public void transmitPacket(PacketCore packet) {
		..
		CSPManager.resourcePool.putPacket(packet);
	}
	@Override
	public void receiveFrame() {		
		PacketCore packet = CSPManager.resourcePool.getPacket(CSPManager.TIMEOUT_SINGLE_ATTEMPT);
		..
		RouteHandler.packetsToBeProcessed.enqueue(packet);		
	}
}
