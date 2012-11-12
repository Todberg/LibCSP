package sw901e12.csp;

import sw901e12.csp.util.ConnectionQueue;
import sw901e12.csp.util.Const;
import sw901e12.csp.util.Queue;

public class ResourcePool {
	
	/* Pools */
	public Queue<Socket> sockets;
	public ConnectionQueue connections;
	public Queue<Packet> packets;
	
	/* Static connection pool containing all connections */
	public Connection[] globalConnections;
	
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
		this.globalConnections = new Connection[connectionsCapacity];
		
		Connection connection;
		for(byte i = 0; i < connectionsCapacity; i++) {
			connection = new Connection(packetsCapacity);
			connections.enqueue(connection);
			globalConnections[i] = connection;
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
	
	public Socket getSocket(int timeout) {
		return sockets.dequeue(timeout);
	}
	
	public void putSocket(Socket socket) {
		socket.dispose();
	}
	
	public Connection getConnection(int timeout) {
		return connections.dequeue(timeout);
	}
	
	public void putConnection(Connection connection) {
		connection.dispose();
	}
	
	public Packet getPacket(int timeout) {
		return packets.dequeue(timeout);
	}
	
	public void putPacket(Packet packet) {
		packet.dispose();
	}
	
	public Connection getGlobalConnection(int id) {
		for(Connection connection : globalConnections) {
			if(connection.id == id && connection.isOpen) {
				return connection;
			}
		}
		return null;
	}
}
