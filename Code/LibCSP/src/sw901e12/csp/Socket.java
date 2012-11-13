package sw901e12.csp;

import sw901e12.csp.handlers.RouteHandler;
import sw901e12.csp.util.ConnectionQueue;
import sw901e12.csp.util.IDispose;

public class Socket implements IDispose {
	
	public byte port;
	public ConnectionQueue connections;
	
	public Socket(byte connectionsCapacity) {
		this.connections = new ConnectionQueue(connectionsCapacity);
	}

	@Override
	public void dispose() {
		this.port = 0;
		this.connections.reset();
		this.connections = null;
		CSPManager.resourcePool.putSocket(this);
	}
	
	// Hmm..
	public Connection accept(int timeout) {
		Connection connection = CSPManager.resourcePool.getConnection(timeout);
		if(connection != null) {
			connections.enqueue(connection);
			return connection;
		}
		return null;
	}
	
	public void close() {
		RouteHandler.portTable[port].isOpen = false;
		dispose();
	}
}