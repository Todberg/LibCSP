package sw901e12.csp;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.RelativeTime;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.Phase;
import javax.safetycritical.annotate.SCJAllowed;
import javax.safetycritical.annotate.SCJRestricted;

import sw901e12.csp.handlers.RouteHandler;
import sw901e12.csp.interfaces.IMACProtocol;
import sw901e12.csp.interfaces.InterfaceLoopback;
import sw901e12.csp.util.Const;

public class CSPManager {
	
	public static byte nodeAddress;
	public static short outgoingPorts;
	public static ResourcePool resourcePool;
	public RouteHandler routeHandler;
	
	PeriodicParameters routeHandlerPeriodicParameters;
	StorageParameters routeHandlerStorageParameters;
	PriorityParameters routeHandlerPriorityParameters;
	
	/**
	 * Initializes CSP
	 * This method must be invoked as the first in the initialization phase
	 * @param nodeAddress The specified address of the host (must be in the range 0-30)
	 */
	@SCJAllowed(Level.LEVEL_1)
	@SCJRestricted(Phase.INITIALIZATION)
	public void init(byte nodeAddress) {
		CSPManager.nodeAddress = nodeAddress;
		CSPManager.outgoingPorts = 0;
		
		initializeRouteHandlerParameters();
		routeHandler = new RouteHandler(this.routeHandlerPriorityParameters,
			this.routeHandlerPeriodicParameters, 
			this.routeHandlerStorageParameters, 0);

		routeSet(nodeAddress, InterfaceLoopback.getInterface(), 0x0);
	}
	
	private void initializeRouteHandlerParameters() {
		final int ROUTE_HANDLER_BACKING_STORE_SIZE_IN_BYTES = 1024;
		final int ROUTE_HANDLER_SCOPE_SIZE_IN_BYTES = 512;
		final int ROUTE_HANDLER_RELEASE_PERIOD_IN_MS = 30;
		final int ROUTE_HANDLER_PRIORITY = 10;
		
		routeHandlerPriorityParameters = new PriorityParameters(ROUTE_HANDLER_PRIORITY);
		routeHandlerPeriodicParameters = new PeriodicParameters(new RelativeTime(0, 0), new RelativeTime(ROUTE_HANDLER_RELEASE_PERIOD_IN_MS, 0));
		routeHandlerStorageParameters = new StorageParameters(ROUTE_HANDLER_BACKING_STORE_SIZE_IN_BYTES, new long[] { ROUTE_HANDLER_SCOPE_SIZE_IN_BYTES }, 0, 0);
		
	}
	
	
	/**
	 * Registers the route handler as a periodic event handler with the scheduler.
	 * On transition to mission phase this will cause the route handler to execute as a periodic event handler
	 * concurrently with other event handlers.
	 */
	@SCJAllowed(Level.LEVEL_1)
	@SCJRestricted(Phase.INITIALIZATION)
	public void startRouteHandler() {
		routeHandler.register();
	}
	
	/**
	 * Initializes socket, connection and packet resources to default sizes.
	 * These sizes cannot be changed at run-time.
	 */
	@SCJAllowed(Level.LEVEL_1)
	@SCJRestricted(Phase.INITIALIZATION)
	public void initPools() {
		initPools(Const.DEFAULT_MAX_SOCKETS,
			Const.DEFAULT_MAX_CONNECTION_PER_SOCKET,
			Const.DEFAULT_MAX_CONNECTIONS,
			Const.DEFAULT_PACKET_QUEUE_SIZE_PER_CONNECTION,
			Const.DEFAULT_MAX_PACKETS);
	}
	
