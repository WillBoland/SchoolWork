import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.BiFunction;

// -------------------------------------------------------

/**
 *
 * The abstract class for the four hash tables we will implement. The
 * file HashTableTest has four test cases that should produce the same
 * information as Figures 5.5, 5.11, 5.13, and 5.18 in the book. You
 * should implement many more test cases!!!
 *
 */
abstract class HashTable {
    abstract int getCapacity();
    abstract void setCapacity(int capacity);

    abstract void insert (int key);
    abstract void delete (int key);
    abstract boolean search (int key);

    abstract void rehash ();
}

// -------------------------------------------------------

/**
 *
 * An implementation of a hash table that uses separate chaining. The
 * constructor should take one arguments 'hf' of type HashFunction.
 * The bound should be extracted from the HashFunction and an ArrayList
 * of that bound should be created in the constructor. Each
 * entry in that ArrayList should be initialized to an empty linked list.
 * The three methods to insert, delete, and search
 * should be implemented as described in the book. You should also
 * implement a method rehash that resizes the hash table to the next prime
 * after doubling the capacity.
 */
class HashSeparateChaining extends HashTable {
    // finish the implementation and remove the abstract annotation

    //--------------------
    //MARK: Private Variables
    private int capacity;
    private int size;
    private HashFunction hf;
    private ArrayList<LinkedList<Integer>> chains;


    //--------------------
    //MARK: Initializers
    //----------------------------------------------------------------------------
    HashSeparateChaining(HashFunction hf) {
        this.capacity = hf.getBound();
        this.size = 0;
        this.hf = hf;
        this.chains = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++)
            chains.add(i, new LinkedList<>());
    }

    //--------------------
    //MARK: Public Functions
    //----------------------------------------------------------------------------
    public String toString() {
        String result = "";
        for (int i = 0; i < capacity; i++) {
            result += "chain[" + i + "] = ";
            result += chains.get(i).toString();
            result += "\n";
        }
        return result;
    }

    //----------------------------------------------------------------------------
    int getCapacity() {
        return this.capacity;
    }

    //----------------------------------------------------------------------------
    void setCapacity(int capacity) {
        this.capacity = capacity;
        this.hf.setBound(capacity);
    }

    //----------------------------------------------------------------------------
    void insert(int key) {
        int index = this.hf.apply(key);
        this.chains.get(index).add(key);
        this.size += 1;
    }

    //----------------------------------------------------------------------------
    void delete(int key) {
        int index = this.hf.apply(key);
        if (this.chains.get(index).contains(key)) {
            this.chains.get(index).remove((Integer) key);
            this.size -= 1;
        }
    }

    //----------------------------------------------------------------------------
    boolean search(int key) {
        try {
            return this.chains.get(hf.apply(key)).contains(key);
        } catch (IndexOutOfBoundsException error) {
            return false;
        }
    }

    //----------------------------------------------------------------------------
    void rehash() {
        int nextPrime = new BigInteger(Integer.toString(capacity * 2)).nextProbablePrime().intValue();
        this.capacity = nextPrime;
        this.hf.setBound(this.capacity);

        ArrayList<LinkedList<Integer>> rehashedChains = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++)
            rehashedChains.add(i, new LinkedList<>());

        for (LinkedList<Integer> list : chains) {
            for (Integer element : list) {
                rehashedChains.get(hf.apply(element)).add(element);
            }
        }
        this.chains = rehashedChains;
    }
}

// -------------------------------------------------------

/**
 *
 * Before implementing the next three variants of hash tables, we will
 * implement a small class hierarchy of hash tables entries. There are
 * three kinds of entries: an entry that contains an 'int' value, an
 * entry that is empty and hence available, and an entry that is
 * marked as deleted. A deleted entry is available for insertions but
 * cannot be marked as empty. See the book for details.
 *
 */

abstract class Entry {
    static Entry EMPTY = new Empty();
    static Entry DELETED = new Deleted();
    abstract boolean available ();
}

class Empty extends Entry {
    boolean available () { return true; }
    public String toString () { return ""; }
}

class Deleted extends Entry {
    boolean available () { return true; }
    public String toString () { return "X"; }
}

class Value extends Entry {
    private int i;
    Value (int i) { this.i = i; }
    int get () { return this.i; }
    boolean available () { return false; }
    public String toString () { return String.valueOf(this.i); }
}

// -------------------------------------------------------

/**
 *
 * This class, although abstract, will have a constructor and an
 * implementation of each of the three methods: insert, delete, and
 * search.
 *
 * The constructor should take two arguments: an
 * argument 'hf' of type HashFunction, and an argument 'f' of type
 * BiFunction<Integer,Integer,Integer>. The constructor should create
 * an ArrayList of the given size and initialize each slot in that
 * array with an Empty entry. The constructor should also define a
 * BiFunction<Integer,Integer,Integer> called 'dhf' as follows:
 *
 *   this.dhf = (key,index) -> (hf.apply(key) + f.apply(key,index)) % size;
 *
 * This auxiliary hash function applies the regular hash function 'hf'
 * and then modifies the result using the BiFunction 'f' that depends
 * on the current index in the hash table. The subclasses for linear
 * probing, quadratic probing, and double hashing, will each pass an
 * appropriate function 'f' to control the auxiliary function.
 *
 * Each of the methods insert, delete, and search takes a value 'key'
 * to process. Each of the methods will involve a loop that iterates
 * over all the positions in the ArrayList. At iteration 'i', an
 * integer position is calculated using:
 *
 *   int h = dhf.apply(key,i)
 *
 * For insert: if the position 'h' is available then the value 'key'
 * is stored.
 *
 * For delete: if position 'h' has an entry of kind 'Value' and if the
 * stored integer is identical to 'key' then the entry is marked as
 * deleted.
 *
 * For search: if position 'h' is Empty then the item is not found. If
 * position 'h' has an entry of kind 'Value' and if the stored value
 * is identical to 'key' then the item is found.
 *
 */

