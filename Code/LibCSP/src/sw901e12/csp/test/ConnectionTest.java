package sw901e12.csp.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sw901e12.csp.CSPManager;
import sw901e12.csp.core.ConnectionCore;
import sw901e12.csp.core.PacketCore;
import sw901e12.csp.handlers.RouteHandler;
import sw901e12.csp.test.util.StopWatch;
import sw901e12.csp.util.Const;
import sw901e12.csp.util.Queue;

public class ConnectionTest {

	public CSPManager manager;
	public ConnectionCore connection;
	
	public StopWatch stopWatch = new StopWatch();
	
	@Before
	public void setUp() throws Exception {
		manager = new CSPManager();
		manager.initPools();
		connection = CSPManager.resourcePool.getConnection(CSPManager.TIMEOUT_SINGLE_ATTEMPT);
		RouteHandler.packetsToBeProcessed = new Queue<PacketCore>(Const.DEFAULT_PACKET_QUEUE_SIZE_ROUTING);
	}

	@After
	public void tearDown() throws Exception {
		connection = null;
	}
	
	@Test
	public void testRead() {
		ConnectionCore testConnection = setupTestConnectionWithTwoPacketsInQueue();
		
		Assert.assertEquals(25, testConnection.packets.dequeue(20).data);
		
		PacketCore receivedTestPacket = testConnection.packets.dequeue(CSPManager.TIMEOUT_SINGLE_ATTEMPT);
		Assert.assertEquals(30, receivedTestPacket.readContent());
		Assert.assertEquals(0xAC, receivedTestPacket.header);
	}
	
	private ConnectionCore setupTestConnectionWithTwoPacketsInQueue() {
		PacketCore p1 = new PacketCore(0xAB, 25);
		PacketCore p2 = new PacketCore(0xAC, 30);
		
		ConnectionCore c = new ConnectionCore((byte)20);
		c.packets.enqueue(p1);
		c.packets.enqueue(p2);
		
		return c;
	}
	
	@Test
	public void testReadWithEmptyPacketBuffer() {
		ConnectionCore testConnection = new ConnectionCore((byte)10);
		
		stopWatch.start();
		testConnection.read(20);
		stopWatch.stop();
		
		Assert.assertTrue(stopWatch.getElapsedTime() >= 20);
	}
	
	@Test
	public void testSend() {
		PacketCore testPacketToSend = setupTestPacketWithRandomPayload();
		
		connection.setId((byte)0xA, (byte)2, (byte)0xB, (byte)5); 
		connection.send(testPacketToSend);
		
		Assert.assertEquals(5, testPacketToSend.getDPORT());
		Assert.assertEquals(0xB, testPacketToSend.getDST());
	}
	
	private PacketCore setupTestPacketWithRandomPayload() {
		return new PacketCore(0xAB, 0xABC);
	}
	
	@Test
	public void testClose() {
		CSPManager.outgoingPorts = 2;
		connection.setSPORT((byte)49);
		connection.isOpen = true;
		connection.close();
		
		Assert.assertEquals(false, connection.isOpen);
		Assert.assertEquals(0, CSPManager.outgoingPorts);
	}
	
	@Test
	public void testSetId() {
		connection.setId((byte)9, (byte)11, (byte)4, (byte)8);
		final int idExpected = 0x00125908;
		
		assertEquals(idExpected, connection.id);
	}

	@Test
	public void testGetSRC() {
		final int idWithSrcAddressSetTo7 = 0x000E0000;
		connection.id = idWithSrcAddressSetTo7;
		
		assertEquals(7, connection.getSRC());
		
	}

	@Test
	public void testGetSPORT() {
		final int idWithSrcPortSetTo5 = 0x00002800;
		connection.id = idWithSrcPortSetTo5;
		
		assertEquals(5, connection.getSPORT());
	}

	@Test
	public void testGetDST() {
		final int idWithDstAddressSetTo18 = 0x00000480;
		connection.id = idWithDstAddressSetTo18;
		
		assertEquals(18, connection.getDST());
	}

	@Test
	public void testGetDPORT() {
		final int idWithDstPortSetTo7 = 0x00000007;
		connection.id = idWithDstPortSetTo7;
		
		assertEquals(7, connection.getDPORT());
	}

	@Test
	public void testSetSRC() {
		final int idWithSrcAddressSetTo4 = 0x00080000;
		connection.id = 0;
		connection.setSRC((byte)4);
		
		assertEquals(idWithSrcAddressSetTo4, connection.id);
	}

	@Test
	public void testSetSPORT() {
		final int idWithSrcPortSetTo7 = 0x00003800;
		connection.id = 0;
		connection.setSPORT((byte)7);
		
		assertEquals(idWithSrcPortSetTo7, connection.id);
	}

	@Test
	public void testSetDST() {
		final int idWithDstAddressSetTo9 = 0x00000240;
		connection.id = 0;
		connection.setDST((byte)9);
		
		assertEquals(idWithDstAddressSetTo9, connection.id);
	}

	@Test
	public void testSetDPORT() {
		final int idWithDstPortSetTo3 = 0x00000003;
		connection.id = 0;
		connection.setDPORT((byte)3);
		
		assertEquals(idWithDstPortSetTo3, connection.id);
	}
	
	@Test
	public void testGetConnectionIdFromPacketHeader() {
		/* 
		 * Bitpattern: 11 11010 00010 001011 000110 0000 0 0 0 0 
		 * Fields:     PRI SRC DST DPORT SPORT RES HMAC XTEA RDP CRC 
		 */
		int header = 0xF422C600;
		PacketCore packet = new PacketCore(header, 0);
		
		ConnectionCore c = new ConnectionCore((byte)20);
		c.setId((byte)2, (byte)11, (byte)26, (byte)6);
		
		int connId = ConnectionCore.getConnectionIdFromPacketHeader(packet);
		
		Assert.assertEquals(c.id, connId);
	}
	
	@Test
	public void testDispose() {
		connection.isOpen = true;
		connection.id = 42;

		PacketCore packet = null;
		for(int i = 0; i < Const.DEFAULT_PACKET_QUEUE_SIZE_PER_CONNECTION; i++) {
			packet = CSPManager.resourcePool.getPacket(CSPManager.TIMEOUT_SINGLE_ATTEMPT);
			connection.packets.enqueue(packet);
		}
		
		int connectionsInPoolBefore = CSPManager.resourcePool.connections.count;
		int packetsInPoolBefore = CSPManager.resourcePool.packets.count;
		
		connection.dispose();
		
		int connectionsInPoolAfter = CSPManager.resourcePool.connections.count;
		int packetsInPoolAfter = CSPManager.resourcePool.packets.count;
		
		Assert.assertEquals(false, connection.isOpen);
		Assert.assertEquals(0, connection.id);
		Assert.assertEquals((connectionsInPoolBefore + 1), connectionsInPoolAfter);
		Assert.assertEquals((packetsInPoolBefore + Const.DEFAULT_PACKET_QUEUE_SIZE_PER_CONNECTION), packetsInPoolAfter);
	}
}
