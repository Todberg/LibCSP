package system;

import javax.safetycritical.JopSystem;

import sw901e12.WatchdogSafelet;


public class SCJApplicationMain
{
	public static void main(String[] args)
	{
		JopSystem.startMission(new WatchdogSafelet());
	}
}