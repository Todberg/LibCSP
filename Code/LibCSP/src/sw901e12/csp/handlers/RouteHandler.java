package sw901e12.csp.handlers;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;

import sw901e12.csp.Node;
import sw901e12.csp.Packet;
import sw901e12.csp.Port;
import sw901e12.csp.util.HashMap;
import sw901e12.csp.util.Queue;

public class RouteHandler extends PeriodicEventHandler {

	public HashMap<Node> routeTable;
	
	public Port[] portTable;
	
	public Queue<Packet> packetsToBeProcessed;
		
	public RouteHandler(PriorityParameters priority,
			PeriodicParameters parameters, StorageParameters scp, long scopeSize) {
		super(priority, parameters, scp, scopeSize);
	}

	@Override
	@SCJAllowed(Level.SUPPORT)
	public void handleAsyncEvent() {
		
	}
}
