public T dequeue(long timeout) {
  T value = null;

  boolean waitForever = (timeout == CSPManager.TIMEOUT_NONE ? true : false);
  timeout = System.currentTimeMillis() + timeout;

  do {
    value = dequeue();
  } while(((System.currentTimeMillis() < timeout) || waitForever) && (value == null));	

  return value;
}