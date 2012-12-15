@Override
@SCJAllowed(Level.SUPPORT)
public void handleAsyncEvent() {
  if (Config.DEBUG) {
    console.println("PEHModulePinger");
  }
  for (Module slave : slaves) {	
    conn = manager.createConnection(slave.getCSPAddress(), slave.getCSPPort(), CSPManager.TIMEOUT_SINGLE_ATTEMPT, null);
    
	if(conn != null) {
	  packet = manager.createPacket();
	  packet.setContent(42);
	  conn.send(packet);
	  Packet response = conn.read(10);
	  slave.setResponse(response != null ? true : false);
	  conn.close();
    }
  }
}