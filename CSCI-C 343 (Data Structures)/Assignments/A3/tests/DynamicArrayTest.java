import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

public class DynamicArrayTest {
    private DynamicArray<Integer> d, o;

    @Before
    public void setUp() throws Exception {
        d = new DynamicArray<>(5);
        o = new DynamicArray<>(1);
    }

    @After
    public void tearDown() throws Exception {
        d = null;
    }

    //----------------------------------------------------------------------------
    //MARK: Stack Method Tests
    @Test
    public void clear() {
        //Bottom -> Top: 1,2,3,4,5,6,7,8,9,10
        for(int elem = 1; elem < 11; elem += 1) {
            d.push(elem);
        }
        d.clear();
        assertEquals(0, d.size());

        //Bottom -> Top: 1,2,3,4,5,6,7,8,9,10
        for(int elem = 1; elem < 11; elem += 1) {
            o.push(elem);
        }
        o.clear();
        assertEquals(0, o.size());
    }

    @Test
    public void size() {
        assertEquals(0, d.size());
        d.addFirst(100);
        d.addFirst(200);
        d.addFirst(300);
        d.addFirst(400);
        assertEquals(4, d.size());
        try {
            d.removeFirst();
        } catch (Exception e) {

        }
        assertEquals(3, d.size());
        try {
            d.removeFirst();
            d.removeFirst();
            d.removeFirst();
        } catch (Exception e) {

        }
        assertEquals(0, d.size());

        assertEquals(0, d.size());
        o.addFirst(100);
        o.addFirst(200);
        o.addFirst(300);
        o.addFirst(400);
        assertEquals(4, o.size());
        try {
            o.removeFirst();
        } catch (Exception e) {

        }
        assertEquals(3, o.size());
        try {
            o.removeFirst();
            o.removeFirst();
            o.removeFirst();
        } catch (Exception e) {

        }
        assertEquals(0, o.size());
    }

    @Test
    public void push() throws NoSuchElementE {
        //Bottom -> Top: 1,2,3,4,5,6,7,8,9,10
        for(int elem = 1; elem < 11; elem += 1) {
            d.push(elem);
        }
        assertEquals(10, d.size());

        //reverse to check
        for(int expected = 10; expected > 0; expected -= 1) {
            assertEquals((Object)expected, d.pop());
        }
        assertEquals(0, d.size());

        //Bottom -> Top: 1,2,3,4,5,6,7,8,9,10
        for(int elem = 1; elem < 11; elem += 1) {
            o.push(elem);
        }
        assertEquals(10, o.size());

        //reverse to check
        for(int expected = 10; expected > 0; expected -= 1) {
            assertEquals((Object)expected, o.pop());
        }
        assertEquals(0, o.size());
    }

    @Test
    public void peek() throws NoSuchElementE {
        //Bottom -> Top: 1,2,3,4,5,6,7,8,9,10
        for(int elem = 1; elem < 11; elem += 1) {
            d.push(elem);
        }
        assertEquals(10, d.size());

        //peek top element 1000 times
        for(int count = 0; count < 1000; count += 1) {
            assertEquals((Object)10, d.peek());
        }
        assertEquals(10, d.size());

        //Bottom -> Top: 1,2,3,4,5,6,7,8,9,10
        for(int elem = 1; elem < 11; elem += 1) {
            o.push(elem);
        }
        assertEquals(10, o.size());

        //peek top element 1000 times
        for(int count = 0; count < 1000; count += 1) {
            assertEquals((Object)10, o.peek());
        }
        assertEquals(10, o.size());
    }

    @Test
    public void pop() throws NoSuchElementE {
        //Bottom -> Top: 1,2,3,4,5,6,7,8,9,10
        for(int elem = 1; elem < 11; elem += 1) {
            d.push(elem);
        }
        assertEquals(10, d.size());

        //reverse to check
        for(int expected = 10; expected > 0; expected -= 1) {
            assertEquals((Object)expected, d.pop());
        }
        assertEquals(0, d.size());

        //Bottom -> Top: 1,2,3,4,5,6,7,8,9,10
        for(int elem = 1; elem < 11; elem += 1) {
            o.push(elem);
        }
        assertEquals(10, o.size());

        //reverse to check
        for(int expected = 10; expected > 0; expected -= 1) {
            assertEquals((Object)expected, o.pop());
        }
        assertEquals(0, o.size());
    }

