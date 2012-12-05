public void run() {
	State local = new State();
	initializePeriodicHandler();
	local.setStateToA();
	waitForNextPeriod();

	for(;;) {
		while (!switchToB) {
			doModeAWork();
			waitForNextPeriod();
		}

		local.setStateToB();

		while (!switchToA) {
			doModeBWork();
		}

		local.setStateToA();
	}	
}