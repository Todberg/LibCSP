package system;

import javax.safetycritical.JopSystem;
import com.BlinkerSafelet;

public class SCJApplicationMain
{
	public static void main(String[] args)
	{
		JopSystem.startMission(new BlinkerSafelet());
	}
}