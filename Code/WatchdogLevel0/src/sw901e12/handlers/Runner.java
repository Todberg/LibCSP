package sw901e12.handlers;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;

import com.jopdesign.sys.Memory;

import sw901e12.Scheduling.CyclicSchedule;
import sw901e12.Scheduling.Task;

public class Runner extends PeriodicEventHandler {

	private CyclicSchedule schedule;
	
	public Runner(PriorityParameters priority, PeriodicParameters parameters,
			StorageParameters scp, long scopeSize, CyclicSchedule schedule) {
		super(priority, parameters, scp, scopeSize);
		
		this.schedule = schedule;
	}

	@Override
	@SCJAllowed(Level.SUPPORT)
	public void handleAsyncEvent() {
		for(int i = 0; i < schedule.frames.length; i++) {
			CyclicSchedule.Frame frame = schedule.frames[i];
			Task[] tasks = frame.getTaskSet();
			for(int j = 0; j < tasks.length; j++) {
				Task task = tasks[j];
				Memory.getCurrentMemory().enterPrivateMemory(task.getScopeSize(), task.getTask());
			}
		}
	}
}
