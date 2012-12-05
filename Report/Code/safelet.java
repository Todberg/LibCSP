public class MoonRoverSafelet implements Safelet<Mission> {

	@Override
	@SCJAllowed(Level.SUPPORT)
	@SCJRestricted(phase = Phase.INITIALIZATION)
	public MissionSequencer getSequencer() {
		Mission[] missions = { new SolarPanelMission(), ... };

		return new RepeatingMissionSequencer(
	 	  new PriorityParameters(15),
          new StorageParameters(1000, null),
          missions);
	}

	@Override
	@SCJAllowed(Level.SUPPORT)
	public long immortalMemorySize() {
		return 100000;
	}
}
