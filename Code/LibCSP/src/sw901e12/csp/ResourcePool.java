package sw901e12.csp;

import sw901e12.csp.util.Queue;

public class ResourcePool {
	
	public Queue<Socket> sockets;
	
	public Queue<Connection> connections;
	
	public Queue<Packet> packets;
	
	public ResourcePool(byte socketsCapacity, byte connectionsCapacity, byte packetsCapacity) {
		this.sockets = new Queue<Socket>(socketsCapacity);
		this.connections = new Queue<Connection>(connectionsCapacity);
		this.packets = new Queue<Packet>(packetsCapacity);
	}
	
	public Socket getSocket() {
		return sockets.dequeue();
	}
	
	public void putSocket(Socket socket) {
		sockets.enqueue(socket);
	}
	
	public Connection getConnection() {
		return connections.dequeue();
	}
	
	public void putConnection(Connection connection) {
		connections.enqueue(connection);
	}
	
	public Packet getPacket() {
		return packets.dequeue();
	}
	
	public void putPacket(Packet packet) {
		packets.enqueue(packet);
	}
}
