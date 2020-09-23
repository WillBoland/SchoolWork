//Will Boland
//1/19/2020

/*
 * PLEASE NOTE: Append returns the appended list; does not alter the original list
 */

/**
 * Exception for when calling methods on empty list.
 */
class EmptyListE extends Exception {
    EmptyListE(String message) {
        super(message);
    }
}

/**
 * An abstract class used for implementing a list data structure
 * @param <E> object type for list
 */
abstract class List<E> {
    abstract boolean isEmpty();
    abstract boolean isSingleton();
    abstract E getFirst() throws EmptyListE;
    abstract List<E> getRest() throws EmptyListE;
    abstract E get (int index) throws EmptyListE;
    abstract int length ();
    abstract List<E> append (List<E> other);
    abstract boolean contains (E elem);
}

/**
 * A class that allows an empty list with no data. Is always empty.
 */
class EmptyL<E> extends List<E> {
    private E data;
    private List<E> rest;

    /**
     * Empty list is always empty.
     * @return true
     */
    boolean isEmpty() {
        return true;
    }

    /**
     * empty list can have no lists past it
     * @return false
     */
    boolean isSingleton() {
        return false;
    }

    /**
     * No element due to empty list. Throws error.
     * @throws EmptyListE
     */
    E getFirst() throws EmptyListE {
        throw new EmptyListE("Empty list has no element to get.");
    }

    /**
     * No rest due to empty list. Throws error.
     * @throws EmptyListE
     */
    List<E> getRest() throws EmptyListE {
        throw new EmptyListE("EmptyList does not contain any other lists past it.");
    }

    /**
     * No element to get due to empty list. Throws Error.
     * @throws EmptyListE
     */
    E get(int index) throws EmptyListE {
        throw new EmptyListE("Empty list has no element to get.");
    }

    /**
     * Gives the length of the list. Always 0 for empty list.
     * @return 0
     */
    int length() {
        return 0;
    }

    /**
     * returns the list to append to the empty list
     * @param other list to append
     * @return other
     */
    List<E> append(List<E> other) {
        return other;
    }

    /**
     * Empty List contains no elements. Returns false.
     * @return false
     */
    boolean contains(E elem) {
        return false;
    }
}

/**
 * A class that allows data and other lists.
 */
class NodeL<E> extends List<E> {
    private E data;
    private List<E> rest; //the rest of the list past this point

    NodeL(E data, List rest) {
        this.data = data;
        this.rest = rest;
    }

    /**
     * checks if the list is empty. By definition, this returns false given this is a non-empty list
     * @return true
     */
    boolean isEmpty() {
        return false;
    }

    /**
     * checks if this is the only item in the list
     * @return true if rest is emptyList; false otherwise
     */
    boolean isSingleton() {
        return this.rest.isEmpty();
    }

    /**
     * retrieves the first element in the list at this point
     * @return the first element in the list
     */
    E getFirst() {
        return this.data;
    }

    /**
     * retrieves the rest of the list
     * @return the rest of the list
     */
    List<E> getRest() {
        return this.rest;
    }

    /**
     * retrieves the item at the specified index
     * @param index the index of the element you want to retrieve
     * @throws EmptyListE exception if index is out of bounds
     * @return the element at the specified index
     */
    E get(int index) throws EmptyListE {
        if(index < 0) {
            throw new EmptyListE("Index out of bounds.");
        }

        if(index == 0) {
            return this.data;
        }

        try {
            return this.rest.get(index - 1);
        } catch (EmptyListE error) {
            throw new EmptyListE("Index out of bounds.");
        }
    }

    /**
     * the length of the list
     * @return the length of the list as an int
     */
    int length() {
        return 1 + this.rest.length();
    }

    /**
     * returns an appended version of this list. DOES NOT MODIFY EXISTING LIST.
     * @param other the list to append
     * @return the appended list
     */
    List<E> append(List<E> other) {
        return new NodeL<E>(this.data, this.rest.append(other));
    }

    /**
     * tells whether an element is in a given list or not
     * @param elem the element to search for in the list
     * @return true if element is in list, false otherwise
     */
    boolean contains(E elem) {
        if(this.data.equals(elem)) {
            return true;
        }
        return this.rest.contains(elem);
    }
}