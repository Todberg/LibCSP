package sw901e12.comm;

/*
 * Pinging each module via. I2C is done differently depending on the device
 * Therefore each module will have its own pinger that knows how to communicate with that particular device
 */
public abstract class ModulePinger {
	protected boolean receivedResponseOnLastPing;

	public ModulePinger() {
		this.receivedResponseOnLastPing = false;
	}
	
	public abstract void Ping();
	
	public void ResetDidReceiveResponseFlag() {
		receivedResponseOnLastPing = false;
	}
	public boolean DidReceiveResponseFromModule() {
		return receivedResponseOnLastPing;
	}
}
