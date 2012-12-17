package sw901e12.wcet;
import javax.safetycritical.io.SimplePrintStream;

import sw901e12.Module;
import sw901e12.comm.ModulePingerFactory;
import sw901e12.handlers.PEHModulePinger;

import com.jopdesign.sys.Const;
import com.jopdesign.sys.Native;

public class PEHModulePingerMeasure extends PEHModulePinger {

	protected int timeUsBegin;
	protected int timeUsEnd;
	protected int timeUsForNativeReads;
	protected int timeUsResultForHandler;
	
	protected ModulePingerFactory modulePingerFactory;
	
	public PEHModulePingerMeasure(SimplePrintStream console, Module[] slaves, ModulePingerFactory modulePingerFactory) {
		super(console, slaves);
		this.modulePingerFactory = modulePingerFactory;
		
		initialize();
	}
	
	private void initialize() {
		
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
	}

	@Override
	public void run() {
		timeUsBegin = Native.rdMem(Const.IO_US_CNT);
		super.run();
		timeUsEnd = Native.rdMem(Const.IO_US_CNT);
		
		timeUsResultForHandler = timeUsEnd - timeUsBegin - timeUsForNativeReads;
		timeUsResultForHandler = timeUsEnd - timeUsBegin;
		console.println("US: " + timeUsResultForHandler);
	}
}
