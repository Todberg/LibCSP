package sw901e12.csp.interfaces;

import sw901e12.csp.CSPManager;
import sw901e12.csp.core.Node;
import sw901e12.csp.core.PacketCore;
import sw901e12.csp.handlers.RouteHandler;

import com.jopdesign.io.I2CFactory;
import com.jopdesign.io.I2Cport;

public class InterfaceI2C implements IMACProtocol {

	private static InterfaceI2C instance;
	
	private static final byte INT_SIZE_IN_BYTES = 4;
	private static final byte BYTE_SHIFT_COUNTER = INT_SIZE_IN_BYTES - 1;  
	private static final byte FRAME_SIZE_IN_BYTES = (INT_SIZE_IN_BYTES * 2) + 1;
	
	private int frameByteIndex;
	private I2Cport I2CPort;
	
	private InterfaceI2C() { }
	
	public static InterfaceI2C getInterface() {
		if(instance == null) {
			instance = new InterfaceI2C();
		}
		
		return instance;
	}
	
	@Override
	public void initialize(int MACAddress) {	
		frameByteIndex = 0;
		I2CPort = I2CFactory.getFactory().getI2CportA();
		I2CPort.initialize(MACAddress, true);
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
	public void transmitPacket(PacketCore packet) {
		int[] frame = new int[FRAME_SIZE_IN_BYTES];
		
		Node packetDSTNode = RouteHandler.routeTable[packet.getDST()];
		insertNextHopAddressIntoFrame(frame, packetDSTNode.nextHopMacAddress);
		
		sliceDataIntoBytesAndInsertIntoFrame(frame, packet.header);
		sliceDataIntoBytesAndInsertIntoFrame(frame, packet.data);
		
		//I2CPort.write(packetDSTNode.nextHopMacAddress, frame);
		
		CSPManager.resourcePool.putPacket(packet);
	}

	/*
	 * The incoming I2C frame contains the whole packet, here we extract this
	 * from the data register and assemble the packet to be delivered
	 * 
	 * Context: ISR - invoked by the aperiodic event handler created during
	 * initialization for the I2C interface
	 */
	@Override
	public void receiveFrame() {
		int header = mergeNextDataBytesReceivedAndInsertIntoInteger();
		int data = mergeNextDataBytesReceivedAndInsertIntoInteger();
		
		PacketCore packet = CSPManager.resourcePool.getPacket(CSPManager.TIMEOUT_SINGLE_ATTEMPT);
		packet.header = header;
		packet.data = data;
		RouteHandler.packetsToBeProcessed.enqueue(packet);		
	}
	
	public int mergeNextDataBytesReceivedAndInsertIntoInteger() {
		int result = 0;
		
		for (byte b=0; b < 4; b++){
			result |= I2CPort.rx_fifo_data << position(b);
		}
		
		return result;
	}
	
	public void insertNextHopAddressIntoFrame(int[] frame, byte nextHopAddress) {
		frame[frameByteIndex] = nextHopAddress << 1;
		frameByteIndex++;
	}

	public void sliceDataIntoBytesAndInsertIntoFrame(int[] frame, int data) {
		int dataMask;
		for(byte b = 0 ; b < INT_SIZE_IN_BYTES; b++) {
			dataMask = 0xFF000000 >>>  b*8;
			frame[frameByteIndex] = (data & dataMask) >>> position(b);
			frameByteIndex++;
		}
	}
	
	public int position(int index) {
		return (BYTE_SHIFT_COUNTER - index)*8;
	}
}
