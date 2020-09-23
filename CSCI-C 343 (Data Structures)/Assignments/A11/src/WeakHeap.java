import java.util.ArrayList;
import java.util.List;

class RootE extends Exception {
}

class NoLeftChildE extends Exception {
}

class NoRightChildE extends Exception {
}

public class WeakHeap {
    private int size;
    private ArrayList<Item> elems;
    private ArrayList<Integer> flips;

    //--------------------
    //MARK: Initializers
    //----------------------------------------------------------------------------
    WeakHeap() {
        this.size = 0;
        this.elems = new ArrayList<>();
        this.flips = new ArrayList<>();
    }

    WeakHeap (ArrayList<Item> items) {
        this.size = 0;
        this.elems = new ArrayList<>();
        this.flips = new ArrayList<>();

        //copy items
        for(Item item: items) {
            item.setPosition(size);
            item.setHeap(this);
            this.elems.add(item);
            this.flips.add(0);
            this.size += 1;
        }

        //call weakHeapify()
        this.weakHeapify();
    }

    //--------------------
    //MARK: Public Functions
    //--------------------
    //MARK: weakHeapify()
    //----------------------------------------------------------------------------
    /*
     * This method executes a loop that starts with the last element
     * in the array and ends with the element at position 1. In each
     * iteration, the item is joined with its distinguished ancestor.
     * Note that when calling join, the distinguished ancestor is
     * always in the first argument position.
     */
    void weakHeapify () {
        for(int i = size - 1; i >= 1; i -= 1) {
            try {
                this.join(getDAncestorIndex(i), i);
            } catch (RootE error) {
                //Nothing cause we hit root.
            }
        }
    }


    //--------------------
    //MARK: isEmpty()
    //----------------------------------------------------------------------------
    boolean isEmpty() {
        return size == 0;
    }

    //--------------------
    //MARK: getSize()
    //----------------------------------------------------------------------------
    int getSize() {
        return size;
    }

    //--------------------
    //MARK: findMin()
    //----------------------------------------------------------------------------
    Item findMin() {
        return elems.get(0);
    }

    //--------------------
    //MARK: getElems()
    //----------------------------------------------------------------------------
    List<Item> getElems() {
        return elems;
    }

    //--------------------
    //MARK: getElem()
    //----------------------------------------------------------------------------
    Item getElem (int i) {
        return elems.get(i);
    }

    //--------------------
    //MARK: getValue()
    //----------------------------------------------------------------------------
    int getValue(int i) {
        return elems.get(i).getValue();
    }

    //--------------------
    //MARK: getFlip()
    //----------------------------------------------------------------------------
    int getFlip (int i) {
        return flips.get(i);
    }

    //--------------------
    //MARK: toString()
    //----------------------------------------------------------------------------
    public String toString() {
        return getElems().toString();
    }

    //--------------------
    //MARK: Index Functions
    // Computations with indices
    //  * Position 1 in elems is for the right child of the root
    //  * After that the left child of an item at position i is at position 2i+flips(i)
    //  * and the right child is at position 2i+1-flips(i)
    //  * The parent of a child at position i is at position i/2

    //--------------------
    //MARK: getParentIndex()
    //----------------------------------------------------------------------------
    int getParentIndex(int i) throws RootE {
        final int ROOT_INDEX = 0;
        if(i != ROOT_INDEX) {
            return i / 2;
        }
        throw new RootE();
    }

    //--------------------
    //MARK: getLeftChildIndex()
    //----------------------------------------------------------------------------
    int getLeftChildIndex(int i) throws NoLeftChildE {
        try {
            final int ROOT_INDEX = 0;
            final int CHILD_INDEX = (2 * i) + flips.get(i);
            if (i != ROOT_INDEX && CHILD_INDEX < size) {
                return (2 * i) + flips.get(i);
            }
        } catch (IndexOutOfBoundsException error) {} //Out of bounds, no left, so continue to throw statement
        throw new NoLeftChildE();
    }

    //--------------------
    //MARK: getRightChildIndex()
    //----------------------------------------------------------------------------
    int getRightChildIndex(int i) throws NoRightChildE {
        try {
            final int CHILD_INDEX = (2 * i) + 1 - flips.get(i);
            if(CHILD_INDEX < size) {
                return CHILD_INDEX;
            }
        } catch (IndexOutOfBoundsException error) {} //Out of bounds, no left, so continue to throw statement
        throw new NoRightChildE();
    }

    //--------------------
    //MARK: isLeftChild()
    //----------------------------------------------------------------------------
    boolean isLeftChild (int i) throws RootE {
        try {
            final int P_INDEX = getParentIndex(i);
            return getLeftChildIndex(P_INDEX) == i;
        } catch (RootE error) {
            throw new RootE();
        } catch (NoLeftChildE e) {
            return false;
        }
    }

    //--------------------
    //MARK: isRightChild()
    //----------------------------------------------------------------------------
    boolean isRightChild (int i) throws RootE {
        try {
            final int P_INDEX = getParentIndex(i);
            return getRightChildIndex(P_INDEX) == i;
        } catch (RootE error) {
            throw new RootE();
        } catch (NoRightChildE e) {
            return false;
        }
    }

