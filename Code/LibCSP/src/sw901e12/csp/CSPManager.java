package sw901e12.csp;

import sw901e12.csp.handlers.RouteHandler;
import sw901e12.csp.util.Const;

public class CSPManager {
	
	public static byte nodeAddress;
	public static ResourcePool resourcePool;
	
	public void init(byte nodeAddress) {
		this.nodeAddress = nodeAddress;		
	}
	
	public void startRouteHandler() {
		new RouteHandler(null,
			null, 
			null, 
			(Long) null, 
			CSPManager.resourcePool).register();
	}
	
	public void initPools() {
		initPools(Const.DEFAULT_MAX_SOCKETS,
			Const.DEFAULT_MAX_CONNECTION_PER_SOCKET,
			Const.DEFAULT_MAX_CONNECTIONS,
			Const.DEFAULT_PACKET_QUEUE_SIZE_PER_CONNECTION,
			Const.DEFAULT_MAX_PACKETS_STORED);
	}
	
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
	
	public Socket createSocket(int port, Object options) {
		if(port < 0 || port > (Const.MAX_SERVICE_PORTS + Const.MAX_BIND_PORTS)) {
			throw new IllegalArgumentException("Port must be in range 0-47");
		}
		
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
	
	public Connection createConnection(int address, int port, int timeout, Object options) {
		if(address < 0 || address > Const.MAX_NETWORK_HOSTS) {
			throw new IllegalArgumentException("Address must be in range 0-32");
		}
		if(port < 0 || port > (Const.MAX_SERVICE_PORTS + Const.MAX_BIND_PORTS)) {
			throw new IllegalArgumentException("Port must be in range 0-47");
		}
		
		Connection connection = resourcePool.getConnection(timeout);
		if(connection != null) {
			connection.setId(nodeAddress, (byte)0, (byte)address, (byte)port);
			connection.isOpen = true;
			return connection;
		}
		return null;
	}
}
