import java.util.ArrayList;
/**
 * A binary search tree that can only have two or less children per sub tree
 */
public class BinarySearchTree {
  private Node root; //root node
  private int indices = 0; //amount of index stuff
  private ArrayList<Node> listOfNodes = new ArrayList<Node>();
  /**
   * Creates an empty Binary Tree
   */
  public BinarySearchTree() {
    root = null;
  }
  
  public Comparable findKthElement(int index) {
    for(Node n: listOfNodes) {
      if(index == n.index) {
        return n.data;
      }
    }
    return null;
  }
  
  public void add(Comparable obj) {
    Node newNode = new Node();
    newNode.data = obj;
    newNode.left = null;
    newNode.right = null;
    if(root == null) {
      root = newNode;
      root.index = ++indices;
      listOfNodes.add(newNode);
    } else {
      newNode.index = ++indices;
      listOfNodes.add(newNode);
      root.addNode(newNode);
    }
  }
  
  public void remove(Comparable obj) {
    Node toBeRemoved = root;
    Node parent = null;
    boolean found = false;
    while(!found && toBeRemoved != null) {
      int d = toBeRemoved.data.compareTo(obj);
      if(d == 0) { found = true; }
      else {
        parent = toBeRemoved;
        if (d > 0) { toBeRemoved = toBeRemoved.left; }
        else { toBeRemoved = toBeRemoved.right; }
      }
    }
    
    if(!found) { return; }
    
    if(toBeRemoved.left == null || toBeRemoved.right == null) {
      Node newChild;
      if(toBeRemoved.left == null) {
        newChild = toBeRemoved.right;
      } else {
        newChild = toBeRemoved.left;
      }
      
      if(parent == null) {
        root = newChild;
      } else if(parent.left == toBeRemoved) {
        parent.left = newChild;
      } else {
        parent.right = newChild;
      }
      return;
    }
    
    Node smallestParent = toBeRemoved;
    Node smallest = toBeRemoved.right;
    while(smallest.left != null) {
      smallestParent = smallest;
      smallest = smallest.left;
    }
    
    toBeRemoved.data = smallest.data;
    if(smallestParent == toBeRemoved) {
      smallestParent.right = smallest.right;
    } else {
      smallestParent.left = smallest.right;
    }
  }
  
  public void print() {
    print(root);
    System.out.println();
  }
  
  private static void print(Node parent) {
    if(parent == null) { return; }
    print(parent.left);
    System.out.print(parent.data + " ");
    print(parent.right);
  }
  
  //returns the height of the tree
  public int height() { return BinarySearchTree.height(root); }
  
  private static int height(Node n) {
    if(n == null)
      return 0;
    return 1 + Math.max(height(n.left), height(n.right));
  }
  
  /**
   * searches for an object. true if it is in the tree
   */
  public boolean find(Comparable obj) {
    Node current = root;
    while(current != null) {
      int d = current.data.compareTo(obj);
      if (d == 0) { return true; } //found it!
      else if(d < 0) { current = current.left; }
      else { current = current.right; }
    }
    return false;
  }
  
  ///inner class Node holds data, left and right nodes.
  class Node {
    public Comparable data;
    public Node left;
    public Node right;
    int index; //the index in the tree!
    
    public void addNode(Node newNode) {
      int comp = newNode.data.compareTo(data);
      if(comp < 0) {
        if(left == null) {
          left = newNode;
        } else {
          left.addNode(newNode);
        }
      } else if (comp > 0) {
        if(right == null) {
          right = newNode;
        } else {
          right.addNode(newNode);
        }
      }
    }
  }
}
