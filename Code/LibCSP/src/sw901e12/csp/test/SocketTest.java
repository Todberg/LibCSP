package sw901e12.csp.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sw901e12.csp.CSPManager;
import sw901e12.csp.core.ConnectionCore;
import sw901e12.csp.core.PacketCore;
import sw901e12.csp.core.Port;
import sw901e12.csp.core.SocketCore;
import sw901e12.csp.handlers.RouteHandler;
import sw901e12.csp.test.util.StopWatch;
import sw901e12.csp.util.Const;

public class SocketTest {

	public CSPManager manager;
	public SocketCore socket;
	
	public StopWatch stopWatch = new StopWatch();
	
	@Before
	public void setUp() {
		manager = new CSPManager();
		manager.initPools();
		socket = CSPManager.resourcePool.getSocket(CSPManager.TIMEOUT_SINGLE_ATTEMPT);
	}
	
	@After
	public void tearDown() {
		socket = null;
	}
	
	@Test
	public void testAcceptConnectionIsNullAndTimeoutOccurs() {
		int timeout = 1000;
		
		stopWatch.start();
		ConnectionCore connection = socket.accept(timeout);
		stopWatch.stop();
		
		Assert.assertNull(connection);
		Assert.assertTrue(stopWatch.getElapsedTime() >= 1000);
	}
	
	@Test
	public void testAcceptConnectionIsNotNullAndTimeoutDoesNotOccur() {
		int timeout = 1000;
		
		for(int i = 0; i < 4; i++) {
			socket.connections.enqueue(CSPManager.resourcePool.getConnection(CSPManager.TIMEOUT_SINGLE_ATTEMPT));
		}
		
		stopWatch.start();
		ConnectionCore connection = socket.accept(timeout);
		stopWatch.stop();
		
		Assert.assertNotNull(connection);
		Assert.assertTrue(stopWatch.getElapsedTime() < 1000);
	}
	
	@Test
	public void testClose() {
		RouteHandler.portTable = new Port[Const.MAX_INCOMING_PORTS];
		
		for (byte i = 0; i < Const.MAX_INCOMING_PORTS; i++) {
			RouteHandler.portTable[i] = new Port();
		}
		
		socket.port = 6;
		RouteHandler.portTable[6].socket = socket;
		RouteHandler.portTable[6].isOpen = true;
		
		socket.close();
		
		Assert.assertEquals(false, RouteHandler.portTable[6].isOpen);
		Assert.assertNull(RouteHandler.portTable[6].socket);
		
	}
	
	@Test
	public void testDispose() {
		socket.port = 7;
		
		ConnectionCore connection = null;
		PacketCore packet = null;	
		for(int i = 0; i < Const.DEFAULT_MAX_CONNECTION_PER_SOCKET; i++) {
			connection = CSPManager.resourcePool.getConnection(CSPManager.TIMEOUT_SINGLE_ATTEMPT);
			socket.connections.enqueue(connection);
			for(int j = 0; j < Const.DEFAULT_PACKET_QUEUE_SIZE_PER_CONNECTION; j++) {
				packet = CSPManager.resourcePool.getPacket(CSPManager.TIMEOUT_SINGLE_ATTEMPT);
				connection.packets.enqueue(packet);
			}
		}
		
		int socketsInPoolBefore = CSPManager.resourcePool.sockets.count;
		int connectionsInPoolBefore = CSPManager.resourcePool.connections.count;
		int packetsInPoolBefore = CSPManager.resourcePool.packets.count;
		
		socket.dispose();
		
		int socketsInPoolAfter = CSPManager.resourcePool.sockets.count;
		int connectionsInPoolAfter = CSPManager.resourcePool.connections.count;
		int packetsInPoolAfter = CSPManager.resourcePool.packets.count;
		
		Assert.assertEquals(0, socket.port);
		Assert.assertEquals((socketsInPoolBefore + 1), socketsInPoolAfter);
		Assert.assertEquals((connectionsInPoolBefore + Const.DEFAULT_MAX_CONNECTION_PER_SOCKET), connectionsInPoolAfter);
		Assert.assertEquals((packetsInPoolBefore + (Const.DEFAULT_PACKET_QUEUE_SIZE_PER_CONNECTION * Const.DEFAULT_MAX_CONNECTION_PER_SOCKET)), packetsInPoolAfter);
	}
}
