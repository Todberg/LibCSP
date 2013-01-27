package system;

import javax.safetycritical.JopSystem;

import sw901e12.WatchdogSafelet;

public class SCJApplication
{
	public static void main(String[] args) throws InterruptedException
	{
		JopSystem.startMission(new WatchdogSafelet());
	}
}