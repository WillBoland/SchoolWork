import junit.framework.TestCase;
import java.util.Set;
import java.util.TreeSet;

public class SetCombTest extends TestCase{
  
  public void testUnion() {
    Set a = new TreeSet();
    Set b = new TreeSet();
    Set expected = new TreeSet();
    
    a.add(1);
    a.add(2);
    a.add(3);
    a.add(4);
    
    b.add(4);
    b.add(6);
    b.add(3);
    b.add(7);
    
    for(int i = 1; i < 8; i++) {
      if(i != 5)
        expected.add(i);
    }
    assertTrue("union test", (expected.equals(SetComb.union(a, b))));
    
    a = new TreeSet();
    b = new TreeSet();
    a.add("alpha");
    a.add("charlie");
    b.add("bravo");
    b.add("apha");
    expected = new TreeSet();
    expected.addAll(a);
    expected.addAll(b);
    assertTrue("union test Strings", (expected.equals(SetComb.union(a, b))));
  }
  
  public void testIntersection() {
    Set a = new TreeSet();
    Set b = new TreeSet();
    Set expected = new TreeSet();
    
    a.add(1);
    a.add(2);
    a.add(3);
    a.add(4);
    
    b.add(4);
    b.add(6);
    b.add(3);
    b.add(7);
    
    expected.add(4);
    expected.add(3);
    
    assertTrue("intersection test", (expected.equals(SetComb.intersection(a, b))));
  }
}
