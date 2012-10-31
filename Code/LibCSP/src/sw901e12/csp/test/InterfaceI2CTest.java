package sw901e12.csp.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sw901e12.csp.Packet;
import sw901e12.csp.interfaces.InterfaceI2C;

public class InterfaceI2CTest extends InterfaceI2C {
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testGetBytePositionInInteger() {
		final int testBytePositionWanted = 2;
		final int testExpectedShiftedPosition = 8; 
		
		int actualShiftedPosition = this.position(testBytePositionWanted);
		assertEquals(testExpectedShiftedPosition, actualShiftedPosition);
	}
	
	@Test
	public void testInsertNodeDestinationAddressIntoFrame() {
		final int testNodeHeaderWithDestinationAddress = 0x300000; // Address 3
		final int testNodeDestinationAddressInFrameByteWithI2CLSBUsed = 0x00000006;
		Packet testPacket = new Packet(testNodeHeaderWithDestinationAddress, 0);
	
		int[] frame = new int[6];
		this.insertNodeDestinationAddressIntoFrame(frame, testPacket);
		
		assertEquals(testNodeDestinationAddressInFrameByteWithI2CLSBUsed, frame[0]);
	}
	
	@Test
	public void testSliceDataIntoBytesAndInsertIntoFrame() {
		int[] frame = new int[9];
		
		int header = 0xABDECFDA;
		int data = 0xABCDABCD;
		
		this.sliceDataIntoBytesAndInsertIntoFrame(frame, header);
		this.sliceDataIntoBytesAndInsertIntoFrame(frame, data);
		
		assertEquals(0xAB, frame[0]);
		assertEquals(0xDE, frame[1]);
		assertEquals(0xCF, frame[2]);
		assertEquals(0xDA, frame[3]);
		assertEquals(0xAB, frame[4]);
	}
}