    @Test
    public void stackThrowTests() {
        //Bottom -> Top: 1,2,3,4,5,6,7,8,9,10
        boolean pop = false;
        boolean peek = false;
        try {
            d.pop();
        } catch (NoSuchElementE e) {
            pop = true;
        }

        try {
            d.peek();
        } catch (NoSuchElementE e) {
            peek = true;
        }
        assertTrue(pop);
        assertTrue(peek);

        //Bottom -> Top: 1,2,3,4,5,6,7,8,9,10
        boolean pop1 = false;
        boolean peek1 = false;
        try {
            o.pop();
        } catch (NoSuchElementE e) {
            pop1 = true;
        }

        try {
            o.peek();
        } catch (NoSuchElementE e) {
            peek1 = true;
        }
        assertTrue(pop1);
        assertTrue(peek1);
    }

    //----------------------------------------------------------------------------
    //MARK: Queue Method Tests

    @Test
    public void offer() throws NoSuchElementE {
        //Back 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 Front
        for(int elem = 1; elem < 11; elem += 1) {
            d.offer(elem);
        }
        assertEquals(10, d.size());

        //same way to check
        for(int expected = 1; expected < 11; expected += 1) {
            assertEquals((Object)expected, d.remove());
        }
        assertEquals(0, d.size());

        for(int elem = 1; elem < 11; elem += 1) {
            o.offer(elem);
        }
        assertEquals(10, o.size());

        //same way to check
        for(int expected = 1; expected < 11; expected += 1) {
            assertEquals((Object)expected, o.remove());
        }
        assertEquals(0, o.size());
    }

    @Test
    public void poll() throws NoSuchElementE {
        //Back 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 Front
        for(int elem = 1; elem < 11; elem += 1) {
            d.offer(elem);
        }
        assertEquals(10, d.size());

        //same way to check
        for(int expected = 1; expected < 11; expected += 1) {
            assertEquals((Object)1, d.poll());
        }
        assertEquals(10, d.size());

        //Back 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 Front
        for(int elem = 1; elem < 11; elem += 1) {
            o.offer(elem);
        }
        assertEquals(10, o.size());

        //same way to check
        for(int expected = 1; expected < 11; expected += 1) {
            assertEquals((Object)1, o.poll());
        }
        assertEquals(10, o.size());
    }

    @Test
    public void throwTest() {
        boolean result = false;
        try {
            d.removeFirst();
        } catch (NoSuchElementE e) {
            result = true;
        }
        assertTrue(result);
    }

    @Test
    public void remove() throws NoSuchElementE {
        //Back 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 Front
        for(int elem = 1; elem < 11; elem += 1) {
            d.offer(elem);
        }
        assertEquals(10, d.size());

        //same way to check
        for(int expected = 1; expected < 11; expected += 1) {
            assertEquals((Object)expected, d.remove());
        }
        assertEquals(0, d.size());

        //Back 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 Front
        for(int elem = 1; elem < 11; elem += 1) {
            o.offer(elem);
        }
        assertEquals(10, o.size());

        //same way to check
        for(int expected = 1; expected < 11; expected += 1) {
            assertEquals((Object)expected, o.remove());
        }
        assertEquals(0, o.size());
    }

    //----------------------------------------------------------------------------
    //MARK: Deque Method Tests
    @Test
    public void addFirst() throws NoSuchElementE {
        o.addFirst(1);
        assertEquals(1, o.size());
        o.addFirst(2);
        assertEquals(2, o.size());
        o.clear();

        for(int count = 0; count < 10000; count += 1) {
            o.addFirst(count);
        }
        assertEquals(10000, o.size());

        assertEquals((Integer)9999, o.removeFirst());
    }

    @Test
    public void addLast() throws NoSuchElementE {
        o.addLast(1);
        assertEquals(1, o.size());
        o.addLast(2);
        assertEquals(2, o.size());
        o.clear();

        for(int count = 0; count < 10000; count += 1) {
            o.addLast(count);
        }
        assertEquals(10000, o.size());

        assertEquals((Integer)9999, o.removeLast());
    }

