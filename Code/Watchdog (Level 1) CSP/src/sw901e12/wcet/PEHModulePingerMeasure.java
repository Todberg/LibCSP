package sw901e12.wcet;
import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;
import javax.safetycritical.io.SimplePrintStream;

import sw901e12.Module;
import sw901e12.csp.CSPManager;
import sw901e12.handlers.PEHModulePinger;

public class PEHModulePingerMeasure extends PEHModulePinger {

	private int timeUsBegin;
	private int timeUsEnd;
	private int timeUsForNativeReads;
	private int timeUsResultForHandler;
	
	public PEHModulePingerMeasure(PriorityParameters priority,
			PeriodicParameters parameters, StorageParameters scp,
			long scopeSize, SimplePrintStream console, Module[] slaves, CSPManager manager) {
		super(priority, parameters, scp, scopeSize, console, slaves, manager);
		
		initialize();
	}
	
	private void initialize() {
		/*
		modulePingerFactory = ModulePingerFactory.createEnvironmentSpecificModuleFactory();
		
		timeUsBegin = Native.rdMem(Const.IO_US_CNT);
		timeUsEnd = Native.rdMem(Const.IO_US_CNT);
		timeUsForNativeReads = timeUsEnd - timeUsBegin;
		
		slaves = new Module[10];
		slaves[0] = Module.createWithNameAddressAndPinger("Ultrasonic Sensor", 0x01, modulePingerFactory.createModulePingerOnI2CAddress(0x01));
		slaves[1] = Module.createWithNameAddressAndPinger("Ultrasonic Sensor", 0x01, modulePingerFactory.createModulePingerOnI2CAddress(0x01));
		slaves[2] = Module.createWithNameAddressAndPinger("Ultrasonic Sensor", 0x01, modulePingerFactory.createModulePingerOnI2CAddress(0x01));
		slaves[3] = Module.createWithNameAddressAndPinger("Ultrasonic Sensor", 0x01, modulePingerFactory.createModulePingerOnI2CAddress(0x01));
		slaves[4] = Module.createWithNameAddressAndPinger("Ultrasonic Sensor", 0x01, modulePingerFactory.createModulePingerOnI2CAddress(0x01));
		slaves[5] = Module.createWithNameAddressAndPinger("Ultrasonic Sensor", 0x01, modulePingerFactory.createModulePingerOnI2CAddress(0x01));
		slaves[6] = Module.createWithNameAddressAndPinger("Ultrasonic Sensor", 0x01, modulePingerFactory.createModulePingerOnI2CAddress(0x01));
		slaves[7] = Module.createWithNameAddressAndPinger("Ultrasonic Sensor", 0x01, modulePingerFactory.createModulePingerOnI2CAddress(0x01));
		slaves[8] = Module.createWithNameAddressAndPinger("Ultrasonic Sensor", 0x01, modulePingerFactory.createModulePingerOnI2CAddress(0x01));
		slaves[9] = Module.createWithNameAddressAndPinger("Ultrasonic Sensor", 0x01, modulePingerFactory.createModulePingerOnI2CAddress(0x01));
		*/
	}
	
	@Override
	@SCJAllowed(Level.SUPPORT)
	public void handleAsyncEvent() {
		/*
		timeUsBegin = Native.rdMem(Const.IO_US_CNT);
		super.handleAsyncEvent();
		timeUsEnd = Native.rdMem(Const.IO_US_CNT);
		
		timeUsResultForHandler = timeUsEnd - timeUsBegin - timeUsForNativeReads;
		timeUsResultForHandler = timeUsEnd - timeUsBegin;
		console.println("US: " + timeUsResultForHandler);
		*/
	}
}
