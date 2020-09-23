import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

public class DynamicArray<E> implements StackI<E>, QueueI<E>, DequeI<E> {
    //--------------------
    //MARK: Instance Variables
    //----------------------------------------------------------------------------
    private Optional<E>[] elements;
    private int initialCapacity, capacity, front, back, size;
    //
    // data stored in locations:
    // front+1, front+2, ... back-2, back-1 (all mod capacity)
    //
    // common cases:
    // front points to an empty location
    // back points to an empty location
    // adding to front decreases 'front' by 1
    // adding to back increases 'back' by 1
    // removing does the opposite
    //
    // |-------------------------|
    // | 4 5 6 _ _ _ _ _ _ 1 2 3 |
    // |-------------------------|
    //         /\        /\      /\
    //        back      front  capacity
    //

    //--------------------
    //MARK: Initializers
    //----------------------------------------------------------------------------
    /**
     * initializes an empty DynamicArray
     * @param initialCapacity the capacity for the dynamic array initially
     */
    @SuppressWarnings("unchecked")
    DynamicArray (int initialCapacity) {
        this.elements = (Optional<E>[]) Array.newInstance(Optional.class, initialCapacity); //create array of type Optional<E>
        Arrays.fill(this.elements, Optional.empty());   //fill this array with Optional.empty()

        this.initialCapacity = initialCapacity;
        this.capacity = initialCapacity;
        this.front = this.capacity - 1;
        this.back = 0;
        this.size = 0;
    }

    //--------------------
    //MARK: Stack Methods: delegate to dequeue methods
    //----------------------------------------------------------------------------
    /**
     * removes all elements from the current stack
     */
    public void clear() {
        this.elements = (Optional<E>[]) Array.newInstance(Optional.class, initialCapacity); //create array of type Optional<E>
        Arrays.fill(this.elements, Optional.empty());   //fill this array with Optional.empty()

        this.capacity = initialCapacity;
        this.front = this.capacity - 1;
        this.back = 0;
        this.size = 0;
    }

    //----------------------------------------------------------------------------
    /**
     * returns the number of elements in the current stack
     * @return the number of elements
     */
    public int size() {
        return this.size;
    }

    //----------------------------------------------------------------------------
    /**
     * updates the current stack by adding the given element to the top
     * @param item the item to add
     */
    public void push(E item) {
<<<<<<< HEAD
        this.addFirst(item);
=======
	
>>>>>>> bc17e900c22d50e60d3f18b44a1c4637f12fe25d
    }

    //----------------------------------------------------------------------------
    /**
     * eturns but does not remove the element at the top of the stack
     * @return the element at top of stack
     * @throws NoSuchElementE no element found
     */
    public E peek() throws NoSuchElementE {
        return this.getFirst();
    }

    //----------------------------------------------------------------------------
    /**
     * updates the current stack by removing the top element; return it as well
     * @return the element at top of stack
     * @throws NoSuchElementE no element found
     */
    public E pop() throws NoSuchElementE {
        return this.removeFirst();
    }

    //--------------------
    //MARK: Queue Methods: delegate to dequeue methods
    //----------------------------------------------------------------------------
    /**
     * updates the current queue by adding the given element of the end of the queue
     * @param elem the element to add
     */
    public void offer(E elem) {
        this.addFirst(elem);
    }

    //----------------------------------------------------------------------------
    /**
     * returns but does not remove the element at the front of the queue
     * @return the element at front of queue
     * @throws NoSuchElementE no element found
     */
    public E poll() throws NoSuchElementE {
        return this.getLast();
    }

    //----------------------------------------------------------------------------
    /**
     * updates the current queue by removing the element at the front; return it as well
     * @return the element at front of queue
     * @throws NoSuchElementE
     */
    public E remove() throws NoSuchElementE {
        return this.removeLast();
    }


    //--------------------
    //MARK: Dequeue Methods
    //----------------------------------------------------------------------------
    /**
     * updates the current queue by adding the given element to the front of the queue
     * @param elem the element to add to front of queue
     */
    public void addFirst(E elem) {
        if(size == capacity) {
            doubleCapacity();
        }

        this.elements[front] = Optional.of(elem);
        this.front = Math.floorMod(front - 1, capacity);
        this.size += 1;
    }

    //----------------------------------------------------------------------------

    /**
     * updates the current queue by adding the given element to the end of the queue
     * @param elem the element to add to end of queue
     */
    public void addLast(E elem) {
        if(size == capacity) {
            doubleCapacity();
        }

        this.elements[back] = Optional.of(elem);
        this.back = Math.floorMod(back + 1, capacity);
        this.size += 1;
    }

    //----------------------------------------------------------------------------
    /**
     * returns but does not remove the element at the front
     * @return the front element
     * @throws NoSuchElementE
     */
    public E getFirst() throws NoSuchElementE {
        if(this.size == 0)
            throw new NoSuchElementE();

        return elements[Math.floorMod(front + 1, capacity)].get();
    }

    //----------------------------------------------------------------------------
    /**
     * returns but does not remove the element at the end
     * @return the end element
     * @throws NoSuchElementE
     */
    public E getLast() throws NoSuchElementE {
        if(this.size == 0)
            throw new NoSuchElementE();

        return elements[Math.floorMod(back - 1, capacity)].get();
    }

    //----------------------------------------------------------------------------
    /**
     * updates the current deque by removing the element at the front; return it as well
     * @return the front element
     * @throws NoSuchElementE no element found
     */
    public E removeFirst() throws NoSuchElementE {
        if(this.size == 0)
            throw new NoSuchElementE();
        E elem = this.getFirst();
        this.front = Math.floorMod(front + 1, capacity);
        this.elements[front] = Optional.empty();
        this.size -= 1;
        return elem;
    }

    //----------------------------------------------------------------------------
    /**
     * updates the current deque by removing the element at the end; return it as well
     * @return the end element
     * @throws NoSuchElementE
     */
    public E removeLast() throws NoSuchElementE {
        if(this.size == 0)
            throw new NoSuchElementE();
        E elem = this.getLast();
        this.back = Math.floorMod(back - 1, capacity);
        this.elements[back] = Optional.empty();
        this.size -= 1;
        return elem;
    }

    //----------------------------------------------------------------------------
    /**
     * doubles the capacity of the current DynamicArray
     */
    @SuppressWarnings("unchecked")
    void doubleCapacity() {
        Optional<E>[] newElements = (Optional<E>[]) Array.newInstance(Optional.class, capacity * 2); //create array of type Optional<E>
        Arrays.fill(newElements, Optional.empty());   //fill this array with Optional.empty()
        
        for(int offset = 0; offset < capacity; offset += 1) {
            newElements[offset] = elements[Math.floorMod(front + offset + 1, capacity)];
        }

        this.elements = newElements;
        this.capacity = 2 * capacity;
        this.front = capacity - 1;
        this.back = capacity - size;
    }

    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    // the following methods are used for debugging and testing;
    // please do not edit them
    Optional<E>[] getElements () { return elements; }

    int getCapacity () { return capacity; }

    int getFront () { return front; }

    int getBack () { return back; }
}
