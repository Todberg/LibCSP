/*
package sw901e12.csp.test;

import junit.framework.Assert;

import org.junit.Test;

import sw901e12.csp.util.Queue;

public class QueueTest extends Queue<String> {
	
	public Queue<String> queue;
	
	public QueueTest() {
		super((byte)10);
	}
	
	@Test
	public void testEnqueue() {
		enqueue("First");
		enqueue("Second");
		Assert.assertEquals(2, count);
		Assert.assertEquals("First", start.value);
		Assert.assertEquals("Second", head.next.value);
	}
	
	@Test
	public void testDequeue() {
		enqueue("First");
		enqueue("Second");
		enqueue("Third");
		
		dequeue(20);
		dequeue(20);
		Assert.assertEquals("Third", dequeue(20));
	}
	
	@Test
	public void testEnqueueToWrapAround() {
		enqueue("First");
		enqueue("Second");
		enqueue("Third");
		enqueue("Fourth");
		enqueue("Fifth");
		enqueue("Sixth");
		enqueue("Seventh");
		enqueue("Eigth");
		enqueue("Ninth");
		enqueue("Tenth");
		dequeue(20);
		dequeue(20);
		enqueue("Firstoveragain");
		enqueue("Secondoveragain");
		
		Assert.assertEquals(10, count);
		Assert.assertEquals("Firstoveragain", start.value);
		
	}
}
*/
