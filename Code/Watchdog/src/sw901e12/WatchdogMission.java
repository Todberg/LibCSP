package sw901e12;

import java.util.Vector;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.RelativeTime;
import javax.safetycritical.Mission;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;

import sw901e12.comm.ModulePingerFactory;
import sw901e12.handlers.WatchdogPeriodicEventHandler;

public class WatchdogMission extends Mission {

	ModulePingerFactory modulePingerFactory;
	Module[] slaveModules;
	
	@Override
	@SCJAllowed(Level.SUPPORT)
	protected void initialize() {
		PriorityParameters priorityParam = new PriorityParameters(10);
		PeriodicParameters releaseParam = new PeriodicParameters(new RelativeTime(0, 0), new RelativeTime(1500, 0));
		StorageParameters storageParam = new StorageParameters(10000, new long[] { 500 }, 0, 0);
		
		initializeModulePingerFactory();
		initializeSlaveModules();
		
		WatchdogPeriodicEventHandler WDHandler = new WatchdogPeriodicEventHandler(
				priorityParam,
				releaseParam, 
				storageParam, 
				0,
				slaveModules);
		
		WDHandler.register();	
	}
	
	@SCJAllowed(Level.SUPPORT)
	protected void initializeModulePingerFactory() {
		this.modulePingerFactory = ModulePingerFactory.CreateEnvironmentSpecificModuleFactory();
	}
	
	@SCJAllowed(Level.SUPPORT)
	protected void initializeSlaveModules() {
		slaveModules = new Module[1];
		
		slaveModules[0] = Module.ModuleWithAddressAndPinger(0x01, modulePingerFactory.CreateModulePingerOnI2CAddress(0x01));
	}

	@Override
	@SCJAllowed
	public long missionMemorySize() {
		return 100000;
	}

}
