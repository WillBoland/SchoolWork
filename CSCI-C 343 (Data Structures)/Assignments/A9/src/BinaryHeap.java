import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Binary heap with reverse bits...
 * We can flip left and right subtrees in one operation
 * <p>
 * There is a subtle interaction between the heap and the items it contains.
 * - the heap maintains an arraylist of all items
 * - each item has a reference to the heap and its position within the arraylist
 */

class NoParentE extends Exception {
}

class NoLeftChildE extends Exception {
}

class NoRightChildE extends Exception {
}

public class BinaryHeap {
    private int size;
    private ArrayList<Item> elems;

    BinaryHeap() {
        this.size = 0;
        this.elems = new ArrayList<>();
    }

    /**
     * This constructor performs "heapify". First it copy the incoming
     * elements one-by-one to the arraylist 'elems' stored as an instance variable.
     * For each item copied, the constructor should initialize properly using
     * setPosition and setHeap. When everything is properly initialized and
     * copied to 'elems' the constructor calls 'heapify'.
     *
     * We will be using 0! (added by Will B)
     */
    BinaryHeap(ArrayList<Item> es) {
<<<<<<< HEAD
        this.size = es.size();
        this.elems = new ArrayList<>();

        //copy incoming elements one-by-one to 'elems'
        for(int index = 0; index < size; index += 1) {
            Item current = es.get(index);
            current.setHeap(this);
            current.setPosition(index);
            this.elems.add(current);
        }
        //constructor calls heapify
=======
        this.size = 0;
        this.elems = new ArrayList<>();
        size = es.size();
        for (int i = 0; i < es.size(); i++) {
            Item old = es.get(i);
            old.setPosition(i);
            old.setHeap(this);
            elems.add(old);
        }

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
        heapify();
    }

