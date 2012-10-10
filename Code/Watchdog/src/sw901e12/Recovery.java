package sw901e12;

public abstract class Recovery {
	
	public volatile boolean executeRecovery;
	
	public Recovery() {
		this.executeRecovery = false;
	}
	
	public abstract void executeRecovery();
}
