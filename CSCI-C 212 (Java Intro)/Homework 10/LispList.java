public interface LispList {
  boolean empty();
  Object head();
  LispList tail();
  static EmptyList NIL = new EmptyList();
  int length();
  
  public LispList cons(Object obj);
  public String toString();
  public boolean contains(Object obj);
  
  static class Node
    {
        public Object data;
        public Node next;
    }
}
