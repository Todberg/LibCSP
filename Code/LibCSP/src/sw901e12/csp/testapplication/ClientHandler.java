package sw901e12.csp.testapplication;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;

import sw901e12.csp.CSPManager;
import sw901e12.csp.Connection;
import sw901e12.csp.Packet;

public class ClientHandler extends PeriodicEventHandler {

	private CSPManager cspManager;
	
	public ClientHandler(PriorityParameters priority,
			PeriodicParameters parameters, StorageParameters scp, long scopeSize,
			 CSPManager manager) {
		super(priority, parameters, scp, scopeSize);
		
		this.cspManager = manager;
	}

	@Override
	@SCJAllowed(Level.SUPPORT)
	public void handleAsyncEvent() {
		System.out.println("Client released");
		
		char c = 'P';
		Connection conn = cspManager.createConnection(ClientServerMission.NODE_ADDRESS,
			12, CSPManager.TIMEOUT_NONE, null);
		
		if (conn != null) {	
			System.out.println("Got new connection, gonna send packet");
			Packet p = cspManager.createPacket();
			p.setContent((int)c);
				
			conn.send(p);
			conn.close();
		}
	}

}
