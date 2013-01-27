package sw901e12.csp;

import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;

public interface Packet {
	
	/**
	 * Sets the payload of the packet.
	 * @param data Payload data
	 */
	@SCJAllowed(Level.LEVEL_1)
	public void setContent(int data);
	
	/**
	 * Gets the payload of the packet.
	 * @return Payload data
	 */
	@SCJAllowed(Level.LEVEL_1)
	public int readContent();
}
