import junit.framework.TestCase;


public class LispListTest extends TestCase {
  
  public void testEmpty() {
    LispList notEmptyList = LispList.NIL.cons("a").cons("b").cons("c").cons("lol");
    assertTrue("empty failed. notemptylist", (notEmptyList.empty() == false));
    
    LispList emptyList = LispList.NIL;
    assertTrue("empty failed. emptylist", (emptyList.empty() == true));
  }
  
  public void testHead() {
    LispList notEmptyList = LispList.NIL.cons("a").cons("b").cons("c").cons("lol");
    assertTrue("head failed. notemptylist", (notEmptyList.head().equals("a")));
    
    LispList emptyList = LispList.NIL;
    try {
      emptyList.head();
      fail("Should have thrown an exception!");
    }
    catch (UnsupportedOperationException e) {
      // Good, that's what we expect
    }
  }
  
  public void testLength() {
    LispList notEmptyList = LispList.NIL.cons("a").cons("b").cons("c").cons("lol");
    assertTrue("length failed. notemptylist", (notEmptyList.length() == 4));
    
    LispList emptyList = LispList.NIL;
    assertTrue("length failed. emptylist", (emptyList.length() == 0));
  }
  
  public void testContains() {
    LispList notEmptyList = LispList.NIL.cons("a").cons("b").cons("c").cons("lol");
    assertTrue("contains failed. notemptylist", (notEmptyList.contains("lol") == true));
    
    LispList emptyList = LispList.NIL;
    assertTrue("contains failed. emptylist", (emptyList.contains("a") == false));
  }
  
}
