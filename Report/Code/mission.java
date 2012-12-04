import javax.safetycritical.Mission;

public class SolarPanelMission extends Mission {

	@Override
	@SCJAllowed(Level.SUPPORT)
	public void initialize() {
		...
	}
	
	@Override
	@SCJAllowed
	public long missionMemorySize() {
		return 10000;
	}
}
