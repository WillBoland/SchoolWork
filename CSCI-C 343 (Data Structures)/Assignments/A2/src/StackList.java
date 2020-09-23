// Complete the implementation of this class

class StackList<E> implements StackI<E> {
    //--------------------
    //MARK: Instance Variables
    //----------------------------------------------------------------------------
    private List<E> elements;

    //--------------------
    //MARK: Constructors
    //----------------------------------------------------------------------------
    /**
     * Creates a new Stack
     */
    StackList() {
        this.elements = new EmptyL<>();
    }

    //--------------------
    //MARK: Public Functions
    //----------------------------------------------------------------------------
    /**
     * removes all elements from the current stack
     * expected O(1)
     */
    public void clear() {
        this.elements = new EmptyL<>();
    }

    //----------------------------------------------------------------------------
    /**
     * checks if the current stack is empty
     * expected O(1)
     * @return true if stack contains no elements; false otherwise
     */
    public boolean empty() {
        return this.elements.isEmpty();
    }

    //----------------------------------------------------------------------------
    /**
     * returns but does not remove the element at the top of the stack
     * expected O(1)
     * @return the element at the top of the stack
     * @throws NoSuchElementE no element found
     */
    public E peek() throws NoSuchElementE {
        return this.elements.getFirst();
    }

    //----------------------------------------------------------------------------

    /**
     * updates the current stack by removing the top element
     * expected O(1)
     * @return
     * @throws NoSuchElementE
     */
    public E pop() throws NoSuchElementE {
        E topElem = this.elements.getFirst();
        this.elements = this.elements.getRest();
        return topElem;
    }

    //----------------------------------------------------------------------------
    /**
     * updates the current stack by adding the given element to the top
     * expected O(1)
     * @param item
     */
    public void push(E item) {
        this.elements = new NodeL<>(item, this.elements);
    }

    //----------------------------------------------------------------------------
    /**
     * returns the number of elements in the current set
     */
    public int size() {
        return elements.length();
    }
}