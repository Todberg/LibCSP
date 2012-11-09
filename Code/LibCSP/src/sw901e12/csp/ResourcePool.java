package sw901e12.csp;

import sw901e12.csp.util.ConnectionQueue;
import sw901e12.csp.util.Const;
import sw901e12.csp.util.Queue;

public class ResourcePool {
	
	/* Pools */
	public Queue<Socket> sockets;
	public ConnectionQueue connections;
	public Queue<Packet> packets;
	
	public ResourcePool(byte socketsCapacity,
			byte connectionsPerSocketCapacity,
			byte connectionsCapacity,
			byte packetsPerConnectionCapacity,
			byte packetsCapacity) {
		
		initializeSocketPool(socketsCapacity, connectionsPerSocketCapacity);
		initializeConnectionPool(connectionsCapacity, packetsCapacity);
		initializePacketPool(packetsCapacity);
	}
	
	private void initializeSocketPool(byte socketsCapacity, byte connectionsPerSocketCapacity) {
		this.sockets = new Queue<Socket>(socketsCapacity);
		
		Socket socket;
		for(byte i = 0; i < socketsCapacity; i++) {
			socket = new Socket(connectionsPerSocketCapacity);
			sockets.enqueue(socket);
		}
	}
	
	private void initializeConnectionPool(byte connectionsCapacity, byte packetsCapacity) {
		this.connections = new ConnectionQueue(connectionsCapacity);
		
		Connection connection;
		for(byte i = 0; i < connectionsCapacity; i++) {
			connection = new Connection(packetsCapacity);
			connections.enqueue(connection);
		}
	}
	
	private void initializePacketPool(byte packetsCapacity) {
		this.packets = new Queue<Packet>(packetsCapacity);
		
		Packet packet;
		for(byte i = 0; i < packetsCapacity; i++) {
			packet = new Packet(0, 0);
			packets.enqueue(packet);
		}
	}
	
	public Socket getSocket() {
		return sockets.dequeue(Const.TIMEOUT_SINGLE_ATTEMPT);
	}
	
	public void putSocket(Socket socket) {
		socket.dispose();
	}
	
	public Connection getConnection() {
		return connections.dequeue(Const.TIMEOUT_SINGLE_ATTEMPT);
	}
	
	public void putConnection(Connection connection) {
		connection.dispose();
	}
	
	public Packet getPacket() {
		return packets.dequeue(Const.TIMEOUT_SINGLE_ATTEMPT);
	}
	
	public void putPacket(Packet packet) {
		packet.dispose();
	}
}
