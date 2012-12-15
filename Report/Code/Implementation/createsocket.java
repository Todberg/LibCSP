/**
 * Creates and binds a socket to a specific port.
 * @param port Port number to use
 * @param options Socket options
 * @return Socket object bound to the port or null if none available
 */
@SCJAllowed(Level.LEVEL_1)
@SCJRestricted(Phase.ALL)
public synchronized Socket createSocket(int port, Object options) {		
	Port p = RouteHandler.portTable[port];
	if(!p.isOpen) {
		Socket socket = resourcePool.getSocket(CSPManager.TIMEOUT_SINGLE_ATTEMPT);
		if(socket != null) {
			p.isOpen = true;
			p.socket = (SocketCore) socket;
			p.socket.port = (byte)port;				
			return socket;
		}
	}
	return null;
}