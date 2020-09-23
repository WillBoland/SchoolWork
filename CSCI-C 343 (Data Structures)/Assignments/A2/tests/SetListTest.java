import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SetListTest {
    SetList<Integer> empty, single, triple, large;

    @Before
    public void setUp() throws Exception {
        empty = new SetList<>();
        single = new SetList<>();
        triple = new SetList<>();
        large = new SetList<>();

        single.add(1);

        triple.add(1);
        triple.add(2);
        triple.add(3);

        for(int i = 0; i < 1000; i++) {
            large.add(i);
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

        empty.clear();
        single.clear();
        triple.clear();
        large.clear();

        assertEquals(0, empty.size());
        assertEquals(0, single.size());
        assertEquals(0, triple.size());
        assertEquals(0, large.size());
    }

    @Test
    public void isEmpty() {
        reset();
        assertTrue(empty.isEmpty());
        assertFalse(single.isEmpty());
        assertFalse(triple.isEmpty());
        assertFalse(large.isEmpty());
    }

    @Test
    public void add() {
        reset();

        empty.add(10);
        single.add(200);
        triple.add(300);
        large.add(20);
        large.add(20);
        large.add(20);

        assertEquals(1, empty.size());
        assertEquals(2, single.size());
        assertEquals(4, triple.size());
        assertEquals(1000, large.size());
        assertTrue(large.contains(996));
    }

    @Test
    public void contains() {
        reset();
        empty.add(10);
        single.add(200);
        triple.add(300);
        large.add(20);
        large.add(20);
        large.add(20);

        assertTrue(empty.contains(10));
        assertTrue(single.contains(200));
        assertTrue(large.contains(996));
        assertTrue(triple.contains(300));
        assertTrue(large.contains(0));
    }

    @Test
    public void size() {
        empty.add(10);
        single.add(200);
        triple.add(300);
        large.add(20);
        large.add(20);
        large.add(20);

        assertEquals(1, empty.size());
        assertEquals(2, single.size());
        assertEquals(4, triple.size());
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