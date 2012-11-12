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
	
	public Socket GetSocket(byte port)
}
