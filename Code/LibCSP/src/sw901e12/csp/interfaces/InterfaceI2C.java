package sw901e12.csp.interfaces;

import com.jopdesign.io.I2CFactory;
import com.jopdesign.io.I2Cport;

import sw901e12.csp.Packet;

public class InterfaceI2C implements IMACProtocol {

	static final short INT_SIZE_IN_BYTES = 4;
	static final short FRAME_SIZE_IN_BYTES = INT_SIZE_IN_BYTES * 2 + 1;
	
	I2Cport I2CPort;
	
	@Override
	public void initialize(int nodeAddress) {
		I2CPort = I2CFactory.getFactory().getI2CportA();
		I2CPort.initConf(nodeAddress);
	}

	/*
	 * The hardware object for I2C and the corresponding microcontroller
	 * works by writing or reading single bytes at a time to the tx/rx register.
	 * Transmitting a packet therefore needs to divide the whole 32bit header
	 * and 32bit data into single bytes that are written to the tx register.
	 * The result is the transmit buffer containing the first 7 bits with
	 * the I2C address, the next 32 bits is the packet header 
	 * and the final 32 bits the data
	 */
	@Override
	public void transmitPacket(Packet packet) {
		
		int[] frame = new int[FRAME_SIZE_IN_BYTES];
		
		frame[0] = packet.getDST() << 1;
		//sliceDataIntoBytesAndInsertIntoFrame(frame, 1, packet.header);
		//sliceDataIntoBytesAndInsertIntoFrame(frame, 5, packet.data);
		
		I2CPort.write(frame);
	}

	@Override
	public void receiveFrame() {
		
	}
	
	public void sliceDataIntoBytesAndInsertIntoFrame(int[] frame, 
			int offset, 
			int data) {
		
		int dataMask;
		for(int i = 0; i < INT_SIZE_IN_BYTES; i++) {
			dataMask = 0x000000FF << i*4;
			frame[offset+i] = (data & dataMask) >> i*4; 
		}	
	}
}
