package system;

import javax.safetycritical.JopSystem;

import com.jopdesign.io.IOFactory;
import com.jopdesign.sys.Const;
import com.jopdesign.sys.Native;

import sw901e12.WatchdogSafelet;


public class SCJApplicationMain
{
	public static void main(String[] args)
	{
		JopSystem.startMission(new WatchdogSafelet());
	}
}