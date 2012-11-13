package sw901e12.csp.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sw901e12.csp.CSPManager;
import sw901e12.csp.Packet;
import sw901e12.csp.util.Const;

public class PacketTest {

	public CSPManager manager;
	public Packet packet;
	
	@Before
	public void setUp() {
		manager = new CSPManager();
		manager.initPools();
		
		/* 
		 * Bitpattern: 11 11010 00010 001011 000110 0000 0 0 0 0 
		 * Fields:     PRI SRC DST DPORT SPORT RES HMAC XTEA RDP CRC 
		 */
		int header = 0xF422C600;
		
		packet = CSPManager.resourcePool.getPacket(Const.TIMEOUT_SINGLE_ATTEMPT);
		packet.header = header;
		packet.data = 42;
	}
	
	@After
	public void tearDown() {
		this.packet = null;
	}

	@Test
	public void testGetCRC() {
		Assert.assertEquals(0x00, packet.getCRC());
	}
	
	@Test
	public void testGetRDP() {
		Assert.assertEquals(0x00, packet.getRDP());
	}
	
	@Test
	public void testGetXTEA() {
		Assert.assertEquals(0x00, packet.getXTEA());
	}
	
	@Test
	public void testGetHMAC() {
		Assert.assertEquals(0x00, packet.getHMAC());
	}
	
	@Test
	public void testGetRES() {
		Assert.assertEquals(0x00, packet.getRES());
	}
	
	@Test
	public void testGetSPORT() {
		Assert.assertEquals(0x06, packet.getSPORT());
	}
	
	@Test
	public void testGetDPORT() {
		Assert.assertEquals(0x0B, packet.getDPORT());
	}
	
	@Test
	public void testGetDST() {
		Assert.assertEquals(0x02, packet.getDST());
	}
	
	@Test
	public void testGetSRC() {
		Assert.assertEquals(0x1A, packet.getSRC());
	}
	
	@Test
	public void testGetPRI() {
		Assert.assertEquals(0x03, packet.getPRI());
	}
	
	@Test
	public void testDispose() {
		packet.header = 0xF422C600;
		packet.data = 42;
		
		int packetsInPoolBefore = CSPManager.resourcePool.packets.count;
		
		packet.dispose();
		
		int packetsInPoolAfter = CSPManager.resourcePool.packets.count;
		
		Assert.assertEquals(0, packet.header);
		Assert.assertEquals(0, packet.data);
		Assert.assertEquals((packetsInPoolBefore + 1), packetsInPoolAfter);
	}
}
