public class SimpleMissionWithOnePeriodicHandler extends Mission {
	StringBuilder someExpandingString;

	@Override
	protected void initialize () {

		someExpandingString = new StringBuilder(3);

		PeriodicEventHandler peh = new PeriodicEventHandler(
			new PriorityParameters(11),
			new PeriodicParameters(new RelativeTime(0, 0), new RelativeTime(1000, 0)),
			new StorageParameters(10000, 1000, 1000)) {
	
		public void handleAsyncEvent() {
	 		someExpandingString.append('A');
		}

	};

	peh.register();
	}
}