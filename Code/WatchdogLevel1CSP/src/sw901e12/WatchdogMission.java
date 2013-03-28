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

import sw901e12.csp.CSPManager;
import sw901e12.csp.interfaces.IMACProtocol;
import sw901e12.handlers.PEHModulePinger;
import sw901e12.handlers.PEHModuleResponseChecker;
import sw901e12.handlers.PEHSystemRecovery;
import sw901e12.sys.Config;
import sw901e12.wcet.PEHModulePingerMeasure;

import com.jopdesign.sys.Const;
import com.jopdesign.sys.Native;

public class WatchdogMission extends Mission {

	private SimplePrintStream console;
	private Module[] slaves;
	
	public volatile boolean executeRecovery;
	
	private PEHModulePinger modulePingerHandler;
	private PEHModuleResponseChecker moduleResponseCheckerHandler;
	private PEHSystemRecovery systemRecoveryHandler;
	
	private CSPManager manager;

	@Override
	@SCJAllowed(Level.SUPPORT)
	public void initialize() {
		//super.peHandlerCount = 4;

		initializeConsole();
		initializeCSP();
		initializeSlaves();
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
	private void initializeSlaves() {
		slaves = new Module[2];
		
		if(isApplicationRunningInSimulator()) {
			{
				int MACAddress = Config.MAC_ADDRESS;
				int CSPAddress = Config.CSP_ADDRESS;
				int CSPPort = 13;
				slaves[0] = Module.create("Module 1", MACAddress, CSPAddress, CSPPort);
				IMACProtocol loopbackInterface = manager.getIMACProtocol(CSPManager.INTERFACE_LOOPBACK);
				loopbackInterface.initialize(MACAddress);
				manager.routeSet(CSPAddress, loopbackInterface, MACAddress);
			}
			{
				int MACAddress = Config.MAC_ADDRESS;
				int CSPAddress = Config.CSP_ADDRESS;
				int CSPPort = 14;
				slaves[1] = Module.create("Module 2", MACAddress, CSPAddress, CSPPort);
				IMACProtocol loopbackInterface = manager.getIMACProtocol(CSPManager.INTERFACE_LOOPBACK);
				loopbackInterface.initialize(MACAddress);
				manager.routeSet(CSPAddress, loopbackInterface, MACAddress);
			}
		} else {
			{ 
				int MACAddress = 0xB;
				int CSPAddress = 0xB;
				int CSPPort = 13;
				slaves[0] = Module.create("Module 1", MACAddress, CSPAddress, CSPPort);
				IMACProtocol I2CInterface = manager.getIMACProtocol(CSPManager.INTERFACE_I2C);
				I2CInterface.initialize(MACAddress);
				manager.routeSet(CSPAddress, I2CInterface, 0xFF); // last param is determined by topological network ordering (atm unknown)
			}
			{
				int MACAddress = 0xC;
				int CSPAddress = 0xC;
				int CSPPort = 14;
				slaves[1] = Module.create("Module 2", MACAddress, CSPAddress, CSPPort);
				IMACProtocol I2CInterface = manager.getIMACProtocol(CSPManager.INTERFACE_I2C);
				I2CInterface.initialize(MACAddress);
				manager.routeSet(CSPAddress, I2CInterface, 0xFF); // last param is determined by topological network ordering (atm unknown)
			}
		}
	}
	
	private final boolean isApplicationRunningInSimulator() {
		return (Native.rd(Const.IO_RAMCNT) == 0);
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
					manager);
		} else {
			modulePingerHandler = new PEHModulePinger(
				priorityParams,
				periodicParams, 
				storageParams, 
				0,
				console,
				slaves,
				manager);
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
				this);
		
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

		RecoveryOptionOne recovery = new RecoveryOptionOne();
		
		systemRecoveryHandler = new PEHSystemRecovery(
				priorityParams, 
				periodicParams, 
				storageParams, 
				0,
				console,
				this,
				recovery);

		systemRecoveryHandler.register();
	}
	
	private void initializeCSP() {
		manager = new CSPManager();
		
		manager.init(Config.CSP_ADDRESS, new PriorityParameters(20), 
				new PeriodicParameters(new RelativeTime(0, 0), new RelativeTime(5, 0)));
		manager.initPools();
		manager.startRouteHandler();
	}
	
	@Override
	@SCJAllowed
	public long missionMemorySize() {
		return 100000;
	}
}
