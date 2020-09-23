import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class ListTest {
    private Random rand;
    private List<Integer> empty, one, three, big;
    private List<String> emptyList;
    private List<List> nodeStackedEmptyLists, nodeSingletonList, nodeTriList;
    private List<String> singletonList, dualList, triList, quadList;

    @Before
    public void setUp() {
        rand = new Random(1);
        empty = new EmptyL<>();
        one = new NodeL<>(3, new EmptyL<>());
        three = new NodeL<>(3, new NodeL<>(3, new NodeL<>(3, new EmptyL<>())));
        big = new EmptyL();
        for (int i=0; i<rand.nextInt(10000); i++) {
            big = new NodeL(rand.nextInt(), big);
        }
        emptyList = new EmptyL<String>();
        nodeStackedEmptyLists = new NodeL<List>(emptyList, new EmptyL<>());
        nodeSingletonList = new NodeL<List>(one, new EmptyL<>());
        nodeTriList = new NodeL<List>(one, new NodeL<List>(three, new NodeL<List>(one, new EmptyL<>())));
        singletonList = new NodeL<String>("first", emptyList);
        dualList = new NodeL<String>("first", new NodeL<String>("second", emptyList));
        triList = new NodeL<String>("first", new NodeL<String>("second", new NodeL<String>("third", emptyList)));
        quadList = new NodeL<String>("first", new NodeL<String>("second", new NodeL<String>("third", new NodeL<String>("fourth", emptyList))));
    }

    @After
    public void tearDown() {
        rand = null;
        empty = null;
        one = null;
        three = null;
        big = null;
    }

    @Test
    public void isEmpty() {
        assertTrue(empty.isEmpty());
        assertFalse(one.isEmpty());
        assertFalse(three.isEmpty());
    }

    @Test
    public void isSingleton() {
        assertFalse(empty.isSingleton());
        assertTrue(one.isSingleton());
        assertFalse(three.isSingleton());
        assertFalse(nodeStackedEmptyLists.isEmpty());
        assertFalse(nodeSingletonList.isEmpty());
        assertFalse(nodeTriList.isEmpty());
        assertTrue(nodeSingletonList.isSingleton());
        assertTrue(nodeStackedEmptyLists.isSingleton());
        assertFalse(nodeTriList.isSingleton());
    }

    @Test(expected = EmptyListE.class)
    public void getFirst1() throws Exception {
        empty.getFirst();
    }

    @Test
    public void getFirst2() throws Exception {
        assertEquals(3 , (int) one.getFirst());
        assertEquals(3, (int) three.getFirst());
        assertEquals(emptyList, nodeStackedEmptyLists.getFirst());
        assertEquals(one, nodeSingletonList.getFirst());
        assertEquals(one, nodeTriList.getFirst());
    }

    @Test(timeout = 1) // one millisecond
    public void getFirst3() throws Exception {
        big.getFirst();
    }

    @Test
    public void getRest() throws Exception {
        assertEquals(emptyList, singletonList.getRest());
        assertNotEquals(singletonList, singletonList.getRest());
        assertNotEquals(dualList, singletonList.getRest());
        assertNotEquals(triList, singletonList.getRest());
        assertNotEquals(quadList, singletonList.getRest());
        assertNotEquals(singletonList, dualList.getRest());
        assertNotEquals(dualList, dualList.getRest());
        assertNotEquals(dualList, triList.getRest());
        assertNotEquals(triList, quadList.getRest());
    }

    @Test
    public void get() throws Exception {
        assertEquals("first", singletonList.get(0));
        assertEquals("first", dualList.get(0));
        assertEquals("first", triList.get(0));
        assertEquals("first", quadList.get(0));
        assertEquals("second", dualList.get(1));
        assertEquals("second", triList.get(1));
        assertEquals("second", quadList.get(1));
        assertEquals("third", triList.get(2));
        assertEquals("third", quadList.get(2));
        assertEquals("fourth", quadList.get(3));
    }

    @Test
    public void length() {
        assertEquals(0, emptyList.length());
        assertEquals(1, singletonList.length());
        assertEquals(2, dualList.length());
        assertEquals(3, triList.length());
        assertEquals(4, quadList.length());
        assertEquals(1, nodeStackedEmptyLists.length());
        assertEquals(1, nodeSingletonList.length());
        assertEquals(3, nodeTriList.length());
    }

    @Test
    public void append() {
        assertEquals(2, singletonList.append(singletonList).length());
        assertEquals(8, quadList.append(quadList).length());
        assertEquals(4, triList.append(singletonList).length());
        assertEquals(2, dualList.append(emptyList).length());
        assertEquals(6, nodeTriList.append(nodeTriList).length());
        assertEquals(4, nodeTriList.append(nodeSingletonList).length());
        assertEquals(4, nodeStackedEmptyLists.append(nodeTriList).length());
        assertTrue(singletonList.append(dualList).contains("second"));
        assertTrue(singletonList.append(quadList).contains("fourth"));
        assertTrue(emptyList.append(dualList).contains("second"));
    }

    @Test
    public void contains() {
        assertTrue(singletonList.contains("first"));
        assertTrue(triList.contains("first"));
        assertTrue(triList.contains("second"));
        assertTrue(quadList.contains("fourth"));
        assertTrue(singletonList.append(dualList).contains("second"));
        assertFalse(emptyList.contains("first"));
        assertFalse(emptyList.contains("second"));
        assertFalse(emptyList.contains("third"));
        assertFalse(emptyList.contains("fourth"));
        assertFalse(triList.contains("fourth"));
        assertFalse(dualList.contains("third"));
        assertFalse(singletonList.contains("second"));
    }

    @Test(expected = EmptyListE.class)
    public void getThrow() throws Exception {
        emptyList.get(1);
    }

    @Test(expected = EmptyListE.class)
    public void getFirstThrow() throws Exception {
        emptyList.getFirst();
    }

    @Test(expected = EmptyListE.class)
    public void getRestThrow() throws Exception {
        emptyList.getRest();
    }

    @Test(expected = EmptyListE.class)
    public void indexRangeThrow() throws Exception {
        quadList.get(30);
    }
}