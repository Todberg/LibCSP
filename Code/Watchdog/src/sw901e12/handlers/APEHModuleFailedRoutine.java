package sw901e12.handlers;

import javax.realtime.AperiodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.AperiodicEventHandler;
import javax.safetycritical.StorageParameters;

import sw901e12.sys.Config;

public class APEHModuleFailedRoutine extends AperiodicEventHandler {

	public APEHModuleFailedRoutine(PriorityParameters priority,
			AperiodicParameters release, StorageParameters scp) {
		super(priority, release, scp);
	}

	@Override
	public void handleAsyncEvent() {
		if(Config.DEBUG) {
			System.out.println("APEH fired!!!");
		}
		
		int x = 2 + 2;
	}
}
