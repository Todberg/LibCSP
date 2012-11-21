package sw901e12.csp.test;

import org.junit.After;
import org.junit.Before;

import sw901e12.csp.CSPManager;
import sw901e12.csp.core.PacketCore;
import sw901e12.csp.handlers.RouteHandler;
import sw901e12.csp.util.Const;
import sw901e12.csp.util.Queue;

public class RouteHandlerTest {
	CSPManager manager;
	
	@Before
	public void setUp() throws Exception {
		 manager = new CSPManager();
		 manager.initPools();
		 RouteHandler.packetsToBeProcessed = new Queue<PacketCore>(Const.DEFAULT_PACKET_QUEUE_SIZE_ROUTING);
	}

	@After
	public void tearDown() throws Exception {
		manager = null;
	}

}
