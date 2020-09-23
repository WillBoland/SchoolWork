//-----------------------------------------------------------------------
// Empty AVL exception

import java.util.NoSuchElementException;

class EmptyAVLE extends Exception {
}

//-----------------------------------------------------------------------
// Abstract AVL class

abstract class AVL implements TreePrinter.PrintableNode {

    //--------------------------
    // Static fields and methods
    //--------------------------

    static EmptyAVLE EAVLX = new EmptyAVLE();

    static AVL EAVL = new EmptyAVL();
    static AVL AVLLeaf(int elem) {
<<<<<<< HEAD
        return new AVLNode(elem);
    }

    // Recursively copy the tree changing AVL nodes to BST nodes
    static BST toBST (AVL avl) {
        return new EmptyBST();
=======
        return new AVLNode(elem, EAVL, EAVL);
    }

    // Recursively copy the tree changing AVL nodes to BST nodes
    static BST toBST(AVL avl) {
        try {
            return new BSTNode(avl.AVLData(), toBST(avl.AVLLeft()), toBST(avl.AVLRight()));
        } catch (EmptyAVLE emptyAVLE) {
            return new EmptyBST();
        }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    abstract int AVLData() throws EmptyAVLE;

    abstract AVL AVLLeft() throws EmptyAVLE;

    abstract AVL AVLRight() throws EmptyAVLE;

    abstract int AVLHeight();

    abstract boolean isEmpty();

    //--------------------------
    // Main methods
    //--------------------------

    abstract boolean AVLfind(int key);

    abstract AVL AVLinsert(int key);

    abstract AVL AVLeasyRight();

    abstract AVL AVLrotateRight();

    abstract AVL AVLeasyLeft();

    abstract AVL AVLrotateLeft();

    abstract AVL AVLdelete(int key) throws EmptyAVLE;

    abstract Pair<Integer, AVL> AVLshrink() throws EmptyAVLE;

<<<<<<< HEAD
    public boolean isBalanced() {
        try {
            return Math.abs(this.AVLLeft().AVLHeight() - this.AVLRight().AVLHeight()) <= 1;
        } catch (EmptyAVLE e) {
            return true;
        }
    }
=======
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
}

//-----------------------------------------------------------------------

class EmptyAVL extends AVL {

    //--------------------------
    // Getters and simple methods
    //--------------------------

    int AVLData() throws EmptyAVLE {
        throw EAVLX;
    }

    AVL AVLLeft() throws EmptyAVLE {
        throw EAVLX;
    }

    AVL AVLRight() throws EmptyAVLE {
        throw EAVLX;
    }

    int AVLHeight() {
        return 0;
    }

    boolean isEmpty() {
        return true;
    }

    ;

    //--------------------------
    // Main methods
    //--------------------------

    boolean AVLfind(int key) {
        return false;
    }

    AVL AVLinsert(int key) {
        return AVLLeaf(key);
    }

    AVL AVLeasyRight() {
        throw new Error("Internal bug: should never call easyRight on empty tree");
    }

    AVL AVLrotateRight() {
        throw new Error("Internal bug: should never call rotateRight on empty tree");
    }

    AVL AVLeasyLeft() {
        throw new Error("Internal bug: should never call easyLeft on empty tree");
    }

    AVL AVLrotateLeft() {
        throw new Error("Internal bug: should never call rotateLeft on empty tree");
    }

    AVL AVLdelete(int key) throws EmptyAVLE {
        throw EAVLX;
    }

    Pair<Integer, AVL> AVLshrink() throws EmptyAVLE {
        throw EAVLX;
    }

    //--------------------------
    // Override
    //--------------------------

    public boolean equals(Object o) {
        return (o instanceof EmptyAVL);
    }

    //--------------------------
    // Printable interface
    //--------------------------

    public TreePrinter.PrintableNode getLeft() {
        return null;
    }

    public TreePrinter.PrintableNode getRight() {
        return null;
    }

