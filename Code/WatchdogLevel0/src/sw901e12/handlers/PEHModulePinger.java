package sw901e12.handlers;

import javax.safetycritical.io.SimplePrintStream;

import sw901e12.Module;
import sw901e12.sys.Config;

public class PEHModulePinger implements Runnable {

	protected SimplePrintStream console;
	protected Module[] slaves;
	
	public PEHModulePinger(SimplePrintStream console, Module[] slaves) {
		this.console = console;
		this.slaves = slaves;
	}

	@Override
	public void run() {
		if (Config.DEBUG) {
			console.println("PEHModulePinger");
		}
		for (Module slave : slaves) {
			slave.getModulePinger().ping();
		}
	}
}