abstract class HashTableAux extends HashTable {
    //--------------------
    //MARK: Variables
    private int capacity;
    private HashFunction hf;
    private BiFunction<Integer,Integer,Integer> f;
    private BiFunction<Integer,Integer,Integer> dhf;
    private ArrayList<Entry> slots;

    //--------------------
    //MARK: Initializers
    //----------------------------------------------------------------------------

    /**
     * The constructor should take two arguments: an
     * argument 'hf' of type HashFunction, and an argument 'f' of type
     * BiFunction<Integer,Integer,Integer>. The constructor should create
     * an ArrayList of the given size and initialize each slot in that
     * array with an Empty entry. The constructor should also define a
     * BiFunction<Integer,Integer,Integer> called 'dhf' as follows:
     *   this.dhf = (key,index) -> (hf.apply(key) + f.apply(key,index)) % size;
     */
    HashTableAux (HashFunction hf, BiFunction<Integer,Integer,Integer> f) {
        this.capacity = hf.getBound();
        this.hf = hf;
        this.f = f;
        this.dhf = (key, index) -> Math.floorMod(hf.apply(key) + f.apply(key, index), capacity);
        this.slots = new ArrayList<>(capacity);
        for (int i = 0; i< capacity; i++)
            this.slots.add(i, Entry.EMPTY);
    }

    //--------------------
    //MARK: Public Functions
    //----------------------------------------------------------------------------
    /**
     * Inserts the given key into the HashTable. Rehashes if cannot insert, then inserts.
     * @param key the int to add to our HashTable
     */
    void insert(int key) {
        for (int i=0; i<capacity; i++) {
            int pos = dhf.apply(key,i);
            if (slots.get(pos).available()) {
                slots.set(pos, new Value(key));
                return;
            }
        }
        rehash();
        insert(key);
    }

    //----------------------------------------------------------------------------
    /**
     * Deletes the given key from our HashTable.
     * @param key the int to delete
     */
    void delete(int key) {
        for (int i=0; i<capacity; i++) {
            int pos = dhf.apply(key,i);
            Value val = new Value(0);

            if(slots.get(pos).getClass() == Empty.class)
                return;

            if(slots.get(pos).getClass() == Value.class)
                val = (Value)slots.get(pos);

            if (!slots.get(pos).available() && key == val.get()) {
                slots.set(pos, Entry.DELETED);
                return;
            }
        }
    }

    //----------------------------------------------------------------------------
    /**
     * Searches for the given key in our HashTable.
     * @param key the int to search for
     * @return true if key was in HashTable; false otherwise
     */
    boolean search(int key) {
        for (int i=0; i<capacity; i++) {
            int pos = dhf.apply(key,i);
            Value val = new Value(0);

            if(slots.get(pos).getClass() == Empty.class)
                return false;

            if(slots.get(pos).getClass() == Value.class)
                val = (Value)slots.get(pos);

            if (!slots.get(pos).available() && key == val.get()) {
                return true;
            }
        }
        return false;
    }

    //----------------------------------------------------------------------------
    /**
     * Retrieves the capacity of the HashTable
     * @return the capacity of the HashTable
     */
    int getCapacity() {
        return this.capacity;
    }

    //----------------------------------------------------------------------------
    /**
     * Sets the capacity of the HashTable to the given input
     * @param capacity capacity to set HashTable size/capacity to
     */
    void setCapacity(int capacity) {
        this.capacity = capacity;
        this.hf.setBound(this.capacity);
    }

    //----------------------------------------------------------------------------
    /**
     * Rehashes the hash table by doubling the capacity to the next prime.
     * Then, rehashes all the entries to their new positions.
     */
    void rehash() {
        //get next prime closest to double the size
        int nextPrime = new BigInteger(Integer.toString(capacity * 2)).nextProbablePrime().intValue();
        this.capacity = nextPrime;
        this.hf.setBound(this.capacity);    //set new  bound to handle new capacity

        ArrayList<Entry> oldSlots = slots; //preserve original

        //set our slots all to empty
        slots = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++)
            slots.add(i, Entry.EMPTY);

        //loop through old slots, insert each value we encounter into slots. Let insert handle collisions
        for(Entry ent: oldSlots) {
            if(ent.getClass() == Value.class) {
                Value val = (Value)ent;
                insert(val.get());
            }
        }
    }

    //----------------------------------------------------------------------------
    public String toString() {
        String result = "";
        for (int i = 0; i< capacity; i++) {
            result += "entry[" + i + "] = ";
            result += slots.get(i).toString();
            result += "\n";
        }
        return result;
    }
}

// -------------------------------------------------------



class HashLinearProbing extends HashTableAux {
    public HashLinearProbing(HashFunction hf) {
        super(hf, (key, iteration) -> iteration);
    }
}


// -------------------------------------------------------


class HashQuadProbing extends HashTableAux {
    public HashQuadProbing(HashFunction hf) {
        super(hf, (key, iteration) -> iteration * iteration);
    }
}


// -------------------------------------------------------


class HashDouble extends HashTableAux {
    public HashDouble(HashFunction hf, HashFunction hf2) {
        super(hf, (key, iteration) -> iteration * hf2.apply(key));
    }
}


// -------------------------------------------------------
