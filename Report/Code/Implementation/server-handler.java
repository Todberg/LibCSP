@Override
public void handleAsyncEvent() {
  connection = socket.accept(CSPManager.TIMEOUT_SINGLE_ATTEMPT);

  if (connection != null) {
    Packet p = connection.read(CSPManager.TIMEOUT_SINGLE_ATTEMPT);
    char data = (char)p.readContent();

    Packet response = cspManager.createPacket();

    switch(data) {
      case 'A':
        response.setContent((int)'X');
        break;
      case 'B':
        response.setContent((int)'Y');
        break;
    }

    connection.send(response);
    connection.close();
  }
}