    //--------------------
    //MARK: getDAncestorIndex()
    //----------------------------------------------------------------------------
    /**
     * Retrieves the distinguished ancestor for a given index.
     * The distinguished ancestor of a right child is its parent.
     * The distinguished ancestor of a left child is the distinguished ancestor of its parent.
     * @param i The index to retrieve the ancestor for.
     * @return The distinguished ancestor index.
     * @throws RootE When we are at index 0, we have no Distinguished ancestor.
     */
    int getDAncestorIndex(int i) throws RootE {
        return (isRightChild(i) ? getParentIndex(i) : getDAncestorIndex(getParentIndex(i)));
    }

    //--------------------
    //MARK: getLeftMostChildIndex()
    //----------------------------------------------------------------------------
    int getLeftMostChildIndex () throws NoLeftChildE {
        if(size < 2) {
            throw new NoLeftChildE();
        }

        int currentIndex = 1;
        int previousIndex = 1;
        try {
            while(currentIndex < size) {
                previousIndex = currentIndex;
                currentIndex = getLeftChildIndex(currentIndex);
            }
        } catch (NoLeftChildE error) {}

        return previousIndex;
    }

    //--------------------
    //MARK: Main Method Helpers
    //--------------------
    //MARK: swap()
    //----------------------------------------------------------------------------
    void swap(int i, int j) {
        Item newI = elems.get(j);
        Item newJ = elems.get(i);

        newI.setPosition(i);
        newJ.setPosition(j);

        this.elems.set(i, newI);
        this.elems.set(j, newJ);
    }

    //--------------------
    //MARK: join()
    //----------------------------------------------------------------------------
    /*
     * If the value at position j is smaller than the value
     * at position i, they are swapped and the flip bit at
     * position j is negated. In that case the method returns
     * false. If no action was taken, the method returns true.
     */
    boolean join (int i, int j) {
        try {
            if(elems.get(j).getValue() < elems.get(i).getValue()) {
                swap(i, j);                     //swap.
                flips.set(j, 1 - flips.get(j)); //flip bit at j is negated.
                return false;
            }
        } catch (IndexOutOfBoundsException error) {}

        return true;
    }

    //--------------------
    //MARK: moveUp()
    //----------------------------------------------------------------------------
    /*
     * The method starts by joining j with its distinguished ancestor.
     * If a swap was performed, the method recursively continues by moving
     * the distinguished ancestor up. If not, the method returns immediately.
     */
    void moveUp (int j) {
        try {
            final int D_INDEX = getDAncestorIndex(j);
            if(!join(D_INDEX, j)) { //swap was performed.
                moveUp(D_INDEX);
            }
        } catch(RootE error) {
            return;
        }
    }

    //--------------------
    //MARK: moveDown()
    //----------------------------------------------------------------------------
    /*
     * The method starts by locating the leftmost child along the leftmost
     * spine. It then repeatedly joins j with that leftmost child and its
     * parents.
     */
    void moveDown (int j) {
        try {
            int currentLeft = getLeftMostChildIndex(); //starts as leftmost
            ArrayList<Integer> itemsToJoin = new ArrayList<>();
            itemsToJoin.add(currentLeft);   //add our left

            //fill itemsToJoin with parents now
            while(currentLeft != 0) {
                try {
                    currentLeft = getParentIndex(currentLeft);
                    itemsToJoin.add(currentLeft);
                } catch (RootE error) {}
            }

            //repeatedly joins j with that leftmost child and its parents
            while(!itemsToJoin.isEmpty()) {
                join(j, itemsToJoin.remove(0));
            }
        } catch (NoLeftChildE error) {}
    }

    //--------------------
    //MARK: updateKey()
    //----------------------------------------------------------------------------
    void updateKey (int i, int value) {
        assert value < elems.get(i).getValue();
        elems.get(i).setValue(value);
        moveUp(i);
    }

    //--------------------
    //MARK: Main Methods
    //--------------------
    //MARK: insert()
    //----------------------------------------------------------------------------
    /*
     * The method adds the new item at the end of the array making sure
     * it calls setPosition and setHeap with the appropriate parameters
     * and initializes the associated flip bit correctly. As a little
     * optimization, if the inserted item is in a left child position, it
     * will reset the flip bit of the parent to 0.
     * The method then calls moveUp.
     */
    void insert (Item item) {
        item.setPosition(size);
        item.setHeap(this);
        elems.add(item);
        flips.add(0);
        size += 1;

        try {
            if (isLeftChild(size - 1)) {
                flips.set(getParentIndex(size - 1), 0);
            }
        } catch (RootE error) {}

        moveUp(size - 1);
    }

    //--------------------
    //MARK: extractMin()
    //----------------------------------------------------------------------------
    /*
     * Like we did in the previous and as is outlined in the lecture notes,
     * the last item in the array is moved to location 0. And then moveDown
     * is called. Just make sure you don't call moveDown if the array has exactly
     * one element!
     */
    Item extractMin () {
        Item smallest = this.findMin();
        swap(0, size - 1);
        elems.remove(size - 1);
        this.size -= 1;
        if(size > 1) {
            moveDown(0);
        }
        return smallest;
    }

    //--------------------
    //MARK: checkOrder()
    //----------------------------------------------------------------------------
    /*
     * This method is useful for testing and debugging. It loops over the elements
     * of the array starting from the end until reaching index 1. For each item,
     * the method checks that it is larger than its distinguished ancestor.
     */
    boolean checkOrder () {
        for(int i = size - 1; i > 1; i -= 1) {
            try {
                if (elems.get(i).getValue() < elems.get(getDAncestorIndex(i)).getValue()) {
                    return false;
                }
            } catch (RootE error) {}
        }
        return true;
    }

}


