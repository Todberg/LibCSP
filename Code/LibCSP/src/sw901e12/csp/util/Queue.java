package sw901e12.csp.util;

/*
 * Queue for holding and processing elements in FIFO order.
 * T will either be Socket, Packet or Connection
 */
public class Queue<T extends IDispose> {
	
	/*
	 * Maximum capacity of the queue and
	 * a count of the used spaces
	 */
	protected byte capacity;
	public byte count;
	
	/*
	 * Handles to the start, head and tail 
	 * element of the queue
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
	 * in a linked list using the next fields
	 */
	public Queue(byte capacity) {
		this.capacity = capacity;
		this.count = 0;
		
		Element prev = null;
		Element element;
		for(int i = 0; i < capacity; i++) {
			element = new Element();	
			
			if(i == 0) {
				start = element;
				head = element;
				tail = element;
			} else {
				prev.next = element;
			}	
			prev = element;
		}	
	}
	
	/*
	 * Continuously attempt to dequeue the head value of the queue
	 * until the dequeue operation succeedes or the supplied timeout occurs
	 */
	public T dequeue(long timeout) {
		T value = null;
		timeout = System.currentTimeMillis() + timeout;
		do {
			 value = dequeue();
		} while((System.currentTimeMillis() < timeout) && (value == null));	
		
		return value;
	}
	
	/* Enqueues a new value in the tail of the queue */
	public synchronized void enqueue(T value) {
		if(count != capacity) {
			tail.value = value;
			if(tail.next == null) {
				tail = start;
			} else {
				tail = tail.next; 
			}	
			count++;
		}
	}
	
	/* Dequeues a value in the head of the queue */
	private synchronized T dequeue() {
		T value = null;
		if(count != 0) {
			value = head.value;
			head.value = null;
			head = head.next;
			
			count--;
		}
		return value;
	}
	
	/* Clears all values and resets the queue */
	public void reset() {
		Element element = null;
		for(byte i = 0; i < count; i++) {
			if(i == 0) {
				element = head;
			}
			
			if (element.value != null) {
				element.value.dispose();
				element.value = null;
			}
		
			element = (element.next == null ? start : element.next);
		}
		count = 0;
		head = start;
		tail = head;
	}
}
