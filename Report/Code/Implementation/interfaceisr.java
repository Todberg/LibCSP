public class ISRHandler extends AperiodicEventHandler {
	
	private IMACProtocol protocolInterface;

	public ISRHandler(PriorityParameters priority, AperiodicParameters release,
			StorageParameters scp, IMACProtocol protocolInterface) {
		super(priority, release, scp);
		
		this.protocolInterface = protocolInterface;
		this.register();
	}
	
	@Override
	public void handleAsyncEvent() {
		protocolInterface.receiveFrame();
	}
}
