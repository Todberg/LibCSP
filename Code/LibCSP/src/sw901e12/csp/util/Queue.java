package sw901e12.csp.util;


public class Queue<T> {
	
	/*
	 * Holds the maximum capacity of the queue and
	 * a count of the empty spaces
	 */
	protected byte capacity;
	protected byte count;
	
	/*
	 * Handles to the start, head and tail element of the queue
	 */
	protected Element start;
	protected Element head;
	protected Element tail;
	
	/*
	 * Structure for each element in the queue.
	 * Holds the the value and a reference to the next
	 * element (if there is no more room in the queue
	 * the next field is null)
	 */
	protected final class Element {
		public T value;
		public Element next;
	}
	
	/*
	 * Instantiates the supplied number of queue elements
	 * with value fields set to null and chains them together 
	 * in a linked list using the next fields.
	 */
	public Queue(byte capacity) {
		this.capacity = capacity;
		this.count = 0;
		
		Element prev = null;
		for(int i = 0; i < capacity; i++) {
			Element element = new Element();	
			
			if(i == 0) {
				this.start = element;
				this.head = element;
				this.tail = element;
			} else {
				prev.next = element;
			}
			
			prev = element;
		}	
	}
	
	/*
	 * Continuously attempt to dequeue the head element of the queue
	 * until the dequeue operation succeedes or the supplied timeout occurs
	 */
	public T dequeue(long timeout) {
		T element = null;
		timeout = System.currentTimeMillis() + timeout;
		do {
			 element = dequeue();
		} while((System.currentTimeMillis() < timeout) && (element == null));
		
		return element;
	}
	
	/*
	 * Enqueues a new element in tail of the queue
	 */
	public synchronized void enqueue(T element) {
		if(count != capacity) {
			tail.value = element;
			if(tail.next == null) {
				tail = start;
			} else {
				tail = tail.next; 
			}
			count++;
		}
	}
	
	/*
	 * Dequeues an element in the head of the queue
	 */
	private synchronized T dequeue() {
		T element = null;
		if(count != 0) {
			element = head.value;
			head.value = null;
			head = head.next;
			
			count--;
		}
		
		return element;
	}
	
	public void clear() {
		Element element = null;
		for(byte i = 0; i < count; i++) {
			if(i == 0) {
				element = head;
			}
			
			element.value = null;
			element = (element.next == null ? start : element.next);
		}
	}
}
