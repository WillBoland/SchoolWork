// Complete the implementation of this class

class LinkedList<E> implements LinkedListI<E> {
    //--------------------
    //MARK: Instance Variables
    private List<E> elements;

    //--------------------
    //MARK: Constructors
    //----------------------------------------------------------------------------
    /**
     * Constructs a new LinkedList
     */
    LinkedList() {
        this.elements = new EmptyL<>();
    }

    //--------------------
    //MARK: Public Functions
    //----------------------------------------------------------------------------
    /**
     * removes all elements from the list
     * expected O(1)
     */
    public void clear() {
        this.elements = new EmptyL<>();
    }

    //----------------------------------------------------------------------------
    /**
     * returns the number of elements in the current list
     * @return number of elements in the list
     */
    public int size() {
        return elements.length();
    }

    //----------------------------------------------------------------------------
    /**
     * updates the current list by adding the given element to the front
     * expected O(1)
     * @param elem the element to add to the front
     */
    public void addFirst(E elem) {
        this.elements = new NodeL<>(elem, this.elements);
    }

    //----------------------------------------------------------------------------
    /**
     * updates the current list by adding the given element to the end
     * expected O(n)
     * @param elem the element to add to the front
     */
    public void addLast(E elem) {
        this.elements = this.elements.addLast(elem);
    }

    //----------------------------------------------------------------------------
    /**
     * returns but does not remove the element at the front of the current list
     * expected O(1)
     * @return the element at the front of the list
     * @throws NoSuchElementE no element to return; list is empty
     */
    public E getFirst() throws NoSuchElementE {
        return this.elements.getFirst();
    }

    //----------------------------------------------------------------------------
    /**
     * returns but does not remove the element at the end of the current list
     * @return the element at the end of the list
     * @throws NoSuchElementE no element to return; list is empty
     */
    public E getLast() throws NoSuchElementE {
        return this.elements.getLast();
    }

    //----------------------------------------------------------------------------
    /**
     * updates the current list by removing the element at the front
     * @return the element that we removed from the front
     * @throws NoSuchElementE
     */
    public E removeFirst() throws NoSuchElementE {
        E firstElem = this.elements.getFirst();
        this.elements = this.elements.getRest();
        return firstElem;
    }
}