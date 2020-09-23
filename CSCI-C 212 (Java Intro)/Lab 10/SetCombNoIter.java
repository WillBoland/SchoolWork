import java.util.TreeSet;
import java.util.Iterator;
import java.util.Set;
//use add() and contians() methods together with an iterator
public class SetCombNoIter {
  public static Set union(Set a, Set b)  throws ClassCastException {
    Set unionSet = new TreeSet();
    unionSet.addAll(a);
    unionSet.addAll(b);
    return unionSet;
  }
  
  public static Set intersection(Set a, Set b) throws ClassCastException {
    Set intersectionSet = new TreeSet();
    
    intersectionSet = a;
    intersectionSet.retainAll(b);
    
    return intersectionSet;
  }
}
