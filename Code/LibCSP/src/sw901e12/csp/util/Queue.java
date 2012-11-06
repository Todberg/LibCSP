package sw901e12.csp.util;

public class Queue<T> {
	
	private byte capacity;
	
	public T[] elements;
	
	public Queue(byte capacity) {
		this.capacity = capacity;
	}
	
	public synchronized T dequeue() {
		return null;
	}
	
	public synchronized void enqueue(T element) {
		
	}
}
