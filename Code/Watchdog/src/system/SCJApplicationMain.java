package system;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.RelativeTime;
import javax.safetycritical.JopSystem;
import javax.safetycritical.StorageParameters;

import sw901e12.WatchdogSafelet;
import sw901e12.handlers.PEHModulePinger;
import sw901e12.sys.Config;
import sw901e12.wcet.PEHModulePingerMeasure;

public class SCJApplicationMain
{
	public static void main(String[] args)
	{
			JopSystem.startMission(new WatchdogSafelet());
	
	}
}