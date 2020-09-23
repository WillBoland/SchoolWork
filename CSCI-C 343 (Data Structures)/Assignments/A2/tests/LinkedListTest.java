import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest {

    LinkedList<Integer> empty, single, triple, large;

    @Before
    public void setUp() throws Exception {
        empty = new LinkedList<>();
        single = new LinkedList<>();
        triple = new LinkedList<>();
        large = new LinkedList<>();

        single.addFirst(1);

        triple.addFirst(1);
        triple.addLast(2);
        triple.addLast(3);

        for(int i = 0; i < 1000; i++) {
            large.addLast(i);
        }
    }

    @After
    public void tearDown() throws Exception {
        empty = null;
        single = null;
        triple = null;
        large = null;
    }

    @Test
    public void clear() {
        reset();

        boolean emptyExpected = false;
        boolean triExpected = false;
        boolean largeExpected = false;

        try {
            empty.clear();
            empty.getFirst();
        } catch (NoSuchElementE error) {
            emptyExpected = true;
        }

        try {
            triple.clear();
            triple.getFirst();
        } catch (NoSuchElementE error) {
            triExpected = true;
        }

        try {
            large.clear();
            large.getFirst();
        } catch (NoSuchElementE error) {
            largeExpected = true;
        }

        assertTrue(emptyExpected);
        assertTrue(triExpected);
        assertTrue(largeExpected);
        assertEquals(0, large.size());
    }

    @Test
    public void size() {
        reset();

        assertEquals(0, empty.size());
        assertEquals(1, single.size());
        assertEquals(3, triple.size());
        assertEquals(1000, large.size());
    }

    @Test
    public void addFirst() throws Exception {
        reset();

        empty.addFirst(0);
        single.addFirst(100);
        triple.addFirst(300);
        large.addFirst(10);

        assertEquals((Object)0, empty.getFirst());
        assertEquals((Object)100, single.getFirst());
        assertEquals((Object)300, triple.getFirst());
        assertEquals((Object)10, large.getFirst());
    }

    @Test
    public void addLast() throws Exception {
        reset();

        empty.addLast(10);
        single.addLast(200);
        triple.addLast(300);
        large.addLast(20);

        assertEquals(1, empty.size());
        assertEquals(2, single.size());
        assertEquals(4, triple.size());
        assertEquals(1001, large.size());
        assertEquals((Object)10, empty.getLast());
        assertEquals((Object)20, large.getLast());

        reset();
    }

    @Test(expected = NoSuchElementE.class)
    public void getFirstThrow() throws NoSuchElementE {
        empty.getFirst();
    }

    @Test
    public void getFirst() throws Exception {
        reset();
        assertEquals((Object)1, single.getFirst());
        assertEquals((Object)1, triple.getFirst());
        assertEquals((Object)0, large.getFirst());
        single.addFirst(200);
        assertEquals((Object)200, single.getFirst());
    }

    @Test(expected = NoSuchElementE.class)
    public void getLastThrow() throws NoSuchElementE {
        empty.getLast();
    }

    @Test
    public void getLast() throws Exception {
        reset();

        assertEquals((Object)1, single.getLast());
        assertEquals((Object)3, triple.getLast());
        assertEquals((Object)999, large.getLast());
    }

    @Test
    public void removeFirst() throws Exception {
        reset();

        single.removeFirst();
        triple.removeFirst();
        large.removeFirst();
        large.removeFirst();

        assertEquals(0, single.size());
        assertEquals(2, triple.size());
        assertEquals(998, large.size());
        assertEquals((Object)2, large.getFirst());

        reset();
    }

    void reset() {
        //setup because we cleared all the lists
        try {
            setUp();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}