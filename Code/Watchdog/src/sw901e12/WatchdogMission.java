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
import sw901e12.handlers.PEHSystemRecovery;
import sw901e12.handlers.PEHModulePinger;
import sw901e12.handlers.PEHModuleResponseChecker;
import sw901e12.sys.Config;
import sw901e12.wcet.PEHModulePingerMeasure;

public class WatchdogMission extends Mission {

	private SimplePrintStream console;
	private ModulePingerFactory modulePingerFactory;
	private Module[] slaves;
	
	private RecoveryOptionOne recovery;

	private PEHModulePinger modulePingerHandler;
	private PEHModuleResponseChecker moduleResponseCheckerHandler;
	private PEHSystemRecovery systemRecoveryHandler;

	@Override
	@SCJAllowed(Level.SUPPORT)
	public void initialize() {
		initializeConsole();
		initializeModulePingerFactory();
		initializeSlaves();
		
		recovery = new RecoveryOptionOne();
        
        initializePEHModulePinger();
        if(!Config.MEASURE_WCET) {
        	initializePEHModuleResponseChecker();
            initializePEHSystemRecovery(); 
        }      
	}
	
	@SCJAllowed(Level.SUPPORT)
	private void initializeConsole() {
		OutputStream os = null;
        try {
            os = Connector.openOutputStream("console:");
        } catch (IOException e) {
            throw new Error("No console available");
        }
        
        console = new SimplePrintStream(os);
	}
	
	@SCJAllowed(Level.SUPPORT)
	private void initializeModulePingerFactory() {
		modulePingerFactory = ModulePingerFactory.createEnvironmentSpecificModuleFactory();
	}
	
	@SCJAllowed(Level.SUPPORT)
	private void initializeSlaves() {
		slaves = new Module[1];
		slaves[0] = Module.createWithNameAddressAndPinger("Ultrasonic Sensor", 0x01, modulePingerFactory.createModulePingerOnI2CAddress(0x01));
	}
	
	private void initializePEHModulePinger() {
		final int PING_HANDLER_PRIORITY = 15;
		final int PING_HANDLER_BACKING_STORE_SIZE_IN_BYTES = 1024;
		final int PING_HANDLER_SCOPE_SIZE_IN_BYTES = 512;
		final int PING_HANDLER_RELEASE_PERIOD_IN_MS = 500;
		
		PriorityParameters priorityParams = new PriorityParameters(PING_HANDLER_PRIORITY);
		PeriodicParameters periodicParams = new PeriodicParameters(new RelativeTime(0, 0), new RelativeTime(PING_HANDLER_RELEASE_PERIOD_IN_MS, 0));
		StorageParameters storageParams = new StorageParameters(PING_HANDLER_BACKING_STORE_SIZE_IN_BYTES, new long[] { PING_HANDLER_SCOPE_SIZE_IN_BYTES }, 0, 0);
		
		if (Config.MEASURE_WCET) {
			modulePingerHandler = new PEHModulePingerMeasure(
					priorityParams,
					periodicParams, 
					storageParams, 
					0,
					console,
					slaves,
					modulePingerFactory);
		} else {
			modulePingerHandler = new PEHModulePinger(
				priorityParams,
				periodicParams, 
				storageParams, 
				0,
				console,
				slaves);
		}
		
		modulePingerHandler.register();	
	}
	
	private void initializePEHModuleResponseChecker() {
		final int RESPONSE_CHECK_HANDLER_PRIORITY = 10;
		final int RESPONSE_CHECK_HANDLER_BACKING_STORE_SIZE_IN_BYTES = 1024;
		final int RESPONSE_CHECK_HANDLER_SCOPE_SIZE_IN_BYTES = 512;
		final int RESPONSE_CHECK_HANDLER_RELEASE_PERIOD_IN_MS = 500;
		
		PriorityParameters priorityParams = new PriorityParameters(RESPONSE_CHECK_HANDLER_PRIORITY);
		PeriodicParameters periodicParams = new PeriodicParameters(new RelativeTime(0, 0), new RelativeTime(RESPONSE_CHECK_HANDLER_RELEASE_PERIOD_IN_MS, 0));
		StorageParameters storageParams = new StorageParameters(RESPONSE_CHECK_HANDLER_BACKING_STORE_SIZE_IN_BYTES, new long[] { RESPONSE_CHECK_HANDLER_SCOPE_SIZE_IN_BYTES }, 0, 0);
		
		moduleResponseCheckerHandler = new PEHModuleResponseChecker(
				priorityParams,
				periodicParams,
				storageParams,
				0,
				console,
				slaves,
				recovery);
		
		moduleResponseCheckerHandler.register();
	}
	
	private void initializePEHSystemRecovery() {
		final int FAILED_MODULE_HANDLER_PRIORITY = 5;
		final int FAILED_MODULE_HANDLER_BACKING_STORE_SIZE_IN_BYTES = 1024;
		final int FAILED_MODULE_HANDLER_SCOPE_SIZE_IN_BYTES = 512;
		final int FAILED_MODULE_HANDLER_RELEASE_PERIOD_IN_MS = 500;
		
		PriorityParameters priorityParams = new PriorityParameters(FAILED_MODULE_HANDLER_PRIORITY);
		PeriodicParameters periodicParams = new PeriodicParameters(new RelativeTime(0, 0), new RelativeTime(FAILED_MODULE_HANDLER_RELEASE_PERIOD_IN_MS, 0));
		StorageParameters storageParams = new StorageParameters(FAILED_MODULE_HANDLER_BACKING_STORE_SIZE_IN_BYTES,
															  new long[] { FAILED_MODULE_HANDLER_SCOPE_SIZE_IN_BYTES }, 0, 0);

		systemRecoveryHandler = new PEHSystemRecovery(
				priorityParams, 
				periodicParams, 
				storageParams, 
				0,
				console,
				recovery);
		
		systemRecoveryHandler.register();
	}
	
	@Override
	@SCJAllowed
	public long missionMemorySize() {
		return 100000;
	}
}
