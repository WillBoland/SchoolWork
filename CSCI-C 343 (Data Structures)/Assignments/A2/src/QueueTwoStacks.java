// Complete the implementation of this class
// For this implementation all the methods should take amortized O(1) time

class QueueTwoStacks<E> implements QueueI<E> {
    //--------------------
    //MARK: Instance Variables
    private StackList<E> front, back;
    private int currentSize;

    //--------------------
    //MARK: Constructors
    //----------------------------------------------------------------------------

    /**
     * Constructs a new Queue
     */
    QueueTwoStacks() {
        this.front = new StackList<>();
        this.back = new StackList<>();
        this.currentSize = 0;
    }

    //--------------------
    //MARK: Public Functions
    //----------------------------------------------------------------------------

    /**
     * removes all elements from the current queue
     * expected O(1)
     */
    public void clear() {
        this.front = new StackList<>();
        this.back = new StackList<>();
        this.currentSize = 0;
    }

    //----------------------------------------------------------------------------

    /**
     * updates the current queue by adding the given element of the end of the queue
     * expected O(1)
     *
     * @param elem the element you want to add
     */
    public void offer(E elem) {
        this.front.push(elem);
        this.currentSize += 1;
    }

    //----------------------------------------------------------------------------

    /**
     * returns but does not remove the element at the front of the queue
     * expected O(1)
     *
     * @return the element at front of queue
     * @throws NoSuchElementE no element found
     */
    public E poll() throws NoSuchElementE {
        if (this.back.empty()) {
            while (!this.front.empty()) {
                this.back.push(this.front.pop());
            }
        }
        return this.back.peek();
    }

    //----------------------------------------------------------------------------

    /**
     * updates the current queue by removing the element at the front
     * expected O(1)
     *
     * @return the element removed from the front
     * @throws NoSuchElementE no element found to remove
     */
    public E remove() throws NoSuchElementE {
        if (this.back.empty()) {
            while (!this.front.empty()) {
                this.back.push(this.front.pop());
            }
        }
        this.currentSize -= 1;
        return this.back.pop();
    }

    //----------------------------------------------------------------------------

    /**
     * returns the number of elements in the current queue
     *
     * @return the size of the queue
     */
    public int size() {
        return this.currentSize;
    }
}