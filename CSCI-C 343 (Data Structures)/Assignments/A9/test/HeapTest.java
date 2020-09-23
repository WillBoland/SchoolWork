import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import static org.junit.Assert.*;

public class HeapTest {

    @Test
    public void isEmpty() {
        BinaryHeap bhp = new BinaryHeap();
        assertTrue(bhp.isEmpty());
        bhp.insert(new Item("Test", 1));
        assertFalse(bhp.isEmpty());
        bhp.extractMin();
        assertTrue(bhp.isEmpty());
        assertTrue(bhp.isEmpty());
        bhp.insert(new Item("Test", 1));
        assertFalse(bhp.isEmpty());
        bhp.extractMin();
        assertTrue(bhp.isEmpty());
        assertTrue(bhp.isEmpty());
        bhp.insert(new Item("Test", 1));
        bhp.insert(new Item("Test", 2));
        assertFalse(bhp.isEmpty());
        bhp.extractMin();
        bhp.extractMin();
        assertTrue(bhp.isEmpty());
    }

    @Test
    public void getSize() {
        BinaryHeap bhp = new BinaryHeap();
        assertEquals(0, bhp.getSize());
        bhp.insert(new Item("Test", 1));
        bhp.insert(new Item("Test", 2));
        bhp.insert(new Item("Test", 3));
        assertEquals(3, bhp.getSize());
        bhp.extractMin();
        assertEquals(2, bhp.getSize());

        bhp = new BinaryHeap();
        for(int i = 0; i < 1000; i += 1) {
            bhp.insert(new Item("Test", i));
        }
        assertEquals(1000, bhp.getSize());
        TreePrinter.print(bhp.findMin());
    }

