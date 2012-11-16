package sw901e12.csp;

import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.Phase;
import javax.safetycritical.annotate.SCJAllowed;
import javax.safetycritical.annotate.SCJRestricted;

import sw901e12.csp.handlers.RouteHandler;
import sw901e12.csp.util.Const;
import sw901e12.csp.util.IDispose;
import sw901e12.csp.util.Queue;

public class Connection implements IDispose {
	
	/* Connection masks */
	public final static int MASK_SRC = 0x003E0000;
	public final static int MASK_SPORT = 0x0001F800;
	public final static int MASK_DST = 0x000007C0;
	public final static int MASK_DPORT = 0x0000003F;
	
	/*
	 * Connection identifier
	 * Format: S000000000 | SRC:5 | SPORT:6 | DST:5 | DPORT:6 | 
	 */
	public int id;
	
	public boolean isOpen;
	public Queue<Packet> packets;
	
	public Connection(byte i) {
		this.packets = new Queue<Packet>(i);
	}
	
	public void setId(byte SRC, byte SPORT, byte DST, byte DPORT) {
		setSRC(SRC);
		setSPORT(SPORT);
		setDST(DST);
		setDPORT(DPORT);
	}
	
	public byte getDPORT() {
		return (byte)(id & MASK_DPORT);
	}
	
	public byte getDST() {
		return (byte)((id & MASK_DST) >>> 6);
	}
	
	public byte getSPORT() {
		return (byte)((id & MASK_SPORT) >>> 11); 
	}
	
	public byte getSRC() {
		return (byte)((id & MASK_SRC) >>> 17);
	}
	
	public void setDPORT(byte DPORT) {
		 id &= ~(MASK_DPORT);
		 id |= (int)DPORT;
	}
	
	public void setDST(byte DST) {
		id &= ~(MASK_DST);
		id |=  ((int)DST << 6);
	}
	
	public void setSPORT(byte SPORT) {
		id &= ~(MASK_SPORT);
		id |= ((int)SPORT << 11);
	}
	
	public void setSRC(byte SRC) {
		id &= ~(MASK_SRC);
		id |= ((int)SRC << 17);
	}
	
	/**
	 * Attempt to read any packet received in FIFO order.
	 * @param timeout Maximum time in milliseconds to wait for an unused packet from the packet pool
	 * @return Next packet received on the connection or null if none
	 */
	@SCJAllowed(Level.LEVEL_1)
	@SCJRestricted(Phase.RUN)
	public Packet read(int timeout) {
		Packet packet = packets.dequeue(timeout);
		if(packet != null) {
			Packet packetCopy = new Packet(packet.header, packet.data);
			CSPManager.resourcePool.putPacket(packet);
			return packetCopy;
		}
		return null;
	}
	
	/**
	 * Sends a packet to the destination of the current connection.
	 * @param packet The packet with specified data to be sent
	 */
	@SCJAllowed(Level.LEVEL_1)
	@SCJRestricted(Phase.RUN)
	public void send(Packet packet) {
		packet.setSRC(CSPManager.nodeAddress);
		packet.setSPORT(getSPORT());
		packet.setDST(getDST());
		packet.setDPORT(getDPORT());
		
		if (RouteHandler.packetsToBeProcessed.count < RouteHandler.packetsToBeProcessed.capacity) {
			RouteHandler.packetsToBeProcessed.enqueue(packet);
		} else {
			CSPManager.resourcePool.packets.enqueue(packet);
		}
	}
	
	public synchronized void processPacket(Packet packet) {
		if (isOpen) {
			packets.enqueue(packet);
		} else {
			CSPManager.resourcePool.putPacket(packet);
		}
	}
	
	/**
	 * Closes the connection if open
	 */
	@SCJAllowed(Level.LEVEL_1)
	@SCJRestricted(Phase.RUN)
	public synchronized void close() {	
		if(isOpen) {
			byte SPORT = getSPORT();
			if(SPORT > Const.MAX_INCOMING_PORTS) {
				SPORT -= (Const.MAX_INCOMING_PORTS + 1);
				CSPManager.outgoingPorts &= ~(1 << SPORT);
			}
			dispose();
		}
	}
	
	/**
	 * Extracts the connection id (src, sport, dst, dport) from a packet header
	 */
	@SCJAllowed(Level.LEVEL_1)
	@SCJRestricted(Phase.RUN)
	public static int getConnectionIdFromPacketHeader(Packet packet) {
		int connectionId = 0;
	
		connectionId = (packet.getDST() << 17);
		connectionId |= (packet.getDPORT() << 11);
		connectionId |= (packet.getSRC() << 6);
		connectionId |= packet.getSPORT();
		 
		return connectionId;
	}
	
	@Override
	public void dispose() {
		this.isOpen = false;
		this.id = 0;
		this.packets.reset();
		CSPManager.resourcePool.putConnection(this);
	}
}