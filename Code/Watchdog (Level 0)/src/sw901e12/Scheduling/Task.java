package sw901e12.Scheduling;

public class Task {
	
	private int scopeSize;
	private Runnable task;
	private String name;
	
	public Task(int scopeSize, String name, Runnable task) {
		this.scopeSize = scopeSize;
		this.name = name;
		this.task = task;
	}
	
	public int getScopeSize() {
		return scopeSize;
	}
	
	public Runnable getTask() {
		return task;
	}
	
	public String getName() {
		return name;
	}
}
