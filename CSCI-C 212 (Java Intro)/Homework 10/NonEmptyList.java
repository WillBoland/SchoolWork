public class NonEmptyList implements LispList {
  private Object head;
  private LispList tail;
  
  public NonEmptyList(Object head, LispList tail) {
        this.tail = tail;
        this.head = head;
    }
  
  public LispList cons(Object o) {
    NonEmptyList list = this; //sets to self
    while(!list.tail().empty()) { //while it is empty
        list = (NonEmptyList)list.tail(); //turn tail
      }
    list.setTail(new NonEmptyList(o, new EmptyList()));
    return this;
    }
 
 public void setTail(LispList list) {
        this.tail = list;
    }
  
  public int length() {
    if(this.tail.empty())
      return 1;
    return 1 + this.tail.length(); //recursive
  }
   
  public boolean empty() { return false; } //by definition, can not be true
  
  public Object head() { return this.head; } //head
  
  public LispList tail() { 
    return this.tail; //returns tail
  }
  
  public boolean contains(Object o) {
      if(this.head.equals(o)) 
        return true;
      else if(this.tail.empty())
        return false;
      else return
        this.tail.contains(o);
    }
  
  public String toString() { return this.head() + " " + this.tail().toString(); }
}
