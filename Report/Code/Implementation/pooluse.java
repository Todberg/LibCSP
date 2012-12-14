public PacketCore getPacket(int timeout) {
	return packets.dequeue(timeout);
}

public void putPacket(PacketCore packet) {
	packets.enqueue(packet);
}