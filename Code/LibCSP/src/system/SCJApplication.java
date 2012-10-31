package system;

import sw901e12.csp.Packet;

public class SCJApplication {
	
	public static void main(String[] args) {
		int header = 0xF422C600;
		Packet packet = new Packet(header, 0);
		
		System.out.println("CRC : " + packet.getCRC());
		System.out.println("RDP : " + packet.getRDP());
		System.out.println("XTEA : " + packet.getXTEA());
		System.out.println("HMAC : " + packet.getHMAC());
		System.out.println("RES : " + packet.getRES());
		System.out.println("SPORT : " + packet.getSPORT());
		System.out.println("DPORT : " + packet.getDPORT());
		System.out.println("SRC : " + packet.getSRC());
		System.out.println("DST : " + packet.getDST());
		System.out.println("PRI : " + packet.getPRI());
	}
}
