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

import sw901e12.Scheduling.CyclicSchedule;
import sw901e12.comm.ModulePingerFactory;
import sw901e12.handlers.PEHModulePinger;
import sw901e12.handlers.PEHModuleResponseChecker;
import sw901e12.handlers.PEHSystemRecovery;
import sw901e12.handlers.Runner;

public class WatchdogMission extends Mission {
	
	private CyclicSchedule schedule;
	
	private SimplePrintStream console;
	private ModulePingerFactory modulePingerFactory;
	private Module[] slaves;
	
	public volatile boolean executeRecovery;

	@Override
	@SCJAllowed(Level.SUPPORT)
	public void initialize() {
		initializeConsole();
		initializeModulePingerFactory();
		initializeSlaves();

		initializeTasks();	
		initializeRunner();
	}
	
	private void initializeTasks() {
		Task[] tasks = new Task[3];
		tasks[0] = new Task(512, "Pinger", new PEHModulePinger(console, slaves));
		tasks[1] = new Task(512, "Checker",  new PEHModuleResponseChecker(console, slaves, this));
		tasks[2] = new Task(512, "Recovery", new PEHSystemRecovery(console, new RecoveryOptionOne(), this));
		
		RelativeTime duration = new RelativeTime(500, 0);
		CyclicSchedule.Frame[] frames = { new CyclicSchedule.Frame(duration, tasks) };
		schedule = new CyclicSchedule(frames);
	}
	
	private void initializeRunner() {
		final int RUNNER_PRIORITY = 20;
		final int RUNNER_BACKING_STORE_SIZE_IN_BYTES = 2048;
		final int RUNNER_SCOPE_SIZE_IN_BYTES = 1024;
		final int RUNNER_RELEASE_PERIOD_IN_MS = 500;
		
		PriorityParameters priorityParams = new PriorityParameters(RUNNER_PRIORITY);
		PeriodicParameters periodicParams = new PeriodicParameters(new RelativeTime(0, 0), new RelativeTime(RUNNER_RELEASE_PERIOD_IN_MS, 0));
		StorageParameters storageParams = new StorageParameters(RUNNER_BACKING_STORE_SIZE_IN_BYTES, new long[] { RUNNER_SCOPE_SIZE_IN_BYTES }, 0, 0);
		
		new Runner(priorityParams, periodicParams, storageParams, 0, schedule).register();
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
	
	@Override
	@SCJAllowed
	public long missionMemorySize() {
		return 100000;
	}
}
