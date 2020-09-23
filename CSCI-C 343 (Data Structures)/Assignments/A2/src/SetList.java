// Complete the implementation of this class

class SetList<E> implements SetI<E> {
    //--------------------
    //MARK: Instance Variables
    //----------------------------------------------------------------------------
    private List<E> elements;

    //--------------------
    //MARK: Constructors
    //----------------------------------------------------------------------------
    /**
     * Creates an empty Set
     */
    SetList () {
        this.elements = new EmptyL<>();
    }

    //----------------------------------------------------------------------------
    /**
     * removes all elements from the current set
     */
    public void clear() {
        this.elements = new EmptyL<>();
    }

    //----------------------------------------------------------------------------
    /**
     * checks if the current set is empty
     * @return
     */
    public boolean isEmpty() {
        return this.elements.isEmpty();
    }

    //----------------------------------------------------------------------------
    /**
     * updates the current set by adding the given element (if not present)
     * can do better than O(n) but not required for this assignment
     * @param elem
     */
    public void add(E elem) {
        if(!this.elements.contains(elem)) {
            this.elements = new NodeL<>(elem, this.elements);
        }
    }

    //----------------------------------------------------------------------------
    /**
     * checks if the current set contains the given element
     * can do better than O(n) but not required for this assignment
     * @param elem
     * @return
     */
    public boolean contains(E elem) {
        return this.elements.contains(elem);
    }

    //----------------------------------------------------------------------------
    /**
     * returns the number of elements in the current set
     * @return
     */
    public int size() {
        return this.elements.length();
    }
}