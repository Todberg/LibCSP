package sw901e12.csp.handlers;

import javax.realtime.AperiodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.AperiodicEventHandler;
import javax.safetycritical.StorageParameters;

import sw901e12.csp.interfaces.IMACProtocol;

public class ISRHandler extends AperiodicEventHandler {
	
	private IMACProtocol protocolInterface;

	public ISRHandler(PriorityParameters priority, AperiodicParameters release,
			StorageParameters scp, IMACProtocol protocolInterface) {
		super(priority, release, scp, 512);
		
		this.protocolInterface = protocolInterface;
		this.register();
	}
	
	@Override
	public void handleAsyncEvent() {
		protocolInterface.receiveFrame();
	}
}
