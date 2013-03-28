package sw901e12.handlers;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;
import javax.safetycritical.io.SimplePrintStream;

import sw901e12.Module;
import sw901e12.csp.CSPManager;
import sw901e12.csp.Connection;
import sw901e12.csp.Packet;
import sw901e12.sys.Config;

public class PEHModulePinger extends PeriodicEventHandler {

	protected Module[] slaves;
	protected SimplePrintStream console;
	
	private CSPManager manager;
	private Connection connection;
	private Packet packet;

	public PEHModulePinger(PriorityParameters priority,
			PeriodicParameters parameters, StorageParameters scp,
			long scopeSize, SimplePrintStream console, Module[] slaves, CSPManager manager) {
		
		super(priority, parameters, scp, scopeSize);

		this.console = console;
		this.slaves = slaves;
		this.manager = manager;
	}

	@Override
	@SCJAllowed(Level.SUPPORT)
	public void handleAsyncEvent() {
		if (Config.DEBUG) {
			console.println("PEHModulePinger");
		}
		
		for (Module slave : slaves) { //@WCA loop = 10
			connection = manager.createConnection(slave.getCSPAddress(), slave.getCSPPort(), CSPManager.TIMEOUT_SINGLE_ATTEMPT, null);
			if(connection != null) {
				packet = manager.createPacket();
				packet.setContent(42);
				connection.send(packet);
				Packet response = connection.read(10);
				slave.setResponse(response != null ? true : false);
				connection.close();
			}
		}
	}
}
