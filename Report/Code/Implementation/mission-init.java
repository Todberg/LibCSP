public class ClientServerMission extends Mission implements Safelet<Mission> {

  public static final int NODE_ADDRESS = 0xA;
  private CSPManager manager;

  @Override
  @SCJAllowed(Level.SUPPORT)
  protected void initialize() {
    manager = new CSPManager();
    
    final int ROUTING_HANDLER_RELEASE_PERIOD_IN_MS = 20;
    final int ROUTING_HANDLER_PRIORITY = 18;
    
    PriorityParameters routingPriorityParameters = 
      new PriorityParameters(ROUTING_HANDLER_PRIORITY);
    PeriodicParameters routingPeriodicParameters = 
      new PeriodicParameters(new RelativeTime(0, 0), 
        new RelativeTime(ROUTING_HANDLER_RELEASE_PERIOD_IN_MS, 0));
    
    manager.init((byte)ClientServerMission.NODE_ADDRESS,
        routingPriorityParameters,
        routingPeriodicParameters);
    manager.initPools();

    manager.routeSet(ClientServerMission.NODE_ADDRESS, manager.getIMACProtocol(manager.INTERFACE_LOOPBACK), 0x3C);
    
    initializeFirstClientHandler();
    initializeSecondClientHandler();
  
    initializeServerHandler();

    manager.startRouteHandler();
  }
  ...
}