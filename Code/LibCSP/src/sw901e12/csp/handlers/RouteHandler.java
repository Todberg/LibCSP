package sw901e12.csp.handlers;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;

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
	public Port[] portTable;
	public static Queue<Packet> packetsToBeProcessed;
	
	private ResourcePool resourcePool;
	
	public RouteHandler(PriorityParameters priority,
			PeriodicParameters parameters, StorageParameters scp, long scopeSize,
			ResourcePool resourcePool) {
		super(priority, parameters, scp, scopeSize);
		
		RouteHandler.routeTable = new Node[Const.MAX_NETWORK_HOSTS];
		this.portTable = new Port[Const.MAX_PORTS_PER_HOST];
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
		for(byte i = 0; i < Const.MAX_PORTS_PER_HOST; i++) {
			portTable[i] = new Port();
		}
	}
	
	@Override
	@SCJAllowed(Level.SUPPORT)
	public void handleAsyncEvent() {
		
		Packet packet = packetsToBeProcessed.dequeue(Const.TIMEOUT_SINGLE_ATTEMPT);
		if(packet != null) {
			byte packetDST = packet.getDST(); 
			if(packetDST == CSPManager.nodeAddress || packetDST == Const.BROADCAST_ADDRESS) {
				Port packetDPORT = portTable[packet.getDPORT()];
				if(packetDPORT.isOpen) {
					Socket packetDstSocket = packetDPORT.socket;
					ConnectionQueue packetConnections = packetDstSocket.connections;
					Connection packetConnection = packetConnections.getConnection(10); // TODO "id"!
					
					if(packetConnection == null) {
						packetConnection = resourcePool.getConnection();
						packetConnection.id = 10; // TODO "id"!
						packetConnections.enqueue(packetConnection);
					}
					packetConnection.packets.enqueue(packet);
					
				} else {
					
				}
			} else {
				Node packetDstNode = routeTable[packetDST];
				packetDstNode.protocolInterface.transmitPacket(packet);
			}
		}
		
		/*
		 *  pakke = dequeue packetsToBeProcessed
		 *  hvis pakke er til mig (eller broadcast):
		 * 		Led i portTable efter matchende port --> socket (opret hvis CLOSED) --> connection:
		 * 			Hvis connection == OPEN:
		 * 				put pakke i conn-packet-queue
		 * 			Hvis connection == CLOSED:
		 * 				Open connection og put pakke i conn-pakcet-queue:
		 * 			Hvis der ikke er connection:
		 * 				Dequeue ny connection fra ConnectionPool og put pakke i conn-packet-queue
		 * 		Hvis port lukket => led efter catch all (ANY)
		 *  ellers:
		 *  	Kig i routeTable efter index == dst i header
		 *  	Den node vi får ud transmit pakke via nexthopaddr
		 * 
		 */
	}
}