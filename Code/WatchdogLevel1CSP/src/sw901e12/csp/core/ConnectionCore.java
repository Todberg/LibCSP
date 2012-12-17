package sw901e12.csp.core;

import sw901e12.csp.CSPManager;
import sw901e12.csp.Connection;
import sw901e12.csp.Packet;
import sw901e12.csp.handlers.RouteHandler;
import sw901e12.csp.util.Const;
import sw901e12.csp.util.IDispose;
import sw901e12.csp.util.Queue;

public class ConnectionCore implements IDispose, Connection {
	
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
	public Queue<PacketCore> packets;
	
	public ConnectionCore(byte packetsCapacity) {
		this.packets = new Queue<PacketCore>(packetsCapacity);
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
	
	public PacketCore read(int timeout) {
		PacketCore packet = packets.dequeue(timeout);
		if(packet != null) {
			PacketCore packetCopy = new PacketCore(packet.header, packet.data);
			CSPManager.resourcePool.putPacket(packet);
			return packetCopy;
		}
		return null;
	}

	public void send(Packet packet) {
		PacketCore p = (PacketCore)packet;
		p.setSRC(CSPManager.nodeAddress);
		p.setSPORT(getSPORT());
		p.setDST(getDST());
		p.setDPORT(getDPORT());
		
		if (RouteHandler.packetsToBeProcessed.count < RouteHandler.packetsToBeProcessed.capacity) {
			RouteHandler.packetsToBeProcessed.enqueue(p);
		} else {
			CSPManager.resourcePool.packets.enqueue(p);
		}
	}
	
	public synchronized void processPacket(PacketCore packet) {
		if (isOpen && !packets.isFull()) {
			packets.enqueue(packet);
		} else {
			packet.dispose();
		}
	}
	
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
	
	public static int getConnectionIdFromPacketHeader(PacketCore packet) {
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