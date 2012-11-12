package sw901e12.csp;

import sw901e12.csp.handlers.RouteHandler;
import sw901e12.csp.util.ConnectionQueue;
import sw901e12.csp.util.IDispose;

public class Socket implements IDispose {
	
	public byte attachedPort;
	public ConnectionQueue connections;
	
	public Socket(byte connectionsCapacity) {
		this.connections = new ConnectionQueue(connectionsCapacity);
	}

	@Override
	public void dispose() {
		this.attachedPort = 0;
		this.connections.reset();
		this.connections = null;
		CSPManager.resourcePool.putSocket(this);
	}
	
	public static int getConnectionIdFromPacketHeader(Packet packet) {
		return 0;
	}

	public Connection accept(int timeout) {
		return CSPManager.resourcePool.getConnection(timeout);
	}
	
	public void close() {
		RouteHandler.portTable[attachedPort].isOpen = false;
		dispose();
	}
}