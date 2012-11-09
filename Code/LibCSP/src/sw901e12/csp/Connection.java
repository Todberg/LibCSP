package sw901e12.csp;

import sw901e12.csp.util.Queue;

public class Connection implements IDispose {
	public int id;
	public Queue<Packet> packets;
	
	public Connection(byte packetsCapacity) {
		this.packets = new Queue<Packet>(packetsCapacity);
	}

	@Override
	public void dispose() {
		this.id = 0;
		this.packets.reset();
		this.packets = null;
		CSPManager.resourcePool.putConnection(this);
	}
}