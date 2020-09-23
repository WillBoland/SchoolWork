import org.junit.Test;

import static org.junit.Assert.*;

public class ListTest {
    //empty list
    EmptyL<String> emptyList = new EmptyL<String>();

    //non-empty String Lists
    NodeL<String> singletonList = new NodeL<String>("first", emptyList);
    NodeL<String> dualList = new NodeL<String>("first", new NodeL<String>("second", emptyList));
    NodeL<String> triList = new NodeL<String>("first", new NodeL<String>("second", new NodeL<String>("third", emptyList)));
    NodeL<String> quadList = new NodeL<String>("first", new NodeL<String>("second", new NodeL<String>("third", new NodeL<String>("fourth", emptyList))));

    //non-empty Integer Lists
    NodeL<Integer> intSingletonList = new NodeL<Integer>(2, emptyList);
    NodeL<Integer> intDualList = new NodeL<Integer>(2, new NodeL<Integer>(200, emptyList));

    //lists that contains other lists
    NodeL<List> nodeStackedEmptyLists = new NodeL<List>(emptyList, emptyList);
    NodeL<List> nodeSingletonList = new NodeL<List>(singletonList, emptyList);
    NodeL<List> nodeTriList = new NodeL<List>(singletonList, new NodeL<List>(quadList, new NodeL<List>(triList, emptyList)));

    @Test
    public void isEmpty() {
        //true statements
        assertTrue(emptyList.isEmpty());

        //false statements
        assertFalse(singletonList.isEmpty());
        assertFalse(dualList.isEmpty());
        assertFalse(triList.isEmpty());
        assertFalse(quadList.isEmpty());

        assertFalse(intSingletonList.isEmpty());

        assertFalse(nodeStackedEmptyLists.isEmpty());
        assertFalse(nodeSingletonList.isEmpty());
        assertFalse(nodeTriList.isEmpty());
    }

    @Test
    public void isSingleton() {
        //true statements
        assertTrue(singletonList.isSingleton());
        assertTrue(nodeSingletonList.isSingleton());
        assertTrue(nodeStackedEmptyLists.isSingleton());
        assertTrue(intSingletonList.isSingleton());


        //false statements
        assertFalse(emptyList.isSingleton());
        assertFalse(dualList.isSingleton());
        assertFalse(triList.isSingleton());
        assertFalse(quadList.isSingleton());
        assertFalse(intDualList.isSingleton());
        assertFalse(nodeTriList.isSingleton());
    }

    @Test
    public void getFirst() {
        //equal test
        assertEquals("first", singletonList.getFirst());
        assertEquals("first", dualList.getFirst());
        assertEquals("first", triList.getFirst());
        assertEquals("first", quadList.getFirst());

        assertEquals((Object)2, intSingletonList.getFirst());
        assertEquals(emptyList, nodeStackedEmptyLists.getFirst());
        assertEquals(singletonList, nodeSingletonList.getFirst());
        assertEquals(singletonList, nodeTriList.getFirst());

        //not equal test
        assertNotEquals("second", singletonList.getFirst());
        assertNotEquals("second", dualList.getFirst());
        assertNotEquals("second", triList.getFirst());
        assertNotEquals("fourth", quadList.getFirst());
    }

    @Test
    public void getRest() {
        //equal test
        assertEquals(emptyList, singletonList.getRest());
        assertEquals(emptyList, nodeStackedEmptyLists.getRest());
        assertEquals(emptyList, nodeSingletonList.getRest());

        //not equal test
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
    public void get() {
        try {
            //true tests
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

            assertEquals((Object) 2, intDualList.get(0));
            assertEquals((Object) 200, intDualList.get(1));

            assertEquals(emptyList, nodeStackedEmptyLists.get(0));
            assertEquals(singletonList, nodeSingletonList.get(0));
            assertEquals(quadList, nodeTriList.get(1));
            assertEquals(triList, nodeTriList.get(2));
        } catch (EmptyListE error) {
            System.out.println("ERROR THROWN: " + error.getMessage());
        }
    }

    @Test
    public void length() {
        //equal tests
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
        //equals
        assertEquals(2, singletonList.append(singletonList).length());
        assertEquals(8, quadList.append(quadList).length());
        assertEquals(4, triList.append(singletonList).length());
        assertEquals(2, dualList.append(emptyList).length());

        assertEquals(6, nodeTriList.append(nodeTriList).length());
        assertEquals(4, nodeTriList.append(nodeSingletonList).length());
        assertEquals(4, nodeStackedEmptyLists.append(nodeTriList).length());
    }

    @Test
    public void contains() {
        //true tests
        assertTrue(singletonList.contains("first"));
        assertTrue(triList.contains("first"));
        assertTrue(triList.contains("second"));
        assertTrue(quadList.contains("fourth"));
        assertTrue(singletonList.append(dualList).contains("second"));

        assertTrue(nodeStackedEmptyLists.contains(emptyList));
        assertTrue(nodeSingletonList.contains(singletonList));
        assertTrue(nodeTriList.contains(quadList));
        assertTrue(nodeTriList.contains(triList));

        assertTrue(intSingletonList.contains(2));
        assertTrue(intDualList.contains(2));
        assertTrue(intDualList.contains(200));

        //false tests
        assertFalse(emptyList.contains("first"));
        assertFalse(emptyList.contains("second"));
        assertFalse(emptyList.contains("third"));
        assertFalse(emptyList.contains("fourth"));
        assertFalse(triList.contains("fourth"));
        assertFalse(dualList.contains("third"));
        assertFalse(singletonList.contains("second"));
    }

    //----------THROW TESTS----------//

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