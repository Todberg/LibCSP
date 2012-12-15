@Override
@SCJAllowed(Level.SUPPORT)
public void handleAsyncEvent() {
  if(Config.DEBUG) {
	console.println("PEHModuleResponseChecker");
  }
  for (Module slave : slaves) { // @WCA loop<=10
    if(slave.getResponse() == true) {
	  slave.resetResponse();
    } else {
	  mission.executeRecovery = true;
	  break;
	}
  }
}