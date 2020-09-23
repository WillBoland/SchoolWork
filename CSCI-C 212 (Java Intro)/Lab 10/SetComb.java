import java.util.TreeSet;
import java.util.Iterator;
import java.util.Set;
//use add() and contians() methods together with an iterator
public class SetComb {
  public static Set union(Set a, Set b)  throws ClassCastException {
    Set unionSet = new TreeSet();
    Iterator aIterator = a.iterator();
    Iterator bIterator = b.iterator();
    
    while(aIterator.hasNext()) {
      unionSet.add(aIterator.next());
    }
    while(bIterator.hasNext()) {
      unionSet.add(bIterator.next());
    }
    return unionSet;
  }
  
  public static Set intersection(Set a, Set b) throws ClassCastException {
    Set intersectionSet = new TreeSet();
    Iterator aIterator = a.iterator();
    Iterator bIterator = b.iterator();
    
    while(aIterator.hasNext()) {
      Object value = aIterator.next();
      if(b.contains(value)) {
        intersectionSet.add(value);
      }
    }
    
    return intersectionSet;
  }
}
