package system;

import javax.safetycritical.JopSystem;

import sw901e12.csp.testapplication.ClientServerMission;

public class SCJApplication {
	
	public static void main(String[] args) {
		JopSystem.startMission(new ClientServerMission());
	}
}