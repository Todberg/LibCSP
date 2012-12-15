@Override
public void handleAsyncEvent() {
  Connection conn = cspManager.createConnection(ClientServerMission.NODE_ADDRESS, 12, CSPManager.TIMEOUT_NONE, null);
  
  if (conn != null) { 
    Packet p = cspManager.createPacket();
    p.setContent((int)'A');

    conn.send(p);

    Packet response = conn.read(CSPManager.TIMEOUT_NONE);
    System.out.println("Response: " + (char)response.readContent());

    conn.close();
  }
}