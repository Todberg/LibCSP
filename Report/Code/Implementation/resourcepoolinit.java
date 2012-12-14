private void initializePacketPool(byte packetsCapacity) {
	this.packets = new Queue<PacketCore>(packetsCapacity);
	
	PacketCore packet;
	for(byte i = 0; i < packetsCapacity; i++) {
		packet = new PacketCore(0, 0);
		packets.enqueue(packet);
	}
}