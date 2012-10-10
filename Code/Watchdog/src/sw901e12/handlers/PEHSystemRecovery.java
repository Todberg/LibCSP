package sw901e12.handlers;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.io.SimplePrintStream;

import sw901e12.Recovery;
import sw901e12.sys.Config;

public class PEHSystemRecovery extends PeriodicEventHandler {
	
	private SimplePrintStream console;
	private Recovery recovery;
	
	public PEHSystemRecovery(PriorityParameters priority,
			PeriodicParameters release, StorageParameters scp, 
			long scopeSize,  SimplePrintStream console, Recovery recovery) {
		
		super(priority, release, scp, scopeSize);
		
		this.console = console;
		this.recovery = recovery;
	}

	@Override
	public void handleAsyncEvent() {
		if(Config.DEBUG) {
			console.println("PEHSystemRecovery");
		}
		
		if(recovery.executeRecovery) {
			if(Config.DEBUG) {
				console.println("Initiating system recovery...");
			}
			recovery.executeRecovery();
		}
	}
}
