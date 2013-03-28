package sw901e12.csp;

import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.Phase;
import javax.safetycritical.annotate.SCJAllowed;
import javax.safetycritical.annotate.SCJRestricted;

public interface Connection {
	
	/**
	 * Attempt to read any packet received in FIFO order.
	 * @param timeout Maximum time in milliseconds to wait for an unused packet from the packet pool 
	 * @return Next packet received on the connection or null if none
	 */
	@SCJAllowed(Level.LEVEL_1)
	public Packet read(int timeout);
	
	/**
	 * Sends a packet to the destination of the current connection.
	 * @param packet The packet with specified data to be sent
	 */
	@SCJAllowed(Level.LEVEL_1)
	public void send(Packet packet);
	
	/**
	 * Closes the connection if open
	 */
	@SCJAllowed(Level.LEVEL_1)
	public void close();
}
