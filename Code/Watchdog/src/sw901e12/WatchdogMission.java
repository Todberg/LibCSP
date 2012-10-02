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
        
        initializeAperiodicFailedModuleRoutineHandler();
        initializePeriodicModulePingerHandler();
        initializePeriodicModuleResponseCheckHandler();
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

	private void initializePeriodicModulePingerHandler() {
		final int ModulePingHandlerPriority = 10;
		final int ModulePingHandlerBackingStoreSizeInBytes = 1024;
		final int ModulePingHandlerScopeSizeInBytes = 512;
		final int ModulePingHandlerReleasePeriodInMs = 1500;
		
		PriorityParameters modulePingPriority = new PriorityParameters(ModulePingHandlerPriority);
		PeriodicParameters modulePingParams = new PeriodicParameters(new RelativeTime(0, 0), new RelativeTime(ModulePingHandlerReleasePeriodInMs, 0));
		StorageParameters modulePingStorage = new StorageParameters(ModulePingHandlerBackingStoreSizeInBytes,
															   new long[] { ModulePingHandlerScopeSizeInBytes }, 0, 0);
		
		modulePingHandler = new PEHModulePinger(
				modulePingPriority,
				modulePingParams, 
				modulePingStorage, 
				0,
				slaveModules,
				console);
		
		modulePingHandler.register();	
	}
	
	private void initializeAperiodicFailedModuleRoutineHandler() {
		final int ModuleFailHandlerPriority = 14;
		final int ModuleFailHandlerBackingStoreSizeInBytes = 1024;
		final int ModuleFailHandlerScopeSizeInBytes = 512;
		
		PriorityParameters moduleFailPriority = new PriorityParameters(ModuleFailHandlerPriority);
		AperiodicParameters moduleFailParams = new AperiodicParameters(null, null);
		StorageParameters moduleFailStorage = new StorageParameters(ModuleFailHandlerBackingStoreSizeInBytes,
															  new long[] { ModuleFailHandlerScopeSizeInBytes }, 0, 0);
		
		moduleFailHandler = new APEHFailedModuleRoutine(moduleFailPriority, moduleFailParams, moduleFailStorage);
		moduleFailHandler.register();
	}
	
	private void initializePeriodicModuleResponseCheckHandler() {
	
	}
	
	@Override
	@SCJAllowed
	public long missionMemorySize() {
		return 100000;
	}

}
