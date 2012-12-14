public final static int MASK_SPORT = 0x00003F00;

public int header;
public int data;

public byte getSPORT() {
  return (byte)((header & MASK_SPORT) >>> 8);
}

public void setSPORT(byte value) {
  header &= ~(MASK_SPORT);
  header |= (int)value << 8;
}