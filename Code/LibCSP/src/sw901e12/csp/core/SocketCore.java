package sw901e12.csp.core;

import sw901e12.csp.CSPManager;
import sw901e12.csp.handlers.RouteHandler;
import sw901e12.csp.util.ConnectionQueue;
import sw901e12.csp.util.IDispose;

public class SocketCore implements IDispose, sw901e12.csp.Socket {
	
	public byte port;
	public ConnectionQueue connections;
	
	public SocketCore(byte connectionsCapacity) {
		this.connections = new ConnectionQueue(connectionsCapacity);
	}

	public ConnectionCore accept(int timeout) {
		return connections.dequeue(timeout);
	}
	
	public synchronized void processConnection(ConnectionCore connection) {
		if(port != -1) {
			connections.enqueue(connection);
		}
	}
	
	public synchronized void close() {
		if(RouteHandler.portTable[port].isOpen) {
			RouteHandler.portTable[port].isOpen = false;
			RouteHandler.portTable[port].socket = null;
			dispose();
		}	
	}
	
	@Override
	public void dispose() {
		this.port = -1;
		this.connections.reset();
		CSPManager.resourcePool.putSocket(this);
	}
}