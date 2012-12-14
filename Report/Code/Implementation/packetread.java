public PacketCore read(int timeout) {
	PacketCore packet = packets.dequeue(timeout);
	if(packet != null) {
		PacketCore packetCopy = new PacketCore(packet.header, packet.data);
		CSPManager.resourcePool.putPacket(packet);
		return packetCopy;
	}
	return null;
}