/**
 * A tree that can only have two or less children per sub tree
 */
public class BinaryTree {
  private Node root; //root node
  
  /**
   * Creates an empty Binary Tree
   */
  public BinaryTree() {
    root = null;
  }
  
  /**
   * Creates a binary tree with root, left, and right trees.
   */
  public BinaryTree(Object rootData, BinaryTree left, BinaryTree right) {
    this.root = new Node();
    this.root.data = rootData;
    this.root.left = left.root;
    this.root.right = right.root;
  }
  
  ///returns the height of the tree
  public int height() { return BinaryTree.height(root); }
  
  private static int height(Node n) {
    if(n == null)
      return 0;
    return 1 + Math.max(height(n.left), height(n.right));
  }
  
  ///inner class Node holds data, left and right nodes.
  class Node {
    public Object data;
    public Node left;
    public Node right;
  }
}
