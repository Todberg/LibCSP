package sw901e12.csp.transportextentions;

import sw901e12.csp.core.ConnectionCore;
import sw901e12.csp.core.PacketCore;

public interface ITransportExtension {
	public void deliverPacket(ConnectionCore connection, PacketCore packet);
}
