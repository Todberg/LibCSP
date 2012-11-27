package sw901e12.csp.transportextentions;

import sw901e12.csp.core.ConnectionCore;
import sw901e12.csp.core.PacketCore;

public class TransportUDP implements ITransportExtension {

	@Override
	public void deliverPacket(ConnectionCore connection, PacketCore packet) {
		connection.processPacket(packet);
	}
}