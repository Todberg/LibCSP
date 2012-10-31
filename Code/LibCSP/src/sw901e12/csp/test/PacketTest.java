package sw901e12.csp.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sw901e12.csp.Packet;

public class PacketTest {

	Packet packet;
	
	@Before
	public void setUp() {
		/* 
		 * Bitpattern: 11 11010 00010 001011 000110 0000 0 0 0 0 
		 * Fields:     PRI SRC DST DPORT SPORT RES HMAC XTEA RDP CRC 
		 */
		int header = 0xF422C600;
		
		packet = new Packet(header, 0);
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
}
