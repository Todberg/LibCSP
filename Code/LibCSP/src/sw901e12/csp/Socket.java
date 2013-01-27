package sw901e12.csp;

import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.Phase;
import javax.safetycritical.annotate.SCJAllowed;
import javax.safetycritical.annotate.SCJRestricted;


public interface Socket {
	
	/**
	 * Sets the socket in a state where it can receive new connections.
	 * When a new packet arrives for the port on which the socket listens a new connection is created.
	 * @param timeout Timeout in milliseconds to wait for new connection
	 * @return A new established connection upon receiving a new packet, or null on timeout
	 */
	@SCJAllowed(Level.LEVEL_1)
	public Connection accept(int timeout);
	
	/**
	 * Closes the socket an unbinds the used port. 
	 */
	@SCJAllowed(Level.LEVEL_1)
	public void close();
}
