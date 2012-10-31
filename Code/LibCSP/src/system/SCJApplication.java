package system;

public class SCJApplication {
	
	public static void main(String[] args) {
		int header = 0xFEAFFFFF;
		sw901e12.csp.Packet packet = new sw901e12.csp.Packet(header, 0);
		System.out.println("DEST : " + packet.getDEST());
	}
}
