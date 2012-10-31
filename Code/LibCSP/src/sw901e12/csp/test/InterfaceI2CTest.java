package sw901e12.csp.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sw901e12.csp.Packet;
import sw901e12.csp.interfaces.InterfaceI2C;

public class InterfaceI2CTest {

	private InterfaceI2C I2CInterface;
	
	@Before
	public void setUp() {
		this.I2CInterface = new InterfaceI2C();
	}
	
	@After
	public void tearDown() {
		this.I2CInterface = null;
	}

	@Test
	public void testReceiveFrame() {
		
	}
	
	@Test
	public void sliceDataIntoBytesAndInsertIntoFrame() {
		int[] frame = new int[9];
		
		int header = 0xD0000000;
		int data = 0xABCDABCD;
		
		I2CInterface.sliceDataIntoBytesAndInsertIntoFrame(frame, 1, header);
		I2CInterface.sliceDataIntoBytesAndInsertIntoFrame(frame, 5, data);
		
		assertEquals((header & 0x0000000D), frame[1]);
		assertEquals(header & 0x00000000, frame[2]);
		assertEquals(header & 0x00000000, frame[3]);
		assertEquals(header & 0x00000000, frame[4]);
	}
}
