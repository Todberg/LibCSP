package sw901e12.csp.util;

public class HashMap<T> {

	private static final byte CAPACITY = 10;
	
	private HashEntry<T>[] elements;
	
	private static final class HashEntry<T> {
		
		private int key;
		
		private T value;
		
		public HashEntry(byte key, T value) {
			this.key = key;
			this.value = value;
		}
	}
	
	public T get(byte key) {
		return null;
	}
	
	public void put(byte key, T value) {
		int idx = hash(key);
	}
	
	private int hash(byte key) {
		return 0;
	}
}