    @Test
    public void findMin() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("3", 3));
        items.add(new Item("2", 2));
        items.add(new Item("1", 1));

        BinaryHeap bhp = new BinaryHeap(items);
        TreePrinter.print(bhp.findMin());

        assertEquals(1, bhp.extractMin().getValue());
        TreePrinter.print(bhp.findMin());

        assertEquals(2, bhp.findMin().getValue());
        bhp.insert(new Item("0", 0));
        TreePrinter.print(bhp.findMin());

        assertEquals(0, bhp.findMin().getValue());
        TreePrinter.print(bhp.findMin());
    }

    @Test
    public void getElems() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("3", 3));
        items.add(new Item("2", 2));
        items.add(new Item("1", 1));

        BinaryHeap bhp = new BinaryHeap(items);
        assertEquals(3, bhp.getElems().size());
        TreePrinter.print(bhp.findMin());

        bhp.insert(new Item("TEST", 100));
        assertEquals(4, bhp.getElems().size());

        assertEquals(1, bhp.getElems().get(0).getValue());
        assertEquals(2, bhp.getElems().get(1).getValue());
        assertEquals(1, bhp.getElems().get(0).getValue());
        assertEquals(2, bhp.getElems().get(1).getValue());
        assertEquals(3, bhp.getElems().get(2).getValue());
        assertEquals(100, bhp.getElems().get(3).getValue());
        assertEquals(1, bhp.getElems().get(0).getValue());
        assertEquals(2, bhp.getElems().get(1).getValue());
        assertEquals(3, bhp.getElems().get(2).getValue());
        assertEquals(100, bhp.getElems().get(3).getValue());
        assertEquals(3, bhp.getElems().get(2).getValue());
        assertEquals(1, bhp.getElems().get(0).getValue());
        assertEquals(2, bhp.getElems().get(1).getValue());
        assertEquals(3, bhp.getElems().get(2).getValue());
        assertEquals(100, bhp.getElems().get(3).getValue());
        assertEquals(100, bhp.getElems().get(3).getValue());
        TreePrinter.print(bhp.findMin());
    }

    @Test
    public void getElem() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("3", 3));
        items.add(new Item("2", 2));
        items.add(new Item("1", 1));

        BinaryHeap bhp = new BinaryHeap(items);
        assertEquals(1, bhp.getElem(0).getValue());
        assertEquals(2, bhp.getElem(1).getValue());
        assertEquals(3, bhp.getElem(2).getValue());
        assertEquals(0, bhp.getElem(0).getRevbit());
        assertEquals(0, bhp.getElem(0).getRevbit());
        assertEquals(0, bhp.getElem(0).getRevbit());
        TreePrinter.print(bhp.findMin());
        Item test1 = new Item("TEST", 1000);
        Item test2 = new Item("TEST", 2000);
        test2.reverse();
        bhp.insert(test1);
        bhp.insert(test2);

        assertEquals(1000, bhp.getElem(3).getValue());
        assertEquals(0, bhp.getElem(3).getRevbit());
        assertEquals(2000, bhp.getElem(4).getValue());
        assertEquals(1, bhp.getElem(4).getRevbit());
        TreePrinter.print(bhp.findMin());
    }

    @Test
    public void getParentIndex() throws NoParentE {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("3", 3));
        items.add(new Item("2", 2));
        items.add(new Item("1", 1));

        BinaryHeap bhp = new BinaryHeap(items);
        assertEquals(0, bhp.getParentIndex(1));
        assertEquals(0, bhp.getParentIndex(2));
        TreePrinter.print(bhp.findMin());
        boolean expectedThrow = false;
        try {
            bhp.getParentIndex(0);
        } catch (NoParentE e) {
            expectedThrow = true;
        }
        assertTrue(expectedThrow);

        bhp = new BinaryHeap(items);
        boolean expectedThrow2 = false;
        try {
            bhp.getParentIndex(0);
        } catch (NoParentE e) {
            expectedThrow2 = true;
        }
        assertTrue(expectedThrow2);
    }

    @Test
    public void others() {
        BinaryHeap bhp = new BinaryHeap();
        for(int i = 0; i < 1000; i += 1) {
            bhp.insert(new Item("Test0", i));
        }
        TreePrinter.print(bhp.findMin());
        for(int check = 0; check < 1000; check += 1) {
            assertEquals(check, bhp.extractMin().getValue());
        }

        bhp = new BinaryHeap();
        for(int i = 0; i < 9999; i += 1) {
            bhp.insert(new Item("Test1", i));
        }
        TreePrinter.print(bhp.findMin());
        for(int check = 0; check < 9999; check += 1) {
            assertEquals(check, bhp.extractMin().getValue());
        }

        bhp = new BinaryHeap();
        for(int i = 2000; i >= 0; i -= 1) {
            bhp.insert(new Item("Test2", i));
        }
        TreePrinter.print(bhp.findMin());
        for(int check = 0; check >= 2000; check += 1) {
            assertEquals(check, bhp.extractMin().getValue());
        }

        bhp = new BinaryHeap();
        for(int i = 0; i < 1000; i += 1) {
            bhp.insert(new Item("Test3", i));
        }
        TreePrinter.print(bhp.findMin());
        for(int check = 0; check < 1000; check += 1) {
            assertEquals(check, bhp.extractMin().getValue());
        }

        bhp = new BinaryHeap();
        for(int i = 0; i < 9999; i += 1) {
            bhp.insert(new Item("Test4", i));
        }
        TreePrinter.print(bhp.findMin());
        for(int check = 0; check < 9999; check += 1) {
            assertEquals(check, bhp.extractMin().getValue());
        }

        bhp = new BinaryHeap();
        for(int i = 2000; i >= 0; i -= 1) {
            bhp.insert(new Item("Test5", i));
        }
        TreePrinter.print(bhp.findMin());
        for(int check = 0; check >= 2000; check += 1) {
            assertEquals(check, bhp.extractMin().getValue());
        }

        bhp = new BinaryHeap();
        for(int i = 0; i < 1000; i += 1) {
            bhp.insert(new Item("Test6", i));
        }
        TreePrinter.print(bhp.findMin());
        for(int check = 0; check < 1000; check += 1) {
            assertEquals(check, bhp.extractMin().getValue());
        }

        bhp = new BinaryHeap();
        for(int i = 0; i < 9999; i += 1) {
            bhp.insert(new Item("Test7", i));
        }
        TreePrinter.print(bhp.findMin());
        for(int check = 0; check < 9999; check += 1) {
            assertEquals(check, bhp.extractMin().getValue());
        }

        bhp = new BinaryHeap();
        for(int i = 2000; i >= 0; i -= 1) {
            bhp.insert(new Item("Test8", i));
        }
        TreePrinter.print(bhp.findMin());
        for(int check = 0; check >= 2000; check += 1) {
            assertEquals(check, bhp.extractMin().getValue());
        }

        bhp = new BinaryHeap();
        for(int i = 0; i < 1000; i += 1) {
            bhp.insert(new Item("Test9", i));
        }
        TreePrinter.print(bhp.findMin());
        for(int check = 0; check < 1000; check += 1) {
            assertEquals(check, bhp.extractMin().getValue());
        }

        ArrayList<Item> items = new ArrayList<>();
        Item it;
        it = new Item("1",  6);
        items.add(it);
        it = new Item("2",  1);
        it.reverse();
        items.add(it);
        it = new Item("3",  8);
        items.add(it);
        it = new Item("4",  2);
        it.reverse();
        items.add(it);
        it = new Item("5",  4);
        items.add(it);
        it = new Item("6",  7);
        items.add(it);
        it = new Item("7",  9);
        items.add(it);
        it = new Item("8",  3);
        items.add(it);
        it = new Item("9",  5);
        items.add(it);
        bhp = new BinaryHeap(items);
        for (int i = 1; i < 10; i++) assertEquals(i, bhp.extractMin().getValue());
    }

    @Test
    public void sortBH () {
        ArrayList<Item> items = new ArrayList<>();

        Item it;

        it = new Item("a1",  6);
        items.add(it);

        it = new Item("a2",  1);
        it.reverse();
        items.add(it);

        it = new Item("a3",  8);
        items.add(it);

        it = new Item("a4",  2);
        it.reverse();
        items.add(it);

        it = new Item("a5",  4);
        items.add(it);

        it = new Item("a6",  7);
        items.add(it);

        it = new Item("a7",  9);
        items.add(it);

        it = new Item("a8",  3);
        items.add(it);

        it = new Item("a9",  5);
        items.add(it);

        BinaryHeap bhp = new BinaryHeap(items);
        TreePrinter.print(bhp.findMin());

        for (int i = 1; i < 10; i++) assertEquals(i, bhp.extractMin().getValue());

    }

}
