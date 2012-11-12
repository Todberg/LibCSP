package sw901e12.csp.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sw901e12.csp.Connection;
import sw901e12.csp.Packet;

public class ConnectionTest {

	public Connection conn;
	
	@Before
	public void setUp() throws Exception {
		 conn = new Connection((byte) 20);
	}

	@After
	public void tearDown() throws Exception {
		conn = null;
	}

	@Test
	public void testSetId() {
		conn.setId((byte)9, (byte)11, (byte)4, (byte)8);
		final int idExpected = 0x00125908;
		
		assertEquals(idExpected, conn.id);
	}

	@Test
	public void testGetSRC() {
		final int idWithSrcAddressSetTo7 = 0x000E0000;
		conn.id = idWithSrcAddressSetTo7;
		
		assertEquals(7, conn.getSRC());
		
	}

	@Test
	public void testGetSPORT() {
		final int idWithSrcPortSetTo5 = 0x00002800;
		conn.id = idWithSrcPortSetTo5;
		
		assertEquals(5, conn.getSPORT());
	}

	@Test
	public void testGetDST() {
		final int idWithDstAddressSetTo18 = 0x00000480;
		conn.id = idWithDstAddressSetTo18;
		
		assertEquals(18, conn.getDST());
	}

	@Test
	public void testGetDPORT() {
		final int idWithDstPortSetTo7 = 0x00000007;
		conn.id = idWithDstPortSetTo7;
		
		assertEquals(7, conn.getDPORT());
	}

	@Test
	public void testSetSRC() {
		final int idWithSrcAddressSetTo4 = 0x00080000;
		conn.id = 0;
		conn.setSRC((byte)4);
		
		assertEquals(idWithSrcAddressSetTo4, conn.id);
	}

	@Test
	public void testSetSPORT() {
		final int idWithSrcPortSetTo7 = 0x00003800;
		conn.id = 0;
		conn.setSPORT((byte)7);
		
		assertEquals(idWithSrcPortSetTo7, conn.id);
	}

	@Test
	public void testSetDST() {
		final int idWithDstAddressSetTo9 = 0x00000240;
		conn.id = 0;
		conn.setDST((byte)9);
		
		assertEquals(idWithDstAddressSetTo9, conn.id);
	}

	@Test
	public void testSetDPORT() {
		final int idWithDstPortSetTo3 = 0x00000003;
		conn.id = 0;
		conn.setDPORT((byte)3);
		
		assertEquals(idWithDstPortSetTo3, conn.id);
	}
	
	@Test
	public void testGetConnectionIdFromPacketHeader() {
		/* 
		 * Bitpattern: 11 11010 00010 001011 000110 0000 0 0 0 0 
		 * Fields:     PRI SRC DST DPORT SPORT RES HMAC XTEA RDP CRC 
		 */
		int header = 0xF422C600;
		Packet packet = new Packet(header, 0);
		
		Connection c = new Connection((byte)20);
		c.setId((byte)26, (byte)6, (byte)2, (byte)11);
		
		int connId = Connection.getConnectionIdFromPacketHeader(packet);
		
		Assert.assertEquals(c.id, connId);
	}

}
