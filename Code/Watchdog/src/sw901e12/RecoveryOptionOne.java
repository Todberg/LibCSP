package sw901e12;

import com.jopdesign.io.LedSwitch;
import com.jopdesign.io.LedSwitchFactory;
import com.jopdesign.io.SysDevice;

public class RecoveryOptionOne implements IRecoveryRoutine {

	private LedSwitch ledSwitch;
	private SysDevice sysDevice;
	
	public RecoveryOptionOne() {
		LedSwitchFactory ledSwitchfactory = LedSwitchFactory.getLedSwitchFactory();
		ledSwitch = ledSwitchfactory.getLedSwitch();
		sysDevice = ledSwitchfactory.getSysDevice();		
	}
	
	@Override
	public void executeRecovery() {
		
		// Simulate recovery computations
		ledSwitch.ledSwitch = Integer.MAX_VALUE;
		for(int i = 0; i < 10000000; i++);
		ledSwitch.ledSwitch = 0;
		sysDevice.wd = Integer.MAX_VALUE;
		for(int i = 0; i < 10000000; i++);
		sysDevice.wd = 0;
	}
}
