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

import com.jopdesign.sys.Const;
import com.jopdesign.sys.Native;

public class PEHModulePingerMeasure extends PEHModulePinger {

	public PEHModulePingerMeasure(PriorityParameters priority,
			PeriodicParameters parameters, StorageParameters scp,
			long scopeSize, SimplePrintStream console, Module[] slaves,
			CSPManager manager) {
		super(priority, parameters, scp, scopeSize, console, slaves, manager);
	}
	
}
/*
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
		timeUsBegin = Native.rdMem(Const.IO_US_CNT);
		timeUsEnd = Native.rdMem(Const.IO_US_CNT);
		timeUsForNativeReads = timeUsEnd - timeUsBegin;
	}
	
	@Override
	@SCJAllowed(Level.SUPPORT)
	public void handleAsyncEvent() {	
		timeUsBegin = Native.rdMem(Const.IO_US_CNT);
		super.handleAsyncEvent();
		timeUsEnd = Native.rdMem(Const.IO_US_CNT);
		
		timeUsResultForHandler = timeUsEnd - timeUsBegin - timeUsForNativeReads;
		timeUsResultForHandler = timeUsEnd - timeUsBegin;
		console.println("US: " + timeUsResultForHandler);
	}
}
*/