    @Test
    public void getFirst() throws NoSuchElementE {
        for(int elem = 0; elem < 10000; elem += 1) {
            o.addFirst(elem);
        }

        Integer expected = 9999;
        //asserts
        for(int elem = 0; elem < 10000; elem += 1) {
            assertEquals(expected, o.getFirst());
        }
        assertEquals(10000, o.size());
    }

    @Test
    public void getLast() throws NoSuchElementE {
        for(int elem = 0; elem < 10000; elem += 1) {
            o.addLast(elem);
        }

        Integer expected = 9999;
        //asserts
        for(int elem = 0; elem < 10000; elem += 1) {
            assertEquals(expected, o.getLast());
        }
        assertEquals(10000, o.size());
    }

    @Test
    public void removeFirst() throws NoSuchElementE {
        d.addLast(1);
        d.addLast(2);
        d.addFirst(3);
        d.addLast(4);
        d.addFirst(5);
        assertEquals(5, (int) d.removeFirst());
        assertEquals(3, (int) d.removeFirst());
        assertEquals(1, (int) d.removeFirst());
        assertEquals(2, (int) d.removeFirst());
        assertEquals(4, (int) d.removeFirst());
        assertEquals(0, d.size());
        d.addLast(5);
        d.addLast(4);
        d.addFirst(3);
        d.addLast(2);
        d.addFirst(1);
        assertEquals(1, (int) d.removeFirst());
        assertEquals(3, (int) d.removeFirst());
        assertEquals(5, (int) d.removeFirst());
        assertEquals(4, (int) d.removeFirst());
        assertEquals(2, (int) d.removeFirst());
    }

    @Test
    public void removeLast() throws NoSuchElementE {
        d.addLast(1);
        d.addLast(2);
        d.addLast(3);
        d.addLast(4);
        d.addLast(5);
        assertEquals(5, (int) d.removeLast());
        assertEquals(4, (int) d.removeLast());
        assertEquals(3, (int) d.removeLast());
        assertEquals(2, (int) d.removeLast());
        assertEquals(1, (int) d.removeLast());
        assertEquals(0, d.size());
        d.addLast(5);
        d.addLast(4);
        d.addLast(3);
        d.addLast(2);
        d.addLast(1);
        d.addLast(-200);
        assertEquals(-200, (int) d.removeLast());
        assertEquals(1, (int) d.removeLast());
        assertEquals(2, (int) d.removeLast());
        assertEquals(3, (int) d.removeLast());
        assertEquals(4, (int) d.removeLast());
        assertEquals(5, (int) d.removeLast());
    }

    @Test
    public void doubleSize() {
        //one size
        assertEquals(0, o.size());
        o.addFirst(1);
        o.addFirst(2);
        o.addFirst(3);
        assertEquals(4, o.getCapacity());

        for(int elem = 1; elem < 1000; elem +=1) {
            d.addFirst(elem);
        }
        assertEquals(1280, d.getCapacity());

        o.clear();
        for(int elem = 1; elem < 1000; elem +=1) {
            o.addFirst(elem);
        }
        assertEquals(1024, o.getCapacity()); //1024 (powers of 2)
    }