	/**
	 * Initializes socket, connection and packet resources to specified sizes.
	 * These sizes cannot be changed at run-time.
	 * @param socketsCapacity Maximum amount of sockets
	 * @param connectionsPerSocketCapacity Maximum amount of connections per socket (must be less than connectionsCapacity)
	 * @param connectionsCapacity Maximum amount of connections
	 * @param packetsPerConnectionCapacity Maximum amount of packets per connection (must be less than packetsCapacity)
	 * @param packetsCapacity Maximum amount of packets
	 */
	@SCJAllowed(Level.LEVEL_1)
	@SCJRestricted(Phase.INITIALIZATION)
	public void initPools(byte socketsCapacity,
			byte connectionsPerSocketCapacity,
			byte connectionsCapacity,
			byte packetsPerConnectionCapacity,
			byte packetsCapacity) {
		
		CSPManager.resourcePool = new ResourcePool(socketsCapacity, 
			connectionsPerSocketCapacity,
			connectionsCapacity, 
			packetsPerConnectionCapacity,
			packetsCapacity);
	}
	
	/**
	 * Specifies a route in the routing table for outgoing packets.
	 * Init must be invoked before setting routes.
	 * @param nodeAddress Destination address (must be in the range 0-30)
	 * @param protocol Outgoing interface protocol used for the next hop
	 * @param nextHopMacAddress Next hop mac address for the interface protocol
	 */
	@SCJAllowed(Level.LEVEL_1)
	@SCJRestricted(Phase.INITIALIZATION)
	public void routeSet(int nodeAddress, IMACProtocol protocol, int nextHopMacAddress) {		
		RouteHandler.routeTable[nodeAddress].nextHopMacAddress = (byte)nextHopMacAddress;
		RouteHandler.routeTable[nodeAddress].protocolInterface = protocol;
	}
	
	/**
	 * Creates and binds a socket to a specific port.
	 * @param port Port number to use
	 * @param options Socket options
	 * @return Newly created socket
	 */
	@SCJAllowed(Level.LEVEL_1)
	@SCJRestricted(Phase.ALL)
	public synchronized Socket createSocket(int port, Object options) {		
		Port p = RouteHandler.portTable[port];
		if(!p.isOpen) {
			Socket socket = resourcePool.getSocket(Const.TIMEOUT_SINGLE_ATTEMPT);
			if(socket != null) {
				p.isOpen = true;
				p.socket = socket;
				p.socket.port = (byte)port;
				return socket;
			}
		}
		return null;
	}
	
	/**
	 * Creates a connection to another network node. 
	 * This only prepares the connection - i.e no data is actually sent to the node.
	 * @param address Destination address (must in range 0-32)
	 * @param port Destination port (must be in range 0-47)
	 * @param timeout Maximum time in milliseconds to wait for an unused connection from the connection pool
	 * @param options Connection options
	 * @return Newly created connection
	 */
	@SCJAllowed(Level.LEVEL_1)
	@SCJRestricted(Phase.ALL)
	public Connection createConnection(int address, int port, int timeout, Object options) {
		Connection connection = resourcePool.getConnection(timeout);
		
		if(connection != null) {
			byte nodePort = findUnusedOutgoingPort();
			if(nodePort != -1) {
				nodePort += (Const.MAX_INCOMING_PORTS + 1);
				connection.setId(nodeAddress, nodePort, (byte)address, (byte)port);
				connection.isOpen = true;
				return connection;
			}
		}
		return null;
	}
	
	private synchronized byte findUnusedOutgoingPort() {
		short mask;
		for(short index = 0; index < 15; index++) {
			mask = (short) (1 << index);
			if((outgoingPorts & mask) == 0) {
				outgoingPorts |= mask;
				return (byte)index;
			}
		}
		return -1;
	}
	
	/**
	 * Provides a new empty packet that can be transmitted over an open connection.
	 * @return Empty packet
	 */
	@SCJAllowed(Level.LEVEL_1)
	@SCJRestricted(Phase.ALL)
	public Packet createPacket() {
		return CSPManager.resourcePool.getPacket(Const.TIMEOUT_SINGLE_ATTEMPT);
	}
}
