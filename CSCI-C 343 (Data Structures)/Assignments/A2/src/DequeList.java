// Complete the implementation of this class

class DequeList<E> implements DequeI<E> {
    //--------------------
    //MARK: Instance Variables
    //----------------------------------------------------------------------------
    private List<E> elements;

    //--------------------
    //MARK: Constructors
    //----------------------------------------------------------------------------
    /**
     * Constructs an empty DequeList
     */
    DequeList () {
        this.elements = new EmptyL<>();
    }

    //--------------------
    //MARK: Public Functions
    //----------------------------------------------------------------------------
    /**
     * removes all elements from the current dequeue
     * expected O(1)
     */
    public void clear() {
        this.elements = new EmptyL<>();
    }

    //----------------------------------------------------------------------------
    /**
     * updates the current queue by adding the given element to the front of the queue
     * ideally expected O(1) but for this assignment not required
     * @param elem the element to add
     */
    public void addFirst(E elem) {
        this.elements = this.elements.addLast(elem);
    }

    //----------------------------------------------------------------------------
    /**
     * updates the current queue by adding the given element to the end of the queue
     * ideally expected O(1) but for this assignment not required
     * @param elem the element to add
     */
    public void addLast(E elem) {
        this.elements = new NodeL<>(elem, this.elements);
    }

    //----------------------------------------------------------------------------
    /**
     * returns but does not remove the element at the front
     * ideally expected O(1) but for this assignment not required
     * @return the element at the front of the deque
     * @throws NoSuchElementE
     */
    public E getFirst() throws NoSuchElementE {
        return this.elements.getLast();
    }

    //----------------------------------------------------------------------------
    /**
     * returns but does not remove the element at the end
     * ideally expected O(1) but for this assignment not required
     * @return the element at the end of the deque
     * @throws NoSuchElementE no element to retrieve
     */
    public E getLast() throws NoSuchElementE {
        return this.elements.getFirst();
    }

    //----------------------------------------------------------------------------
    /**
     * returns the number of elements in the current dequeue
     * @return the size of the Dequeue
     */
    public int size() {
        return this.elements.length();
    }
}