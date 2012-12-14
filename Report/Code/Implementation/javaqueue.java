public byte capacity;

protected Element start;
protected Element head;
protected Element tail;

protected final class Element {
  public T value;
  public Element next;
}