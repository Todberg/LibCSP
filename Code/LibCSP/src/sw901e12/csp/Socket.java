package sw901e12.csp;

import sw901e12.csp.util.ConnectionQueue;

public class Socket {
	public ConnectionQueue connections;
	
	public Socket(byte connectionsCapacity) {
		this.connections = new ConnectionQueue(connectionsCapacity);
	}
}
