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
		//RunI2C();
		RunTest();
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
		
		i2cPort.initConf(0x0f);
		i2cPort.slaveMode();
		
		while(true) {
			System.out.println("Iteration");
			i2cPort.write(0x0f, 99999);
			Thread.sleep(100);
			i2cPort.read(0x0f, 1);
			System.out.println(i2cPort.rx_fifo_data);
			Thread.sleep(1000);
		}
	}
}