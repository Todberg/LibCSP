package sw901e12.wcet;
import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;
import javax.safetycritical.io.SimplePrintStream;

import sw901e12.Module;
import sw901e12.comm.ModulePingerFactory;
import sw901e12.handlers.PEHModulePinger;

import com.jopdesign.sys.Const;
import com.jopdesign.sys.Native;


public class PEHModulePingerMeasure extends PEHModulePinger {

	private int instructionCountBegin;
	private int instructionCountEnd;
	private int instructionCountForNativeReads;
	private int instructionCountResultForHandler;
	
	private ModulePingerFactory modulePingerFactory;
	
	public PEHModulePingerMeasure(PriorityParameters priority,
			PeriodicParameters parameters, StorageParameters scp,
			long scopeSize, Module[] slaves, SimplePrintStream console, ModulePingerFactory modulePingerFactory) {
		super(priority, parameters, scp, scopeSize, slaves, console);
		
		this.modulePingerFactory = modulePingerFactory;
		initMeasureTest();
	}
	
	private void initMeasureTest() {
		
		modulePingerFactory = ModulePingerFactory.createEnvironmentSpecificModuleFactory();
		
		instructionCountBegin = Native.rdMem(Const.IO_CNT);
		instructionCountEnd = Native.rdMem(Const.IO_CNT);
		instructionCountForNativeReads = instructionCountEnd - instructionCountBegin;
		
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
		
	}
	
	@Override
	@SCJAllowed(Level.SUPPORT)
	public void handleAsyncEvent() {
		instructionCountBegin = Native.rdMem(Const.IO_CNT);
		super.handleAsyncEvent();
		instructionCountEnd = Native.rdMem(Const.IO_CNT);
		instructionCountResultForHandler = instructionCountEnd - instructionCountBegin - instructionCountForNativeReads;
		
		System.out.println("Result: " + instructionCountResultForHandler);
	}

}
