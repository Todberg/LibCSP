package sw901e12.comm;

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
