package sw901e12.handlers;

import javax.realtime.AperiodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.AperiodicEventHandler;
import javax.safetycritical.StorageParameters;

public class APEHNoModuleResponseRoutine extends AperiodicEventHandler {

	public APEHNoModuleResponseRoutine(PriorityParameters priority,
			AperiodicParameters release, StorageParameters scp) {
		super(priority, release, scp);

	}

	@Override
	public void handleAsyncEvent() {
		System.out.println("APEH fired!!!");

	}

}
