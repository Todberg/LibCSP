package system;

import javax.safetycritical.JopSystem;

import com.IICSafelet;
import com.jopdesign.io.IIC;
import com.jopdesign.io.IICFactory;

public class SCJApplicationMain
{
	public static void main(String[] args)
	{
		JopSystem.startMission(new IICSafelet());
	}
}