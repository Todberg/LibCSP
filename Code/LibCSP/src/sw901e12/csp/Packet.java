package sw901e12.csp;

public class Packet {
	
	public int header;
	public int data;
	
	public Packet(int header, int data) {
		this.header = header;
		this.data = data;
	}
	
	/* Header masks */
	public final static int MASK_CRC = 0x00000001;
	public final static int MASK_RDP = 0x00000002;
	public final static int MASK_XTEA = 0x0000004;
	public final static int MASK_HMAC = 0x00000008;
	public final static int MASK_RES = 0x000000F0;
	public final static int MASK_SPORT = 0x00003F00;
	public final static int MASK_DPORT = 0x000FC000;
	public final static int MASK_DEST = 0x01F00000;
	public final static int MASK_SRC = 0x3E000000;
	public final static int MASK_PRI = 0xC0000000;
	
	public int getCRC() {
		return (header & MASK_CRC);
	}
	
	public int getDEST() {
		return (header & MASK_DEST) >>> 20;
	}
}
