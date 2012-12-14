@Override
public void dispose() {
	this.isOpen = false;
	this.id = 0;
	this.packets.reset();
	CSPManager.resourcePool.putConnection(this);
}