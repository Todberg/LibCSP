public class ClientServerMission extends Mission implements Safelet<Mission> {

  public static final int NODE_ADDRESS = 0xA;
  private CSPManager manager;

  @Override
  @SCJAllowed(Level.SUPPORT)
  protected void initialize() {
    manager = new CSPManager();
    manager.init((byte)ClientServerMission.NODE_ADDRESS);
    manager.initPools();
    manager.startRouteHandler();

    initializeFirstClientHandler();
    initializeSecondClientHandler();
    initializeServerHandler();
  }
  ...
}