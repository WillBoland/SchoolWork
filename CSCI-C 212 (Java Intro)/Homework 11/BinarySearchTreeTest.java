import junit.framework.TestCase;
public class BinarySearchTreeTest extends TestCase {
  public void testfindKthElement() {
    BinarySearchTree tree = new BinarySearchTree();
    tree.add(2);
    tree.add(3);
    tree.add(7);
    int expected = 7;
    Comparable actual = tree.findKthElement(3);
    assertEquals("Finding third element", expected, actual);
    
    tree.add(5);
    tree.add(1);
    tree.add(-7);
    int expected2 = -7;
    Comparable actual2 = tree.findKthElement(6);
    assertEquals("Finding third element", expected2, actual2);
    
    tree.add(-2);
    tree.add(-3);
    tree.add(-70);
    int expected3 = -3;
    Comparable actual3 = tree.findKthElement(8);
    assertEquals("Finding third element", expected3, actual3);
  }
  
  
}
