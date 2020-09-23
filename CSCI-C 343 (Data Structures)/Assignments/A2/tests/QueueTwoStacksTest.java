import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTwoStacksTest {

    QueueList<Integer> empty, single, triple, large;

    @Before
    public void setUp() throws Exception {
        empty = new QueueList<>();
        single = new QueueList<>();
        triple = new QueueList<>();
        large = new QueueList<>();

        single.offer(1);

        triple.offer(1);
        triple.offer(2);
        triple.offer(3);

        for(int i = 0; i < 1000; i++) {
            large.offer(i);
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
            empty.poll();
        } catch (NoSuchElementE error) {
            emptyExpected = true;
        }

        try {
            triple.clear();
            triple.poll();
        } catch (NoSuchElementE error) {
            triExpected = true;
        }

        try {
            large.clear();
            large.poll();
        } catch (NoSuchElementE error) {
            largeExpected = true;
        }

        assertTrue(emptyExpected);
        assertTrue(triExpected);
        assertTrue(largeExpected);
        assertEquals(0, large.size());
    }

    @Test
    public void offer() throws Exception {
        reset();

        empty.offer(0);
        empty.offer(200);
        empty.offer(20);

        assertEquals((Object)0, empty.remove());
        assertEquals((Object)200, empty.remove());
        assertEquals((Object)20, empty.remove());
    }

    @Test
    public void poll() throws Exception {
        reset();

        assertEquals((Object)1, single.poll());
        assertEquals((Object)1, triple.poll());
        assertEquals((Object)0, large.poll());
        assertEquals((Object)0, large.poll());
    }

    @Test
    public void remove() throws Exception {
        reset();

        assertEquals((Object)1, single.remove());
        assertEquals((Object)1, triple.remove());
        assertEquals((Object)0, large.remove());
        assertEquals((Object)1, large.remove());
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