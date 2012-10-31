package sw901e12.csp;

/*
 * The CSP header and data are stored in a 32 bit integer each
 * This is to reduce the memory footprint as opposed to storing each
 * element of a CSP packet in a separate data type
 */
public class Packet {
	
	/* Header masks */
	public final static int MASK_CRC = 0x00000001;
	public final static int MASK_RDP = 0x00000002;
	public final static int MASK_XTEA = 0x0000004;
	public final static int MASK_HMAC = 0x00000008;
	public final static int MASK_RES = 0x000000F0;
	public final static int MASK_SPORT = 0x00003F00;
	public final static int MASK_DPORT = 0x000FC000;
	public final static int MASK_DST = 0x01F00000;
	public final static int MASK_SRC = 0x3E000000;
	public final static int MASK_PRI = 0xC0000000;
	
	public int header;
	
	public int data;
	
	public Packet(int header, int data) {
		this.header = header;
		this.data = data;
	}
	
	public byte getCRC() {
		return (byte)(header & MASK_CRC);
	}
	
	public byte getRDP() {
		return (byte)((header & MASK_RDP) >>> 1);
	}
	
	public byte getXTEA() {
		return (byte)((header & MASK_XTEA) >>> 2);
	}
	
	public byte getHMAC() {
		return (byte)((header & MASK_HMAC) >>> 3);
	}
	
	public byte getRES() {
		return (byte)((header & MASK_RES) >>> 4);
	}
	
	public byte getSPORT() {
		return (byte)((header & MASK_SPORT) >>> 8);
	}
	
	public byte getDPORT() {
		return (byte)((header & MASK_DPORT) >>> 14);
	}
	
	public byte getDST() {
		return (byte)((header & MASK_DST) >>> 20);
	}
	
	public byte getSRC() {
		return (byte)((header & MASK_SRC) >>> 25);
	}
	
	public byte getPRI() {
		return (byte)((header & MASK_PRI) >>> 30);
	}
}
