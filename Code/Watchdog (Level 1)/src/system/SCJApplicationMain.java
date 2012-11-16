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
		
		i2cPort.initialize(0xA, true);
		
		while(true) {
			System.out.println("while begin");
			int[] result = new int[5];
			System.out.println("before writeread");
			i2cPort.writeRead(0x01, 0x00, 5);
			System.out.println("after writeread");
			Thread.sleep(100);
			//while ((i2cPort.status & I2Cport.DATA_RDY) == 0);
			System.out.println("reading buffer");
			i2cPort.readBuffer(result);
			
			System.out.print("Received " + (char)result[0]);
			System.out.print((char)result[1]);
			System.out.print((char)result[2]);
			System.out.print((char)result[3]);
			System.out.println((char)result[4]);
			
			Thread.sleep(1000);
		}
	}
}