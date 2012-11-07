package sw901e12.csp;

import sw901e12.csp.util.Queue;

public class Connection {
	public int id;
	public Queue<Packet> packets;
	
	public Connection(byte packetsCapacity) {
		this.packets = new Queue<Packet>(packetsCapacity);
	}
}