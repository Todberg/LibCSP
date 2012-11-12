
package sw901e12.csp.test;

import junit.framework.Assert;

import org.junit.Test;

import sw901e12.csp.util.IDispose;
import sw901e12.csp.util.Queue;


class DisposableTestObject implements IDispose {
	public int value;
	
	public DisposableTestObject(int value) {
		this.value = value;
	}
	
	public boolean equals(DisposableTestObject o) {
		return value == o.value;
	}
	
	@Override
	public String toString() { 
		return Integer.toString(value);
	}
	
	@Override
	public void dispose() {
		
	}
}

public class QueueTest extends Queue<DisposableTestObject> {
	
	public Queue<DisposableTestObject> queue;
	
	public QueueTest() {
		super((byte)10);
	}
	
	@Test
	public void testEnqueue() {
		enqueue(new DisposableTestObject(1));
		enqueue(new DisposableTestObject(2));
		Assert.assertEquals(2, count);
		Assert.assertTrue(new DisposableTestObject(1).equals(start.value));
		Assert.assertTrue(new DisposableTestObject(2).equals(head.next.value));
	}
	
	@Test
	public void testDequeue() {
		enqueue(new DisposableTestObject(1));
		enqueue(new DisposableTestObject(2));
		enqueue(new DisposableTestObject(3));
		
		dequeue(20);
		dequeue(20);
		Assert.assertTrue(new DisposableTestObject(3).equals(dequeue(20)));
	}
	
	@Test
	public void testEnqueueToWrapAround() {
		enqueue(new DisposableTestObject(1));
		enqueue(new DisposableTestObject(2));
		enqueue(new DisposableTestObject(3));
		enqueue(new DisposableTestObject(4));
		enqueue(new DisposableTestObject(5));
		enqueue(new DisposableTestObject(6));
		enqueue(new DisposableTestObject(7));
		enqueue(new DisposableTestObject(8));
		enqueue(new DisposableTestObject(9));
		enqueue(new DisposableTestObject(10));
		dequeue(20);
		dequeue(20);
		enqueue(new DisposableTestObject(11));
		enqueue(new DisposableTestObject(12));
		
		Assert.assertEquals(10, count);
		Assert.assertTrue(new DisposableTestObject(11).equals(start.value));
		
	}
}

