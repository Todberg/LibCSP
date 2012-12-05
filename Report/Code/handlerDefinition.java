public class PEHAlignmentHandler extends PeriodicEventHandler {

	public PEHAlignmentHandler(
	  PriorityParameters priority,
	  PeriodicParameters release, 
	  StorageParameters storage,
	  long scopeSize) {
        super(priority, release, storage, scopeSize);
		...
	}

	@Override
	@SCJAllowed(Level.SUPPORT)
	public void handleAsyncEvent() {
		...
	}
}