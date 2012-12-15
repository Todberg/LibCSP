/* Simulator Module */
int MACAddress = Config.MAC_ADDRESS;
int CSPAddress = Config.CSP_ADDRESS;
int CSPPort = 0x01;
slaves[0] = Module.create("Module 1", MACAddress, CSPAddress, CSPPort);
IMACProtocol loopbackInterface = InterfaceLoopback.getInterface();
loopbackInterface.initialize(MACAddress);
manager.routeSet(CSPAddress, loopbackInterface, MACAddress);

/* Board Module */
int MACAddress = 0x01;
int CSPAddress = 0x01;
int CSPPort = 0x01;
slaves[0] = Module.create("Module 1", MACAddress, CSPAddress, CSPPort);
IMACProtocol I2CInterface = InterfaceI2C.getInterface();
I2CInterface.initialize(MACAddress);
manager.routeSet(CSPAddress, I2CInterface, 0xFF);