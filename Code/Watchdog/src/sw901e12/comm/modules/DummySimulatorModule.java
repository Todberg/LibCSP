package sw901e12.comm.modules;

import joprt.RtThread;
import sw901e12.comm.ModulePinger;

public class DummySimulatorModule extends ModulePinger {
	@Override
	public void Ping() {
		RtThread.sleepMs(5);
		this.receivedResponseOnLastPing = true;
	}

}
