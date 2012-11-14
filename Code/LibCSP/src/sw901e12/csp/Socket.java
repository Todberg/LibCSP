package sw901e12.csp;

import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.Phase;
import javax.safetycritical.annotate.SCJAllowed;
import javax.safetycritical.annotate.SCJRestricted;

import sw901e12.csp.handlers.RouteHandler;
import sw901e12.csp.util.ConnectionQueue;
import sw901e12.csp.util.IDispose;

public class Socket implements IDispose {
	
	public byte port;
	public ConnectionQueue connections;
	
	public Socket(byte connectionsCapacity) {
		this.connections = new ConnectionQueue(connectionsCapacity);
	}

	/**
	 * Sets the socket in a state where it can receive new connections.
	 * When a new packet arrives for the port on which the socket listens a new connection is created.
	 * @param timeout Timeout in milliseconds to wait for new connection
	 * @return A new established connection upon receiving a new packet, or null on timeout
	 */
	@SCJAllowed(Level.LEVEL_1)
	@SCJRestricted(Phase.RUN)
	public Connection accept(int timeout) {
		return connections.dequeue(timeout);
	}
	
	/**
	 * Closes the socket an unbinds the used port. 
	 */
	@SCJAllowed(Level.LEVEL_1)
	@SCJRestricted(Phase.RUN)
	public synchronized void close() {
		if(RouteHandler.portTable[port].isOpen) {
			RouteHandler.portTable[port].isOpen = false;
			RouteHandler.portTable[port].socket = null;
			dispose();
		}	
	}
	
	@Override
	public void dispose() {
		this.port = 0;
		this.connections.reset();
		CSPManager.resourcePool.putSocket(this);
	}
}