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
import sw901e12.csp.Socket;

public class ServerHandler extends PeriodicEventHandler {

	private CSPManager cspManager;
	private Socket socket;
	private Connection connection;
	
	public ServerHandler(PriorityParameters priority,
			PeriodicParameters parameters, StorageParameters scp, long scopeSize, CSPManager manager) {
		super(priority, parameters, scp, scopeSize);
		
		this.cspManager = manager;
		socket =  cspManager.createSocket(12, null);
	}

	@Override
	@SCJAllowed(Level.SUPPORT)
	public void handleAsyncEvent() {
		System.out.println("Server released");
		connection = socket.accept(200);

		if (connection != null) {
			Packet p = connection.read(100);
			System.out.println("S: received - " + (char)p.readContent());
			connection.close();
		}
	}
}
