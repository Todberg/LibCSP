package sw901e12.csp.util;

import sw901e12.csp.Connection;

public class ConnectionQueue extends Queue<Connection> {

	public ConnectionQueue(byte capacity) {
		super(capacity);
	}
	
	public synchronized Connection getConnection(int id) {
		
		Element element = null;
		for(byte i = 0; i < super.count; i++) {
			if(i == 0) {
				element = super.head;
			}

			if(element.value.id == id) {
				break;
			}
						
			element = (element.next == null ? super.start : element.next);
		}
		
		return element.value;
	}
}
