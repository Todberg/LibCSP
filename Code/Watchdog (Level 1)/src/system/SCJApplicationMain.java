package system;

import com.jopdesign.io.ExpansionHeader;
import com.jopdesign.io.ExpansionHeaderFactory;
import com.jopdesign.io.I2CFactory;
import com.jopdesign.io.I2Cport;

public class SCJApplicationMain
{
	public static void main(String[] args) throws InterruptedException
	{
		//JopSystem.startMission(new WatchdogSafelet());
		
		System.out.println("RunI2C");
		RunI2C();
		//RunTest();
	}
	
	private static void RunTest() throws InterruptedException {
		ExpansionHeader header = ExpansionHeaderFactory.getExpansionHeaderFactory().getExpansionHeader();
		System.out.println("hej");
		while(true) {
			header.write(Integer.MAX_VALUE);
			Thread.sleep(2000);
			header.write(0);
			Thread.sleep(2000);
		}
	}
	
	private static void RunI2C() throws InterruptedException {
		I2CFactory factory = I2CFactory.getFactory();
		I2Cport i2cPort = factory.getI2CportA();
		i2cPort.initialize(0x01, true);
		i2cPort.writeRead(0x77, 0xAA, 2);
		Thread.sleep(1000);
		int[] data = new int[2];
		i2cPort.readBuffer(data);
		System.out.println(data[0]);
		System.out.println(data[1]);
		System.out.println(i2cPort.rx_fifo_data);
		/*
		System.out.println("Polling DATA_RDY flag");
		while((i2cPort.status & i2cPort.DATA_RDY) == 0);
		System.out.println("Data is available");
		*/
	}
}