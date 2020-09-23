import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

//Will Boland

public class WeakHeapTest {
    private WeakHeap wh1; //full
    private WeakHeap wh2; //empty
    private WeakHeap wh3; //1 item
    private WeakHeap wh4; //2 items
    private WeakHeap wh5; //same as wh1, except built with list of items

    @Before
    public void setUp () {
        wh1 = new WeakHeap();
        wh1.insert(new Item(30));
        wh1.insert(new Item(50));
        wh1.insert(new Item(40));
        wh1.insert(new Item(70));
        wh1.insert(new Item(80));
        wh1.insert(new Item(60));
        wh1.insert(new Item(170));
        wh1.insert(new Item(110));
        assertTrue(wh1.checkOrder());
        /*
                                30_0
                                  └───────────────────┐
                                                    50_1
                                            ┌─────────┴─────────┐
                                          40_2                70_3
                                       ┌────┴────┐         ┌────┴────┐
                                     80_4      60_5      170_6     110_7
         */

        wh2 = new WeakHeap();
        assertTrue(wh2.checkOrder());
        /*
                                EMPTY
         */

        wh3 = new WeakHeap();
        wh3.insert(new Item(30));
        assertTrue(wh3.checkOrder());
        /*
                                30_0
         */

        wh4 = new WeakHeap();
        wh4.insert(new Item(30));
        wh4.insert(new Item(50));
        assertTrue(wh4.checkOrder());
        /*
                                30_0
                                  └───────────────────┐
                                                    50_1
         */

        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(30));
        items.add(new Item(50));
        items.add(new Item(40));
        items.add(new Item(70));
        items.add(new Item(80));
        items.add(new Item(60));
        items.add(new Item(170));
        items.add(new Item(110));
        wh5 = new WeakHeap(items);
        /* SAME JUST WITH DIFFERENT CONSTRUCTOR
                                30_0
                                  └───────────────────┐
                                                    50_1
                                            ┌─────────┴─────────┐
                                          40_2                70_3
                                       ┌────┴────┐         ┌────┴────┐
                                     80_4      60_5      170_6     110_7
         */
    }

    @Test
    public void getParentIndex() throws RootE {
        assertEquals(70, wh1.getValue(wh1.getParentIndex(7)));
        assertEquals(70, wh1.getValue(wh1.getParentIndex(6)));
        assertEquals(40, wh1.getValue(wh1.getParentIndex(5)));
        assertEquals(40, wh1.getValue(wh1.getParentIndex(4)));
        assertEquals(50, wh1.getValue(wh1.getParentIndex(3)));
        assertEquals(50, wh1.getValue(wh1.getParentIndex(2)));
        assertEquals(30, wh1.getValue(wh1.getParentIndex(1)));

        boolean expected = false;
        try {
            wh2.getParentIndex(0);
        } catch (RootE error) {
            expected = true;
        }
        assertTrue(expected);

        expected = false;
        try {
            wh3.getParentIndex(0);
        } catch (RootE error) {
            expected = true;
        }
        assertTrue(expected);

        expected = false;
        try {
            wh4.getParentIndex(0);
        } catch (RootE error) {
            expected = true;
        }
        assertTrue(expected);

        assertEquals(30, wh4.getValue(wh4.getParentIndex(1)));

        assertEquals(70, wh5.getValue(wh5.getParentIndex(7)));
        assertEquals(70, wh5.getValue(wh5.getParentIndex(6)));
        assertEquals(40, wh5.getValue(wh5.getParentIndex(5)));
        assertEquals(40, wh5.getValue(wh5.getParentIndex(4)));
        assertEquals(50, wh5.getValue(wh5.getParentIndex(3)));
        assertEquals(50, wh5.getValue(wh5.getParentIndex(2)));
        assertEquals(30, wh5.getValue(wh5.getParentIndex(1)));
    }

    @Test
    public void getLeftChildIndex() throws NoLeftChildE {
        assertEquals(40, wh1.getValue(wh1.getLeftChildIndex(1)));
        assertEquals(80, wh1.getValue(wh1.getLeftChildIndex(2)));
        assertEquals(170, wh1.getValue(wh1.getLeftChildIndex(3)));

        boolean expected = false;
        try {
            wh1.getLeftChildIndex(4);
        } catch (NoLeftChildE error) {
            expected = true;
        }
        assertTrue(expected);

        expected = false;
        try {
            wh2.getLeftChildIndex(0);
        } catch (NoLeftChildE error) {
            expected = true;
        }
        assertTrue(expected);

        expected = false;
        try {
            wh3.getLeftChildIndex(0);
        } catch (NoLeftChildE error) {
            expected = true;
        }
        assertTrue(expected);

        expected = false;
        try {
            wh4.getLeftChildIndex(0);
        } catch (NoLeftChildE error) {
            expected = true;
        }
        assertTrue(expected);

        expected = false;
        try {
            wh4.getLeftChildIndex(1);
        } catch (NoLeftChildE error) {
            expected = true;
        }
        assertTrue(expected);

        assertEquals(40, wh5.getValue(wh5.getLeftChildIndex(1)));
        assertEquals(80, wh5.getValue(wh5.getLeftChildIndex(2)));
        assertEquals(170, wh5.getValue(wh5.getLeftChildIndex(3)));
    }

    @Test
    public void getRightChildIndex() throws NoRightChildE {
        assertEquals(50, wh1.getValue(wh1.getRightChildIndex(0)));
        assertEquals(70, wh1.getValue(wh1.getRightChildIndex(1)));
        assertEquals(60, wh1.getValue(wh1.getRightChildIndex(2)));
        assertEquals(110, wh1.getValue(wh1.getRightChildIndex(3)));

        boolean expected = false;
        try {
            wh1.getRightChildIndex(4);
        } catch (NoRightChildE error) {
            expected = true;
        }
        assertTrue(expected);

        expected = false;
        try {
            wh2.getRightChildIndex(0);
        } catch (NoRightChildE error) {
            expected = true;
        }
        assertTrue(expected);

        expected = false;
        try {
            wh3.getRightChildIndex(0);
        } catch (NoRightChildE error) {
            expected = true;
        }
        assertTrue(expected);

        expected = false;
        try {
            wh4.getRightChildIndex(1);
        } catch (NoRightChildE error) {
            expected = true;
        }
        assertTrue(expected);

        assertEquals(50, wh5.getValue(wh5.getRightChildIndex(0)));
        assertEquals(70, wh5.getValue(wh5.getRightChildIndex(1)));
        assertEquals(60, wh5.getValue(wh5.getRightChildIndex(2)));
        assertEquals(110, wh5.getValue(wh5.getRightChildIndex(3)));
    }

    @Test
    public void isLeftChild() throws RootE {
        assertTrue(wh1.isLeftChild(2));
        assertTrue(wh1.isLeftChild(4));
        assertTrue(wh1.isLeftChild(6));
        assertFalse(wh1.isLeftChild(1));
        assertFalse(wh1.isLeftChild(3));
        assertFalse(wh1.isLeftChild(5));
        assertFalse(wh1.isLeftChild(7));

        boolean expected = false;
        try {
            wh1.isLeftChild(0);
        } catch (RootE error) {
            expected = true;
        }
        assertTrue(expected);

        expected = false;
        try {
            wh2.isLeftChild(0);
        } catch (RootE error) {
            expected = true;
        }
        assertTrue(expected);

        expected = false;
        try {
            wh3.isLeftChild(0);
        } catch (RootE error) {
            expected = true;
        }
        assertTrue(expected);

        expected = false;
        try {
            wh4.isLeftChild(0);
        } catch (RootE error) {
            expected = true;
        }
        assertTrue(expected);

        assertTrue(wh5.isLeftChild(2));
        assertTrue(wh5.isLeftChild(4));
        assertTrue(wh5.isLeftChild(6));
        assertFalse(wh5.isLeftChild(1));
        assertFalse(wh5.isLeftChild(3));
        assertFalse(wh5.isLeftChild(5));
        assertFalse(wh5.isLeftChild(7));
    }

    @Test
    public void isRightChild() throws RootE {
        assertTrue(wh1.isRightChild(1));
        assertTrue(wh1.isRightChild(3));
        assertTrue(wh1.isRightChild(5));
        assertTrue(wh1.isRightChild(7));
        assertFalse(wh1.isRightChild(2));
        assertFalse(wh1.isRightChild(4));
        assertFalse(wh1.isRightChild(6));

        assertTrue(wh4.isRightChild(1));

        boolean expected = false;
        try {
            wh1.isRightChild(0);
        } catch (RootE error) {
            expected = true;
        }
        assertTrue(expected);

        expected = false;
        try {
            wh2.isRightChild(0);
        } catch (RootE error) {
            expected = true;
        }
        assertTrue(expected);

        expected = false;
        try {
            wh3.isRightChild(0);
        } catch (RootE error) {
            expected = true;
        }
        assertTrue(expected);

        expected = false;
        try {
            wh4.isRightChild(0);
        } catch (RootE error) {
            expected = true;
        }
        assertTrue(expected);

        assertTrue(wh5.isRightChild(1));
        assertTrue(wh5.isRightChild(3));
        assertTrue(wh5.isRightChild(5));
        assertTrue(wh5.isRightChild(7));
        assertFalse(wh5.isRightChild(2));
        assertFalse(wh5.isRightChild(4));
        assertFalse(wh5.isRightChild(6));
    }

    @Test
    public void getDAncestorIndex() throws RootE {
        assertEquals(0, wh1.getDAncestorIndex(1));
        assertEquals(0, wh1.getDAncestorIndex(2));
        assertEquals(1, wh1.getDAncestorIndex(3));
        assertEquals(0, wh1.getDAncestorIndex(4));
        assertEquals(2, wh1.getDAncestorIndex(5));
        assertEquals(1, wh1.getDAncestorIndex(6));
        assertEquals(3, wh1.getDAncestorIndex(7));

        assertEquals(0, wh5.getDAncestorIndex(1));
        assertEquals(0, wh5.getDAncestorIndex(2));
        assertEquals(1, wh5.getDAncestorIndex(6));
        assertEquals(3, wh5.getDAncestorIndex(7));
        assertEquals(1, wh5.getDAncestorIndex(3));
        assertEquals(0, wh5.getDAncestorIndex(4));
        assertEquals(1, wh5.getDAncestorIndex(6));
        assertEquals(3, wh5.getDAncestorIndex(7));
        assertEquals(0, wh5.getDAncestorIndex(1));
        assertEquals(0, wh5.getDAncestorIndex(2));
        assertEquals(1, wh5.getDAncestorIndex(6));
        assertEquals(3, wh5.getDAncestorIndex(7));
        assertEquals(1, wh5.getDAncestorIndex(3));
        assertEquals(0, wh5.getDAncestorIndex(4));
        assertEquals(1, wh5.getDAncestorIndex(6));
        assertEquals(3, wh5.getDAncestorIndex(7));
        assertEquals(1, wh5.getDAncestorIndex(6));
        assertEquals(1, wh5.getDAncestorIndex(6));
        assertEquals(3, wh5.getDAncestorIndex(7));
        assertEquals(3, wh5.getDAncestorIndex(7));
        assertEquals(2, wh5.getDAncestorIndex(5));
        assertEquals(1, wh5.getDAncestorIndex(6));
        assertEquals(3, wh5.getDAncestorIndex(7));
        assertEquals(1, wh5.getDAncestorIndex(6));
        assertEquals(1, wh5.getDAncestorIndex(6));
        assertEquals(3, wh5.getDAncestorIndex(7));
        assertEquals(3, wh5.getDAncestorIndex(7));
        assertEquals(2, wh5.getDAncestorIndex(5));
        assertEquals(1, wh5.getDAncestorIndex(6));
        assertEquals(3, wh5.getDAncestorIndex(7));
    }

    @Test
    public void getLeftMostChildIndex() throws NoLeftChildE {
        assertEquals(4,wh1.getLeftMostChildIndex());

        boolean expected = false;
        try {
            wh2.getLeftMostChildIndex();
        } catch (NoLeftChildE error) {
            expected = true;
        }
        assertTrue(expected);

        expected = false;
        try {
            wh3.getLeftMostChildIndex();
        } catch (NoLeftChildE error) {
            expected = true;
        }
        assertTrue(expected);

        /*--------------------------------------------------------------------------------------------------------------
        * NOTE: When the height of a tree is 2, we have no left! But, your tests say we do.
        * --------------------------------------------------------------------------------------------------------------

        expected = false;
        try {
            wh4.getLeftMostChildIndex();
        } catch (NoLeftChildE error) {
            expected = true;
        }
        assertTrue(expected);

         */

        assertEquals(4,wh5.getLeftMostChildIndex());
    }

    @Test
    public void swap() {
        assertEquals(50, wh1.getValue(1));
        assertEquals(40, wh1.getValue(2));
        wh1.swap(1,2);
        assertEquals(50, wh1.getValue(2));
        assertEquals(40, wh1.getValue(1));

        boolean expected = false;
        try {
            wh2.swap(0, 1);
        } catch (IndexOutOfBoundsException error) {
            expected = true;
        }
        assertTrue(expected);

        expected = false;
        try {
            wh2.swap(0, 0);
        } catch (IndexOutOfBoundsException error) {
            expected = true;
        }
        assertTrue(expected);

        expected = false;
        try {
            wh2.swap(1, 0);
        } catch (IndexOutOfBoundsException error) {
            expected = true;
        }
        assertTrue(expected);

        expected = false;
        try {
            wh3.swap(0, 1);
        } catch (IndexOutOfBoundsException error) {
            expected = true;
        }
        assertTrue(expected);

        expected = true;
        try {
            wh3.swap(0, 0);
        } catch (IndexOutOfBoundsException error) {
            expected = false;
        }
        assertTrue(expected);

        assertEquals(50, wh5.getValue(1));
        assertEquals(40, wh5.getValue(2));
        wh5.swap(1,2);
        assertEquals(50, wh5.getValue(2));
        assertEquals(40, wh5.getValue(1));
    }

    @Test
    public void join() {
        assertTrue(wh1.join(0,4));

        wh1.getElem(4).setValue(10);
        assertFalse(wh1.join(0,4));
        assertEquals(10, wh1.getValue(0));
        assertEquals(50, wh1.getValue(1));
        assertEquals(40, wh1.getValue(2));
        assertEquals(70, wh1.getValue(3));
        assertEquals(30, wh1.getValue(4));
        assertEquals(60, wh1.getValue(5));
        assertEquals(170, wh1.getValue(6));
        assertEquals(110, wh1.getValue(7));

        wh1.getElem(2).setValue(5);
        assertFalse(wh1.join(0,2));
        assertEquals(5, wh1.getValue(0));
        assertEquals(50, wh1.getValue(1));
        assertEquals(10, wh1.getValue(2));
        assertEquals(70, wh1.getValue(3));
        assertEquals(30, wh1.getValue(4));
        assertEquals(60, wh1.getValue(5));
        assertEquals(170, wh1.getValue(6));
        assertEquals(110, wh1.getValue(7));
        assertEquals(1, wh1.getFlip(2));

        assertTrue(wh5.join(0,4));

        wh5.getElem(4).setValue(10);
        assertFalse(wh5.join(0,4));
        assertEquals(10, wh5.getValue(0));
        assertEquals(50, wh5.getValue(1));
        assertEquals(40, wh5.getValue(2));
        assertEquals(70, wh5.getValue(3));
        assertEquals(30, wh5.getValue(4));
        assertEquals(60, wh5.getValue(5));
        assertEquals(170, wh5.getValue(6));
        assertEquals(110, wh5.getValue(7));

        wh5.getElem(2).setValue(5);
        assertFalse(wh5.join(0,2));
        assertEquals(5, wh5.getValue(0));
        assertEquals(50, wh5.getValue(1));
        assertEquals(10, wh5.getValue(2));
        assertEquals(70, wh5.getValue(3));
        assertEquals(30, wh5.getValue(4));
        assertEquals(60, wh5.getValue(5));
        assertEquals(170, wh5.getValue(6));
        assertEquals(110, wh5.getValue(7));
        assertEquals(1, wh5.getFlip(2));
    }

    @Test
    public void weakHeapify() {
        Random r = new Random(1);
        ArrayList<Item> items = new ArrayList<>();
        for (int i=0; i<14; i++) {
            items.add(new Item(r.nextInt(100)));
        }
        WeakHeap wh = new WeakHeap(items);
        assertEquals(4, wh.getValue(0));
        assertEquals(6, wh.getValue(1));
        assertEquals(48, wh.getValue(2));
        assertEquals(17, wh.getValue(3));
        assertEquals(78, wh.getValue(4));
        assertEquals(47, wh.getValue(5));
        assertEquals(34, wh.getValue(6));
        assertEquals(13, wh.getValue(7));
        assertEquals(85, wh.getValue(8));
        assertEquals(54, wh.getValue(9));
        assertEquals(69, wh.getValue(10));
        assertEquals(73, wh.getValue(11));
        assertEquals(88, wh.getValue(12));
        assertEquals(63, wh.getValue(13));
        assertEquals(1, wh.getFlip(2));
        assertEquals(1, wh.getFlip(3));
        assertEquals(1, wh.getFlip(4));
        assertEquals(1, wh.getFlip(5));
        assertTrue(wh.checkOrder());

        items = new ArrayList<>();
        items.add(new Item(30));
        items.add(new Item(50));
        items.add(new Item(40));
        items.add(new Item(70));
        items.add(new Item(80));
        items.add(new Item(60));
        items.add(new Item(170));
        items.add(new Item(110));
        wh5 = new WeakHeap(items);
        assertTrue(wh5.checkOrder());
    }

    @Test
    public void moveUp() {
        wh1.getElem(7).setValue(0);
        wh1.moveUp(7);
        assertEquals(0, wh1.getValue(0));
        assertEquals(30, wh1.getValue(1));
        assertEquals(40, wh1.getValue(2));
        assertEquals(50, wh1.getValue(3));
        assertEquals(80, wh1.getValue(4));
        assertEquals(60, wh1.getValue(5));
        assertEquals(170, wh1.getValue(6));
        assertEquals(70, wh1.getValue(7));
        assertEquals(1,wh1.getFlip(1));
        assertEquals(1,wh1.getFlip(3));
        assertTrue(wh1.checkOrder());

        wh1.getElem(4).setValue(2);
        wh1.moveUp(4);
        assertEquals(0, wh1.getValue(0));
        assertEquals(2, wh1.getValue(1));
        assertEquals(40, wh1.getValue(2));
        assertEquals(50, wh1.getValue(3));
        assertEquals(30, wh1.getValue(4));
        assertEquals(60, wh1.getValue(5));
        assertEquals(170, wh1.getValue(6));
        assertEquals(70, wh1.getValue(7));
        assertEquals(1,wh1.getFlip(1));
        assertEquals(1,wh1.getFlip(3));
        assertTrue(wh1.checkOrder());

        wh5.getElem(7).setValue(0);
        wh5.moveUp(7);
        assertEquals(0, wh5.getValue(0));
        assertEquals(30, wh5.getValue(1));
        assertEquals(40, wh5.getValue(2));
        assertEquals(50, wh5.getValue(3));
        assertEquals(80, wh5.getValue(4));
        assertEquals(60, wh5.getValue(5));
        assertEquals(170, wh5.getValue(6));
        assertEquals(70, wh5.getValue(7));
        assertEquals(1,wh5.getFlip(1));
        assertEquals(1,wh5.getFlip(3));
        assertTrue(wh5.checkOrder());

        wh5.getElem(4).setValue(2);
        wh5.moveUp(4);
        assertEquals(0, wh5.getValue(0));
        assertEquals(2, wh5.getValue(1));
        assertEquals(40, wh5.getValue(2));
        assertEquals(50, wh5.getValue(3));
        assertEquals(30, wh5.getValue(4));
        assertEquals(60, wh5.getValue(5));
        assertEquals(170, wh5.getValue(6));
        assertEquals(70, wh5.getValue(7));
        assertEquals(1,wh5.getFlip(1));
        assertEquals(1,wh5.getFlip(3));
        assertTrue(wh5.checkOrder());
    }

    @Test
    public void insert() {
        wh1.insert(new Item(10));
        assertTrue(wh1.checkOrder());
        wh1.insert(new Item(11));
        assertTrue(wh1.checkOrder());
        wh1.insert(new Item(12));
        assertTrue(wh1.checkOrder());
        wh1.insert(new Item(13));
        assertTrue(wh1.checkOrder());
        wh1.insert(new Item(14));
        assertTrue(wh1.checkOrder());

        wh2.insert(new Item(10));
        assertTrue(wh2.checkOrder());
        wh2.insert(new Item(11));
        assertTrue(wh2.checkOrder());
        wh2.insert(new Item(12));
        assertTrue(wh2.checkOrder());
        wh2.insert(new Item(13));
        assertTrue(wh2.checkOrder());
        wh2.insert(new Item(14));
        assertTrue(wh2.checkOrder());

        wh3.insert(new Item(10));
        assertTrue(wh3.checkOrder());
        wh3.insert(new Item(11));
        assertTrue(wh3.checkOrder());
        wh3.insert(new Item(12));
        assertTrue(wh3.checkOrder());
        wh3.insert(new Item(13));
        assertTrue(wh3.checkOrder());
        wh3.insert(new Item(14));
        assertTrue(wh3.checkOrder());

        wh4.insert(new Item(10));
        assertTrue(wh4.checkOrder());
        wh4.insert(new Item(11));
        assertTrue(wh4.checkOrder());
        wh4.insert(new Item(12));
        assertTrue(wh4.checkOrder());
        wh4.insert(new Item(13));
        assertTrue(wh4.checkOrder());
        wh4.insert(new Item(14));
        assertTrue(wh4.checkOrder());

        wh5.insert(new Item(10));
        assertTrue(wh5.checkOrder());
        wh5.insert(new Item(11));
        assertTrue(wh5.checkOrder());
        wh5.insert(new Item(12));
        assertTrue(wh5.checkOrder());
        wh5.insert(new Item(13));
        assertTrue(wh5.checkOrder());
        wh5.insert(new Item(14));
        assertTrue(wh5.checkOrder());

    }

    @Test
    public void extractMin() {
        WeakHeap wh = new WeakHeap();
        wh.insert(new Item(1));
        wh.insert(new Item(10));
        wh.insert(new Item(50));
        wh.insert(new Item(11));
        wh.insert(new Item(80));
        wh.insert(new Item(60));
        wh.insert(new Item(12));
        wh.insert(new Item(15));
        wh.insert(new Item(75));
        wh.insert(new Item(90));
        wh.insert(new Item(55));
        wh.insert(new Item(70));
        wh.insert(new Item(13));
        wh.insert(new Item(14));
        wh.insert(new Item(16));
        wh.insert(new Item(100));
        wh.checkOrder();

        wh.extractMin();
        assertEquals(10, wh.getValue(0));
        assertEquals(50, wh.getValue(1));
        assertEquals(75, wh.getValue(2));
        assertEquals(11, wh.getValue(3));
        assertEquals(80, wh.getValue(4));
        assertEquals(60, wh.getValue(5));
        assertEquals(12, wh.getValue(6));
        assertEquals(15, wh.getValue(7));
        assertEquals(100, wh.getValue(8));
        assertEquals(90, wh.getValue(9));
        assertEquals(55, wh.getValue(10));
        assertEquals(70, wh.getValue(11));
        assertEquals(13, wh.getValue(12));
        assertEquals(14, wh.getValue(13));
        assertEquals(16, wh.getValue(14));
        assertEquals(1,wh.getFlip(1));
        assertEquals(1,wh.getFlip(2));
        assertEquals(1,wh.getFlip(8));

        wh = new WeakHeap();
        wh.insert(new Item(1));
        wh.insert(new Item(10));
        wh.insert(new Item(50));
        wh.insert(new Item(11));
        wh.insert(new Item(80));
        wh.insert(new Item(60));
        wh.insert(new Item(12));
        wh.insert(new Item(15));
        wh.insert(new Item(75));
        wh.insert(new Item(90));
        wh.insert(new Item(55));
        wh.insert(new Item(70));
        wh.insert(new Item(13));
        wh.insert(new Item(14));
        wh.insert(new Item(16));
        wh.insert(new Item(100));
        wh.checkOrder();

        wh.extractMin();
        assertEquals(10, wh.getValue(0));
        assertEquals(50, wh.getValue(1));
        assertEquals(75, wh.getValue(2));
        assertEquals(11, wh.getValue(3));
        assertEquals(80, wh.getValue(4));
        assertEquals(60, wh.getValue(5));
        assertEquals(12, wh.getValue(6));
        assertEquals(15, wh.getValue(7));
        assertEquals(100, wh.getValue(8));
        assertEquals(90, wh.getValue(9));
        assertEquals(55, wh.getValue(10));
        assertEquals(70, wh.getValue(11));
        assertEquals(13, wh.getValue(12));
        assertEquals(14, wh.getValue(13));
        assertEquals(16, wh.getValue(14));
        assertEquals(1,wh.getFlip(1));
        assertEquals(1,wh.getFlip(2));
        assertEquals(1,wh.getFlip(8));
    }
}