package sw901e12.csp;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sw901e12.csp.Connection;

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
		fail("Not yet implemented");
	}

	@Test
	public void testGetSRC() {
		final int headerWithSrcAddressSetTo14 = 0x000E0000;
		conn.id = headerWithSrcAddressSetTo14;
		
		assertEquals(14, conn.getSRC());
		
	}

	@Test
	public void testGetSPORT() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDST() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDPORT() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSRC() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSPORT() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDST() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDPORT() {
		fail("Not yet implemented");
	}

}