    /**
     * Implement it as discussed in class...
     * Call move down on half of elems working later to start 15...14...13...12...etc
     */
<<<<<<< HEAD
    void heapify () {
        int half = this.elems.size() / 2;
        while(half != -1) {
            moveDown(half);
            half--;
=======
    void heapify() {

        for (int i = size / 2 - 1; i >= 0; i--) {
            moveDown(i);
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
        }
    }

    boolean isEmpty() {
        return size == 0;
    }

    int getSize() {
        return size;
    }

    /**
     * We will location 0 in the array. The minimum is always guaranteed to be there
     * unless of course the array is empty
     */
    Item findMin() {
<<<<<<< HEAD
=======
        // TODO
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
        return elems.get(0);
    }

    List<Item> getElems() {
        return elems;
    }

    Item getElem(int i) {
        return elems.get(i);
    }

    /**
     * As discussed in class and in the notes, the parent is at index i/2
     * unless of course the current node is the root of the tree
     */
    int getParentIndex(int i) throws NoParentE {
<<<<<<< HEAD
        final int ROOT_OF_TREE = 0;
        if(i == ROOT_OF_TREE) {
            throw new NoParentE();
        }
        return (i - 1) / 2;
=======
        // TODO
        if (i != 0) {
            return (i - 1) / 2;
        }

        throw new NoParentE();
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    /**
     * Under normal circumstances the left child is at index 2i+1. It is possible
     * the index 2i+1 is outside of the array bounds and in that case the node
     * does not have a left child. It is also possible that the current element
     * has its reverse bit set, which means that the child at index 2i+1 is actually
     * the right child and the child at index 2i+2 is the left child.
     */
    int getLeftChildIndex(int i) throws NoLeftChildE {
<<<<<<< HEAD
        try {
            final int LC_INDEX = 2 * i + 1;
            final int LC_REV_INDEX = 2 * i + 2;
            final boolean IS_REVERSED = elems.get(i).getRevbit() == 1;

            if (IS_REVERSED) {
                if (LC_REV_INDEX >= size) {
                    throw new NoLeftChildE();
                }
            } else {
                //check bounds
                if (LC_INDEX >= size) {
                    throw new NoLeftChildE();
                }
            }

            return (IS_REVERSED ? LC_REV_INDEX : LC_INDEX);
        } catch (IndexOutOfBoundsException e) {
            throw new NoLeftChildE();
        }
    }

    int getRightChildIndex(int i) throws NoRightChildE {
        try {
            final int RC_INDEX = 2 * i + 2;
            final int RC_REV_INDEX = 2 * i + 1;
            final boolean IS_REVERSED = elems.get(i).getRevbit() == 1;

            if (IS_REVERSED) {
                if (RC_REV_INDEX >= size) {
                    throw new NoRightChildE();
                }
            } else {
                //check bounds
                if (RC_INDEX >= size) {
                    throw new NoRightChildE();
                }
            }

            return (IS_REVERSED ? RC_REV_INDEX : RC_INDEX);
        } catch (IndexOutOfBoundsException e) {
            throw new NoRightChildE();
        }
=======
        // check bounds
        // check reverse bit
        if (!elems.isEmpty()) {
            if (this.getElem(i).getRevbit() == 0) {

                if (2 * i + 1 < size) {


                    return 2 * i + 1;

                }
            }

            if (this.getElem(i).getRevbit() == 1) {

                if (2 * i + 2 < size) {


                    return 2 * i + 2;

                }
            }


        }
        throw new NoLeftChildE();
    }

    int getRightChildIndex(int i) throws NoRightChildE {

        if (!elems.isEmpty()) {
            if (this.getElem(i).getRevbit() == 0) {

                if (2 * i + 2 < size) {


                    return 2 * i + 2;

                }
            }

            if (this.getElem(i).getRevbit() == 1) {

                if (2 * i + 1 < size) {


                    return 2 * i + 1;

                }
            }
        }

        throw new NoRightChildE();
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    /**
     * This method swaps the array entries at the given indices. It also needs
     * to update each element with its new position.
     */
    void swap(int i, int j) {
<<<<<<< HEAD
        Item newI = elems.get(j);
        Item newJ = elems.get(i);

        newI.setPosition(i);
        newJ.setPosition(j);

        this.elems.set(i, newI);
        this.elems.set(j, newJ);
=======

        Item oldI = this.getElem(i);
        Item oldJ = this.getElem(j);

        //set i to j and j to i
        oldI.setPosition(j);
        oldJ.setPosition(i);

        elems.set(j, oldI);
        elems.set(i, oldJ);

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    int getValue(int i) {
        return elems.get(i).getValue();
    }

    /**
     * When an element is inserted, it is inserted in the bottom layer of the
     * tree and then moveUp is recursively called to move it to its proper
     * position.
     */
    void moveUp(int i) {
        try {
<<<<<<< HEAD
            final int P_INDEX = getParentIndex(i);
            if(elems.get(i).getValue() < elems.get(P_INDEX).getValue()) { //current is smaller than parent; swap and call again
                swap(i, P_INDEX); //swap it with parent
                moveUp(P_INDEX); //call again with new Parent Index
            }
        } catch (NoParentE e) {
            //we dont have a parent because we hit the root!!!
=======
            int parentIndex = getParentIndex(i);

            swap(parentIndex, i);

            moveUp(parentIndex);

        } catch (NoParentE e) {


>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
        }
    }

    void insert(Item ek) {
<<<<<<< HEAD
        //same as constructor
        Item current = ek;
        current.setHeap(this);
        current.setPosition(size);
        this.elems.add(current);

        size += 1;
        moveUp(size - 1);
=======

        ek.setPosition(size);
        elems.add(ek);
        moveUp(size);
        size++;

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    /**
     * When a large element finds itself high in the tree for some reason,
     * we need to move it down. For that we need to compare it to both its
     * children and swap it with the smaller of them
     */
    int minChildIndex(int i) throws NoLeftChildE {
<<<<<<< HEAD
        try {
            final int LC_INDEX = getLeftChildIndex(i);
            final int RC_INDEX = getRightChildIndex(i);
            final int RC_VAL = elems.get(RC_INDEX).getValue();
            final int LC_VAL = elems.get(LC_INDEX).getValue();
            return (RC_VAL < LC_VAL ? RC_INDEX : LC_INDEX);
        } catch (NoLeftChildE e) {
            try {
                return getRightChildIndex(i);
            } catch (NoRightChildE e2) {
                throw new NoLeftChildE();
            }
        } catch (NoRightChildE e) {
            return getLeftChildIndex(i);
        }
=======

        int leftChildIndex = getLeftChildIndex(i);
        try {
            int rightChildIndex = getRightChildIndex(i);
            if(elems.get(leftChildIndex).getValue()< elems.get(rightChildIndex).getValue()){
                return leftChildIndex;
            }else{
                return rightChildIndex;
            }
        } catch(NoRightChildE e){
            return leftChildIndex;
        }

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    void moveDown(int i) {
        try {
<<<<<<< HEAD
            final int MIN_C_INDEX = minChildIndex(i);
            final int P_VALUE = elems.get(i).getValue();
            final int MIN_C_VALUE = elems.get(MIN_C_INDEX).getValue();

            if(P_VALUE > MIN_C_VALUE) {
                swap(i, MIN_C_INDEX);
                moveDown(MIN_C_INDEX);
            }
        } catch (NoLeftChildE e) {
            return;
=======
            int minChildIndex = minChildIndex(i);
            if(elems.get(minChildIndex).getValue() < elems.get(i).getValue()) {
                swap(i, minChildIndex);
                moveDown(minChildIndex);
            }
        } catch (NoLeftChildE noLeftChildE) {

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
        }
    }

    /**
     * The minimum is at location 0. To remove it we take the last element
     * in the array and move it to location 0 and then recursively apply
     * moveDown.
     */
    Item extractMin() {
<<<<<<< HEAD
        Item smallest = elems.get(0);

        if(size == 1) {
            elems.remove(0);
            size -= 1;
            return smallest;
        }

        elems.set(0, elems.get(size - 1));
        elems.remove(size - 1);
        size -= 1;
        moveDown(0);
        return smallest;
=======
        // TODO
        Item min = elems.get(0);
        int lastElementIndex = size-1;
        swap(lastElementIndex,0);
        elems.remove(size-1);
        size--;
        moveDown(0);

        return min;
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }


    public String toString() {
        return getElems().toString();
    }
}
