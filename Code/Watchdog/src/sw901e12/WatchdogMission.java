package sw901e12;

import java.io.IOException;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.RelativeTime;
import javax.safetycritical.Mission;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;
import javax.safetycritical.io.SimplePrintStream;

import sw901e12.comm.ModulePingerFactory;
import sw901e12.handlers.PEHModulePing;

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
        SimplePrintStream console = initializeConsole();
        
		PEHModulePing pingHandler = new PEHModulePing(
				priorityParam,
				releaseParam, 
				storageParam, 
				0,
				slaveModules,
				console);
		
		pingHandler.register();	
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
	
	private SimplePrintStream initializeConsole() {
		OutputStream os = null;
        try {
            os = Connector.openOutputStream("console:");
        } catch (IOException e) {
            throw new Error("No console available");
        }
        
        return new SimplePrintStream(os);
	}

	@Override
	@SCJAllowed
	public long missionMemorySize() {
		return 100000;
	}

}
