import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackListTest {

    StackList<Integer> empty, single, triple, large;



    @Before
    public void setUp() throws Exception {
        empty = new StackList<>();
        single = new StackList<>();
        triple = new StackList<>();
        large = new StackList<>();

        single.push(1);
        triple.push(1);
        triple.push(2);
        triple.push(3);

        for(int i = 0; i < 1000; i++) {
            large.push(i);
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
            empty.pop();
        } catch (NoSuchElementE error) {
            emptyExpected = true;
        }

        try {
            triple.clear();
            triple.pop();
        } catch (NoSuchElementE error) {
            triExpected = true;
        }

        try {
            large.clear();
            large.pop();
        } catch (NoSuchElementE error) {
            largeExpected = true;
        }

        assertTrue(emptyExpected);
        assertTrue(triExpected);
        assertTrue(largeExpected);
        assertEquals(0, large.size());
    }

    @Test
    public void empty() {
        reset();
        assertTrue(empty.empty());
        assertFalse(single.empty());
        assertFalse(triple.empty());
        assertFalse(large.empty());
    }

    @Test
    public void peek() throws Exception {
        reset();
        boolean expected = false;
        try {
            empty.peek();
        } catch (NoSuchElementE error) {
            expected = true;
        }
        assertTrue(expected);
        assertEquals((Object)1, single.peek());
        assertEquals((Object)3, triple.peek());
        assertEquals((Object)999, large.peek());
    }

    @Test
    public void pop() throws Exception {
        reset();
        assertEquals((Object)1, single.pop());
        assertEquals((Object)3, triple.pop());
        assertEquals((Object)999, large.pop());

        assertEquals(0, single.size());
        assertEquals(2, triple.size());
        assertEquals(999, large.size());
    }

    @Test
    public void push() throws Exception {
        reset();
        empty.push(20);
        empty.push(30);
        assertEquals((Object)30, empty.pop());
        assertEquals((Object)20, empty.pop());
        assertEquals(0, empty.size());
    }

    @Test
    public void size() {
        reset();

        assertEquals(0, empty.size());
        assertEquals(1, single.size());
        assertEquals(3, triple.size());
        assertEquals(1000, large.size());
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