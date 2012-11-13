package sw901e12.csp;

import sw901e12.csp.util.IDispose;
import sw901e12.csp.util.Queue;

public class Connection implements IDispose {
	
	/* Connection masks */
	public final static int MASK_SRC = 0x003E0000;
	public final static int MASK_SPORT = 0x0001F800;
	public final static int MASK_DST = 0x000007C0;
	public final static int MASK_DPORT = 0x0000003F;
	
	/*
	 * Connection identifyer
	 * Format: S000000000 | SRC:5 | SPORT:6 | DST:5 | DPORT:6 | 
	 */
	public int id;
	
	public boolean isOpen;
	public Queue<Packet> packets;
	
	public Connection(byte i) {
		this.packets = new Queue<Packet>(i);
	}

	@Override
	public void dispose() {
		this.id = 0;
		this.isOpen = false;
		this.packets.reset();
		this.packets = null;
		CSPManager.resourcePool.putConnection(this);
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
	
	public Packet read(int timeout) {
		Packet packet = packets.dequeue(timeout);
		if(packet != null) {
			Packet packetCopy = new Packet(packet.header, packet.data);
			CSPManager.resourcePool.putPacket(packet);
			return packetCopy;
		}
		return null;
	}
	
	public void close() {
		dispose();
	}
	
	public static int getConnectionIdFromPacketHeader(Packet packet) {
		int connectionId = 0;
	
		connectionId = (packet.getDST() << 17);
		connectionId |= (packet.getDPORT() << 11);
		connectionId |= (packet.getSRC() << 6);
		connectionId |= packet.getSPORT();
		 
		return connectionId;
	}
}