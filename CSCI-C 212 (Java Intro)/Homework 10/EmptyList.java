public class EmptyList implements LispList {
  public boolean empty() { return true; }
  public Object head() throws UnsupportedOperationException{  throw new UnsupportedOperationException(); }
  public LispList tail() throws UnsupportedOperationException { throw new UnsupportedOperationException(); }
  public String toString() { return ""; }
  public int length() { return 0; }
  public boolean contains(Object obj) { return false; }
  
 public NonEmptyList cons(Object o) { return new NonEmptyList(o, new EmptyList()); }
}
