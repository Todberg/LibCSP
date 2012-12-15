@Override
public void handleAsyncEvent() {
  if(Config.DEBUG) {
 	console.println("PEHSystemRecovery");
  }

  if(mission.executeRecovery) {
    if(Config.DEBUG) {
	  console.println("Initiating system recovery...");
	}
	recovery.executeRecovery();
  }
}