    public String getText() {
        return "";
    }
}

//-----------------------------------------------------------------------

class AVLNode extends AVL {
    private int data;
    private AVL left, right;
    private int height;

<<<<<<< HEAD
     public AVLNode(int data) {
         this.data = data;
         this.left = new EmptyAVL();
         this.right = new EmptyAVL();
         this.height = 1;
     }

=======
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    public AVLNode(int data, AVL left, AVL right) {
        this.data = data;
        this.left = left;
        this.right = right;
<<<<<<< HEAD
        this.height = 1 + Math.max(left.AVLHeight(), right.AVLHeight());
=======
        height = 1 + Math.max(left.AVLHeight(), right.AVLHeight());
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    int AVLData() {
        return data;
    }

    AVL AVLLeft() {
        return left;
    }

    AVL AVLRight() {
        return right;
    }

    int AVLHeight() {
        return height;
    }

    boolean isEmpty() {
        return false;
    }

    ;

    //--------------------------
    // Main methods
    //--------------------------

    /**
     * @noinspection Duplicates
     */
    boolean AVLfind(int key) {
<<<<<<< HEAD
        if(key == this.data) { //we found it
            return true;
        }

        return (key < data ? this.left.AVLfind(key) : this.right.AVLfind(key));
    }

    AVL AVLinsert(int key) {
        if(key < data) { //left
            AVL newLeft = new AVLNode(data, this.left.AVLinsert(key), this.right);
            if(newLeft.isBalanced()) { //we are done
                return newLeft;
            } else {
                return newLeft.AVLrotateRight();
            }
        } else { //right
            AVL newRight = new AVLNode(data, this.left, this.right.AVLinsert(key));
            if(newRight.isBalanced()) { //we are done
                return newRight;
            } else {
                return newRight.AVLrotateLeft();
            }
        }
=======
        boolean found = false;

        if (key == this.data) {
            found = true;
        } else if (key < this.data) {
            found = left.AVLfind(key);
        } else {
            found = right.AVLfind(key);
        }
        return found;
    }

    AVL AVLinsert(int key) {
        AVL b;
        if (key < this.data) {
            AVL newLeft = left.AVLinsert(key);
            b = new AVLNode(data, newLeft, right);

            if (right.AVLHeight()+1 < newLeft.AVLHeight()) {
                b = b.AVLrotateRight();
            }
        } else {
            AVL newRight = right.AVLinsert(key);
            b = new AVLNode(data, left, newRight);
            if (left.AVLHeight()+1 < newRight.AVLHeight() ) {
                b = b.AVLrotateLeft();
            }
        }




        return b;
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    //left becomes new root
    //original root becomes right tree of new root
    //new root's original right tree becomes left tree on new left tree of new root
    AVL AVLeasyRight() {
        try {
<<<<<<< HEAD
            AVL leftsRight = left.AVLRight();
            AVL answer = new AVLNode(left.AVLData(), left.AVLLeft(), new AVLNode(this.data, leftsRight, this.right));
            return answer;
        } catch (EmptyAVLE emptyAVLE) {
            return new EmptyAVL();
        }
    }

    //focuses on left subtree first and compare heights of children
    //  if left child is higher, just do an easyRight on whole tree
    //  else do easyLeft on left child and then an easyRight on whole tree
    AVL AVLrotateRight() {
        try {
            if(left.AVLLeft().AVLHeight() > left.AVLRight().AVLHeight() || left.AVLLeft().isEmpty()) { //left child is higher
                return AVLeasyRight();
            } else {
                AVL newLeft = left.AVLeasyLeft(); //easy left on left child
                AVL answer = new AVLNode(data, newLeft, right);
                return answer.AVLeasyRight(); //easy right on whole tree
            }
        } catch (EmptyAVLE emptyAVLE) {
            return new EmptyAVL();
        }
=======
            AVL newRoot = left;
            AVL danglingMan = newRoot.AVLRight();

            return new AVLNode(newRoot.AVLData(), newRoot.AVLLeft(),  new AVLNode(data, danglingMan,right));


        } catch (EmptyAVLE e) {
            throw new Error("Why are you rotating something that's empty?");
        }
    }


    AVL AVLrotateRight() {
        try {
            AVL finalTree;
            if (left.AVLLeft().AVLHeight() >= left.AVLRight().AVLHeight()) {
                finalTree = this.AVLeasyRight();
            } else {
                AVL newLeft = left.AVLeasyLeft();
                AVL midwayTree = new AVLNode(data, newLeft, right);
                finalTree = midwayTree.AVLeasyRight();

            }


            return finalTree;
        } catch (EmptyAVLE E) {
            throw new Error ("Why are you rotating something that's empty?");
        }

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    AVL AVLeasyLeft() {
        try {
<<<<<<< HEAD
            AVL rightsLeft = right.AVLLeft();
            AVL answer = new AVLNode(right.AVLData(), new AVLNode(this.data, this.left, rightsLeft), right.AVLRight());
            return answer;
        } catch (EmptyAVLE emptyAVLE) {
            return new EmptyAVL();
=======
            AVL newRoot = right;
            AVL danglingMan = newRoot.AVLLeft();

            return new AVLNode(newRoot.AVLData(), new AVLNode(data, left, danglingMan), newRoot.AVLRight());


        } catch (EmptyAVLE e) {
            throw new Error("Why are you rotating something that's empty?");
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
        }
    }

    //focuses on right subtree first and compare heights of children
    //  if right child is higher, just do an easyLeft on whole tree
    //  else do easyRight on right child and then an easyLeft on whole tree
    AVL AVLrotateLeft() {
<<<<<<< HEAD
        try {
            if(right.AVLRight().AVLHeight() > right.AVLLeft().AVLHeight() || right.AVLRight().isEmpty()) { //right child is higher
                return AVLeasyLeft();
            } else {
                AVL newRight = right.AVLeasyRight(); //easy right on right child
                AVL answer = new AVLNode(data, left, newRight);
                return answer.AVLeasyLeft(); //easy left on whole tree
            }
        } catch (EmptyAVLE emptyAVLE) {
            return new EmptyAVL();
        }
    }

    AVL AVLdelete(int key) throws EmptyAVLE {
        //we want to delete root (hard case)
        if(key == this.data) {
            //first invoke a helper called shrink on the left subtree
            Pair<Integer, AVL> shrResult = left.AVLshrink();

            //result is a new tree whose root is first component returned by shrunk,
            // left subtree is second component returned by shrink,
            // and right subtree is original right subtree
            AVL result = new AVLNode(shrResult.getFirst(), shrResult.getSecond(), right);

            return result;
        }

        //easy cases
        if(key < data) { //left subtree
            AVL rec = new AVLNode(data, left.AVLdelete(key), right);
            return(rec.isBalanced() ? rec : rec.AVLrotateLeft());
        } else { //right subtree
            AVL rec = new AVLNode(data, left, right.AVLdelete(key));
            return(rec.isBalanced() ? rec : rec.AVLrotateRight());
        }
=======
       try{
           AVL finalTree;
           if(right.AVLRight().AVLHeight() >= right.AVLLeft().AVLHeight()){
               finalTree = this.AVLeasyLeft();
           }
           else{
               AVL newRight = right.AVLeasyRight();
               AVL midwayTree = new AVLNode(data, left, newRight);
               finalTree = midwayTree.AVLeasyLeft();

           }

           return finalTree;
       }catch(EmptyAVLE e){
           throw new Error("What are you doing rotating something empty?");
       }
    }

    AVL AVLdelete(int key) throws EmptyAVLE {
        AVL finalTree;

            if (key < data) {
                AVL newLeft = left.AVLdelete(key);
                finalTree = new AVLNode(data, newLeft, right);
                if (newLeft.AVLHeight() + 1 < right.AVLHeight()) {
                    finalTree = finalTree.AVLrotateLeft();
                }

            } else if (key > data) {
                AVL newRight = right.AVLdelete(key);
                finalTree = new AVLNode(data, left, newRight);
                if (newRight.AVLHeight() + 1 < left.AVLHeight()) {
                    finalTree = finalTree.AVLrotateRight();
                }
            } else {
                try {
                    Pair<Integer, AVL> largestElementOnLeftAndBalancedLeft = left.AVLshrink();
                    finalTree = new AVLNode(largestElementOnLeftAndBalancedLeft.getFirst(), largestElementOnLeftAndBalancedLeft.getSecond(), right);
                    if (largestElementOnLeftAndBalancedLeft.getSecond().AVLHeight() + 1 < right.AVLHeight()) {
                        finalTree = finalTree.AVLrotateLeft();
                    }

                } catch (EmptyAVLE e) {
                    //return right if "this" doesnt have a left
                    finalTree = right;
                }
            }


        return finalTree;

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    //RIGHT MOST LEAF: Opposite of our BST implementation
    Pair<Integer, AVL> AVLshrink() throws EmptyAVLE {
<<<<<<< HEAD
        //is right empty?
        if(this.right.isEmpty()) { //we can delete
            return new Pair<>(data, new EmptyAVL());
        }
=======
       try{
           Pair<Integer, AVL> rightMostChildAndTreeWithoutIt = right.AVLshrink();

           AVL newLeft = new AVLNode(data, left, rightMostChildAndTreeWithoutIt.getSecond());

           if(left.AVLHeight() > rightMostChildAndTreeWithoutIt.getSecond().AVLHeight()+1){
               newLeft = newLeft.AVLrotateRight();
           }
           return new Pair<>(rightMostChildAndTreeWithoutIt.getFirst(), newLeft);

       }catch(EmptyAVLE e){
           return new Pair<Integer,AVL>(data, left);

       }
    }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9

        Pair<Integer, AVL> result = this.right.AVLshrink();
        if(!result.getSecond().isBalanced()) {
            result = new Pair<>(result.getFirst(), result.getSecond().AVLrotateLeft());
        }
        return result;
    }

    //--------------------------
    // Override
    //--------------------------

    public boolean equals(Object o) {
        if (o instanceof AVLNode) {
            AVLNode other = (AVLNode) o;
            return data == other.data && left.equals(other.left) && right.equals(other.right);
        }
        return false;
    }

    //--------------------------
    // Printable interface
    //--------------------------

    public TreePrinter.PrintableNode getLeft() {
        return left.isEmpty() ? null : left;
    }

    public TreePrinter.PrintableNode getRight() {
        return right.isEmpty() ? null : right;
    }

    public String getText() {
        return String.valueOf(data);
    }
}

//-----------------------------------------------------------------------
//-----------------------------------------------------------------------
