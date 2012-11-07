package sw901e12.csp;

import sw901e12.csp.util.Const;
import sw901e12.csp.util.Queue;

public class ResourcePool {
	
	public Queue<Socket> sockets;
	public Queue<Connection> connections;
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
	
	private void initializeSocketPool(byte socketsCapacity, byte connectionsCapacity) {
		this.sockets = new Queue<Socket>(socketsCapacity);
		
		Socket socket;
		for(byte i = 0; i < socketsCapacity; i++) {
			socket = new Socket(connectionsCapacity);
			sockets.enqueue(socket);
		}
	}
	
	private void initializeConnectionPool(byte connectionsCapacity, byte packetsCapacity) {
		this.connections = new Queue<Connection>(connectionsCapacity);
		
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
		socket.connections.clear();
		sockets.enqueue(socket);
	}
	
	public Connection getConnection() {
		return connections.dequeue(Const.TIMEOUT_SINGLE_ATTEMPT);
	}
	
	public void putConnection(Connection connection) {
		connection.packets.clear();
		connections.enqueue(connection);
	}
	
	public Packet getPacket() {
		return packets.dequeue(Const.TIMEOUT_SINGLE_ATTEMPT);
	}
	
	public void putPacket(Packet packet) {
		packet.header = 0;
		packet.data = 0;
		packets.enqueue(packet);
	}
}
