// Complete the implementation of this class
// For this implementation, you can make some of the methods in the interface take O(n) time instead of the expected O(1) time


class QueueList<E> implements QueueI<E> {
    //--------------------
    //MARK: Instance Variables
    private List<E> elements;

    //--------------------
    //MARK: Constructors
    //----------------------------------------------------------------------------
    /**
     * Constructs a new Queue
     */
    QueueList() {
        this.elements = new EmptyL<>();
    }

    //--------------------
    //MARK: Public Functions
    //----------------------------------------------------------------------------
    /**
     * removes all elements from the current queue
     * expected O(1)
     */
    public void clear() {
        this.elements = new EmptyL<>();
    }

    //----------------------------------------------------------------------------
    /**
     * updates the current queue by adding the given element of the end of the queue
     * expected O(n)
     * @param elem the element you want to add
     */
    public void offer(E elem) {
        this.elements = this.elements.addLast(elem);
    }

    //----------------------------------------------------------------------------
    /**
     * returns but does not remove the element at the front of the queue
     * expected O(1)
     * @return the element at front of queue
     * @throws NoSuchElementE no element found
     */
    public E poll() throws NoSuchElementE {
        return this.elements.getFirst();
    }

    //----------------------------------------------------------------------------
    /**
     * updates the current queue by removing the element at the front
     * expected O(1)
     * @return the element removed from the front
     * @throws NoSuchElementE no element found to remove
     */
    public E remove() throws NoSuchElementE {
        E frontElem = this.elements.getFirst();
        this.elements = this.elements.getRest();
        return frontElem;
    }

    //----------------------------------------------------------------------------
    /**
     * returns the number of elements in the current queue
     * @return the size of the queue
     */
    public int size() {
        return this.elements.length();
    }
}