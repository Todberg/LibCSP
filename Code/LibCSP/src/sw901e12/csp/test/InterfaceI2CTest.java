package sw901e12.csp.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sw901e12.csp.interfaces.InterfaceI2C;

public class InterfaceI2CTest {
	
	InterfaceI2C interfaceI2C;
	
	@Before
	public void setUp() {
		interfaceI2C = InterfaceI2C.getInterface();
	}
	
	@After
	public void tearDown() {
		interfaceI2C = null;
	}
	
	@Test
	public void testGetBytePositionInInteger() {
		final int bytePositionWanted = 2;
		final int expectedShiftedPosition = 8; 
		
		int actualShiftedPosition = interfaceI2C.position(bytePositionWanted);
		assertEquals(expectedShiftedPosition, actualShiftedPosition);
	}
	
	@Test
	public void testSliceDataIntoBytesAndInsertIntoFrame() {
		int[] frame = new int[9];
		
		int header = 0xABDECFDA;
		int data = 0xABCDABCD;
		
		interfaceI2C.sliceDataIntoBytesAndInsertIntoFrame(frame, header);
		interfaceI2C.sliceDataIntoBytesAndInsertIntoFrame(frame, data);
		
		assertEquals(0xAB, frame[0]);
		assertEquals(0xDE, frame[1]);
		assertEquals(0xCF, frame[2]);
		assertEquals(0xDA, frame[3]);
		assertEquals(0xAB, frame[4]);
	}
}