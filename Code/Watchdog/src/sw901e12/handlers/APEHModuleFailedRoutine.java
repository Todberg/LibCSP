package sw901e12.handlers;

import javax.realtime.AperiodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.AperiodicEventHandler;
import javax.safetycritical.StorageParameters;

import sw901e12.IFailedModuleRoutine;
import sw901e12.sys.Config;

public class APEHModuleFailedRoutine extends AperiodicEventHandler {
	IFailedModuleRoutine routineOnFailure;
	
	public APEHModuleFailedRoutine(PriorityParameters priority,
			AperiodicParameters release, StorageParameters scp, IFailedModuleRoutine failedModuleRoutine) {
		super(priority, release, scp);
		
		this.routineOnFailure = failedModuleRoutine;
	}

	@Override
	public void handleAsyncEvent() {
		if(Config.DEBUG) {
			System.out.println("Failed module");
		}
		
		routineOnFailure.executeFailedResponseRoutine();
	}
}
