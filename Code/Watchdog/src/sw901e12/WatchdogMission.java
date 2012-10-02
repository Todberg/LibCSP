package sw901e12;

import java.io.IOException;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.realtime.AperiodicParameters;
import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.RelativeTime;
import javax.safetycritical.Mission;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;
import javax.safetycritical.io.SimplePrintStream;

import sw901e12.comm.ModulePingerFactory;
import sw901e12.handlers.APEHFailedModuleRoutine;
import sw901e12.handlers.PEHModulePinger;

public class WatchdogMission extends Mission {

	private ModulePingerFactory modulePingerFactory;
	private Module[] slaveModules;
	private SimplePrintStream console;
	
	private APEHFailedModuleRoutine moduleFailHandler;
	private PEHModulePinger modulePingHandler;	
	
	@Override
	@SCJAllowed(Level.SUPPORT)
	protected void initialize() {
	
		initializeModulePingerFactory();
		initializeSlaveModules();
        initializeConsole();
        
        initializePeriodicModulePingHandler();
        initializeAperiodicFailedModuleRoutineHandler();
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
	
	private void initializeConsole() {
		OutputStream os = null;
        try {
            os = Connector.openOutputStream("console:");
        } catch (IOException e) {
            throw new Error("No console available");
        }
        
        console = new SimplePrintStream(os);
	}

	private void initializePeriodicModulePingHandler() {
		PriorityParameters priorityParam = new PriorityParameters(10);
		PeriodicParameters releaseParam = new PeriodicParameters(new RelativeTime(0, 0), new RelativeTime(1500, 0));
		StorageParameters storageParam = new StorageParameters(10000, new long[] { 500 }, 0, 0);
		
		modulePingHandler = new PEHModulePinger(
				priorityParam,
				releaseParam, 
				storageParam, 
				0,
				slaveModules,
				console);
		
		modulePingHandler.register();	
	}
	
	private void initializeAperiodicFailedModuleRoutineHandler() {
		final int ModuleFailHandlerPriority = 14;
		
		
		PriorityParameters eh1_prio = new PriorityParameters(14);
		AperiodicParameters eh1_pparams = new AperiodicParameters(null, null);
		
		StorageParameters eh1_storage = new StorageParameters(1024, new long[] { 512 }, 0, 0);
		moduleFailHandler = new APEHFailedModuleRoutine(eh1_prio, eh1_pparams, eh1_storage);
		moduleFailHandler.register();
	}
	
	@Override
	@SCJAllowed
	public long missionMemorySize() {
		return 100000;
	}

}