    @Test
    public void dequeNoResize() throws NoSuchElementE {
        assertEquals(0, d.size());
        d.addFirst(1);
        d.addFirst(2);
        d.addFirst(3);
        assertEquals(3, (int) d.removeFirst());
        assertEquals(1, (int) d.removeLast());
        assertEquals(2, (int) d.removeLast());
        assertEquals(0, d.size());
        d.addLast(1);
        d.addLast(2);
        d.addFirst(3);
        d.addLast(4);
        d.addFirst(5);
        assertEquals(5, (int) d.removeFirst());
        assertEquals(3, (int) d.removeFirst());
        assertEquals(1, (int) d.removeFirst());
        assertEquals(2, (int) d.removeFirst());
        assertEquals(4, (int) d.removeFirst());
        assertEquals(0, d.size());
        d.addLast(5);
        d.addLast(4);
        d.addFirst(3);
        d.addLast(2);
        d.addFirst(1);
        assertEquals(1, (int) d.removeFirst());
        assertEquals(3, (int) d.removeFirst());
        assertEquals(5, (int) d.removeFirst());
        assertEquals(4, (int) d.removeFirst());
        assertEquals(2, (int) d.removeFirst());
        d.addFirst(3);
        d.addFirst(2);
        d.addFirst(1);
        assertEquals(1, (int) d.removeFirst());
        assertEquals(3, (int) d.removeLast());
        assertEquals(2, (int) d.removeLast());
        assertEquals(0, d.size());

        //one size
        assertEquals(0, o.size());
        o.addFirst(1);
        o.addFirst(2);
        o.addFirst(3);
        assertEquals(3, (int) o.removeFirst());
        assertEquals(1, (int) o.removeLast());
        assertEquals(2, (int) o.removeLast());
        assertEquals(0, o.size());
        o.addLast(1);
        o.addLast(2);
        o.addFirst(3);
        o.addLast(4);
        o.addFirst(5);
        assertEquals(5, (int) o.removeFirst());
        assertEquals(3, (int) o.removeFirst());
        assertEquals(1, (int) o.removeFirst());
        assertEquals(2, (int) o.removeFirst());
        assertEquals(4, (int) o.removeFirst());
        assertEquals(0, o.size());
        o.addLast(5);
        o.addLast(4);
        o.addFirst(3);
        o.addLast(2);
        o.addFirst(1);
        assertEquals(1, (int) o.removeFirst());
        assertEquals(3, (int) o.removeFirst());
        assertEquals(5, (int) o.removeFirst());
        assertEquals(4, (int) o.removeFirst());
        assertEquals(2, (int) o.removeFirst());
        o.addFirst(3);
        o.addFirst(2);
        o.addFirst(1);
        assertEquals(1, (int) o.removeFirst());
        assertEquals(3, (int) o.removeLast());
        assertEquals(2, (int) o.removeLast());
        assertEquals(0, o.size());

    }

    @Test
    public void dequeResize() throws NoSuchElementE {
        //1,2,4 || 6, 5, 3
        d.addLast(1);
        d.addLast(2);
        d.addFirst(3);
        d.addLast(4);
        d.addFirst(5);
        d.addFirst(6);
        assertEquals(10, d.getCapacity());
        assertEquals(6, (int) d.removeFirst());
        assertEquals(5, (int) d.removeFirst());
        assertEquals(3, (int) d.removeFirst());
        assertEquals(1, (int) d.removeFirst());
        assertEquals(2, (int) d.removeFirst());
        assertEquals(4, (int) d.removeFirst());
        d.addLast(6);
        d.addLast(5);
        d.addFirst(4);
        d.addLast(3);
        d.addFirst(2);
        d.addFirst(1);
        assertEquals(10, d.getCapacity());
        assertEquals(1, (int) d.removeFirst());
        assertEquals(2, (int) d.removeFirst());
        assertEquals(4, (int) d.removeFirst());
        assertEquals(6, (int) d.removeFirst());
        assertEquals(5, (int) d.removeFirst());
        assertEquals(3, (int) d.removeFirst());

        d.clear();
        for(int elem = 1; elem < 1000; elem +=1) {
            d.addFirst(elem);
        }
        assertEquals(1280, d.getCapacity()); //5 * 2 a lot of times

        //ONE SIZE
        o.addLast(1);
        o.addLast(2);
        o.addFirst(3);
        o.addLast(4);
        o.addFirst(5);
        o.addFirst(6);
        assertEquals(8, o.getCapacity());
        assertEquals(6, (int) o.removeFirst());
        assertEquals(5, (int) o.removeFirst());
        assertEquals(3, (int) o.removeFirst());
        assertEquals(1, (int) o.removeFirst());
        assertEquals(2, (int) o.removeFirst());
        assertEquals(4, (int) o.removeFirst());
        o.addLast(6);
        o.addLast(5);
        o.addFirst(4);
        o.addLast(3);
        o.addFirst(2);
        o.addFirst(1);
        assertEquals(8, o.getCapacity());
        assertEquals(1, (int) o.removeFirst());
        assertEquals(2, (int) o.removeFirst());
        assertEquals(4, (int) o.removeFirst());
        assertEquals(6, (int) o.removeFirst());
        assertEquals(5, (int) o.removeFirst());
        assertEquals(3, (int) o.removeFirst());

        o.clear();
        for(int elem = 1; elem < 1000; elem +=1) {
            o.addFirst(elem);
        }
        assertEquals(1024, o.getCapacity()); //1024 (powers of 2)

    }
}
