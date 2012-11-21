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
import sw901e12.csp.util.Const;
import sw901e12.csp.util.Queue;

public class RouteHandler extends PeriodicEventHandler {

	public static Node[] routeTable;
	public static Port[] portTable;
	public static Queue<Packet> packetsToBeProcessed;

	public RouteHandler(PriorityParameters priority,
			PeriodicParameters parameters, StorageParameters scp,
			long scopeSize) {
		super(priority, parameters, scp, scopeSize);

		RouteHandler.routeTable = new Node[Const.MAX_NETWORK_HOSTS];
		RouteHandler.portTable = new Port[Const.MAX_INCOMING_PORTS];
		
		RouteHandler.packetsToBeProcessed = new Queue<Packet>(Const.DEFAULT_PACKET_QUEUE_SIZE_ROUTING);

		initializeRouteTable();
		initializePortTable();
	}

	private void initializeRouteTable() {
		for (byte i = 0; i < Const.MAX_NETWORK_HOSTS; i++) {
			routeTable[i] = new Node();
		}
	}

	private void initializePortTable() {
		for (byte i = 0; i < Const.MAX_INCOMING_PORTS; i++) {
			portTable[i] = new Port();
		}
	}

	@Override
	@SCJAllowed(Level.SUPPORT)
	public void handleAsyncEvent() {		
		Packet packet = packetsToBeProcessed.dequeue(CSPManager.TIMEOUT_SINGLE_ATTEMPT);
		
		if (packet != null) {
			byte packetDST = packet.getDST();
			
			/* The packet is for me */
			if (packetDST == CSPManager.nodeAddress || packetDST == CSPManager.ADDRESS_BROADCAST) {
				
				/* Check for an existing connection that should receive the packet */
				int connectionIdentifier = Connection.getConnectionIdFromPacketHeader(packet);
				Connection packetConnection = CSPManager.resourcePool.getGlobalConnection(connectionIdentifier);
				
				/* If its the first packet with no existing connection (Server) */
				if (packetConnection == null) {
					
					/* Extract the port from the packet header */
					Port packetDPORT = portTable[packet.getDPORT()];
					if (!packetDPORT.isOpen) {
						packetDPORT = portTable[CSPManager.PORT_ANY];
					}
					
					/* If a socket listens on the port (Server) */
					if (packetDPORT.isOpen) {						
						packetConnection = CSPManager.resourcePool.getConnection(CSPManager.TIMEOUT_SINGLE_ATTEMPT);
						if (packetConnection != null) {
							/* 
							 * New connection established - Set connection id (reverse src, dst and sport, dport) 
							 * and enqueue connection in the sockets connection queue 
							 */
							try {
								packetConnection.setId(packet.getDST(), packet.getDPORT(), packet.getSRC(), packet.getSPORT());
								packetConnection.isOpen = true;
								packetDPORT.socket.processConnection(packetConnection);
							} catch(NullPointerException e) {
								packetConnection.dispose();
								packetConnection = null;
							}
						}
					}
				}
				
				/* Check if we have a connection - then deliver or drop the packet */
				if (packetConnection != null) {
					packetConnection.processPacket(packet);
				} else {
					packet.dispose();
				}
			} else { /* The packet is not for me - send it to the destination node through the correct interface */
				Node packetDstNode = routeTable[packetDST];
				packetDstNode.protocolInterface.transmitPacket(packet);
			}
		}
	}
}