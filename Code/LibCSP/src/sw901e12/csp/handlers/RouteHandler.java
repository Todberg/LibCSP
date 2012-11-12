package sw901e12.csp.handlers;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;

import csp.CSPbuffer;

import sw901e12.csp.CSPManager;
import sw901e12.csp.Connection;
import sw901e12.csp.Node;
import sw901e12.csp.Packet;
import sw901e12.csp.Port;
import sw901e12.csp.ResourcePool;
import sw901e12.csp.Socket;
import sw901e12.csp.util.ConnectionQueue;
import sw901e12.csp.util.Const;
import sw901e12.csp.util.Queue;

public class RouteHandler extends PeriodicEventHandler {

	public static Node[] routeTable;
	public static Port[] portTable;
	public static Queue<Packet> packetsToBeProcessed;
	
	private ResourcePool resourcePool;
	
	public RouteHandler(PriorityParameters priority,
			PeriodicParameters parameters, StorageParameters scp, long scopeSize,
			ResourcePool resourcePool) {
		super(priority, parameters, scp, scopeSize);
		
		RouteHandler.routeTable = new Node[Const.MAX_NETWORK_HOSTS];
		this.portTable = new Port[Const.MAX_BIND_PORTS];
		this.resourcePool = resourcePool;
		
		initializeRouteTable();
		initializePortTable();
	}
	
	private void initializeRouteTable() {
		for(byte i = 0; i < Const.MAX_NETWORK_HOSTS; i++) {
			routeTable[i] = new Node();
		}
	}
	
	private void initializePortTable() {
		for(byte i = 0; i < Const.MAX_BIND_PORTS; i++) {
			portTable[i] = new Port();
		}
	}
	
	@Override
	@SCJAllowed(Level.SUPPORT)
	public void handleAsyncEvent() {		
		Packet packet = packetsToBeProcessed.dequeue(Const.TIMEOUT_SINGLE_ATTEMPT);
		
		if (packet != null) {
			byte packetDST = packet.getDST();
			
			if (packetDST == CSPManager.nodeAddress || packetDST == Const.BROADCAST_ADDRESS) {
				int connectionIdentifier = Connection.getConnectionIdFromPacketHeader(packet);
				
				Connection packetConnection = resourcePool.getGlobalConnection(connectionIdentifier);
				
				if (packetConnection == null) {
					Port packetDPORT = portTable[packet.getDPORT()];
					
					 if (!packetDPORT.isOpen) {
						 packetDPORT = portTable[Const.ANY_PORT];
					 }
					 
					 if (packetDPORT.isOpen) {
						Socket packetDstSocket = packetDPORT.socket;
						ConnectionQueue packetConnections = packetDstSocket.connections;
						
						packetConnection = resourcePool.getConnection(Const.TIMEOUT_SINGLE_ATTEMPT);
						// remember random port! 
						packetConnections.enqueue(packetConnection);
					 }
				}
				
				 if (packetConnection != null) {
					 packetConnection.packets.enqueue(packet);
				 } else {
					 resourcePool.putPacket(packet);
				 }
			} else {
				Node packetDstNode = routeTable[packetDST];
				packetDstNode.protocolInterface.transmitPacket(packet);
			}
		}
	}
}