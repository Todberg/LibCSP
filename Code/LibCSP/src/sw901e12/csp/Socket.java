package sw901e12.csp;

import sw901e12.csp.util.ConnectionQueue;
import sw901e12.csp.util.IDispose;

public class Socket implements IDispose {
	public ConnectionQueue connections;
	
	public Socket(byte connectionsCapacity) {
		this.connections = new ConnectionQueue(connectionsCapacity);
	}

	@Override
	public void dispose() {
		this.connections.reset();
		this.connections = null;
		CSPManager.resourcePool.putSocket(this);
	}
}