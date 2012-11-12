package sw901e12.csp.util;

public class Const {
	/*
	 * Limitation of the number of allowed hosts and
	 * ports
	 */
	public static final byte MAX_NETWORK_HOSTS = 32;	
	
	/* Incoming port range  */
	public static final byte MAX_SERVICE_PORTS = 8;
	public static final byte MAX_BIND_PORTS = 39;
	
	/* Outgoing port range */
	public static final byte MAX_OUTGOING_PORTS = 15;

	public static final byte BROADCAST_ADDRESS = 31;
	public static final byte ANY_PORT = 0x01;
	
	/*
	 * Default Pool capacities that are used 
	 * if the user does not supply any
	 */
	public static final byte DEFAULT_MAX_CONNECTIONS = 20;	
	public static final byte DEFAULT_MAX_CONNECTION_PER_SOCKET = 5;
	public static final byte DEFAULT_PACKET_QUEUE_SIZE_ROUTING = 32;
	public static final byte DEFAULT_PACKET_QUEUE_SIZE_PER_CONNECTION = 10;
	public static final byte DEFAULT_MAX_SOCKETS = 15;
	public static final byte DEFAULT_MAX_PACKETS_STORED = 100;
	
	/* Timeouts */
	public static final byte TIMEOUT_NONE = -1;
	public static final byte TIMEOUT_SINGLE_ATTEMPT = 0;
}
