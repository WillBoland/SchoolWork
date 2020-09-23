import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class HashTableTest {

    //All 4 tests test insert, delete, rehash, getCapacity, and setCapacity
    @Test
    public void hashSeparateChaining () {
        Random r = new Random(1);
        HashFunction hf = new HashUniversal(r,31, 10);
        HashTable ht = new HashSeparateChaining(hf);
        ht.insert(1);
        ht.insert(12);
        assertTrue(ht.search(12));
        ht.delete(12);
        assertFalse(ht.search(12));
        assertTrue(ht.search(1));
        assertFalse(ht.search(2));
        ht.insert(22);
        System.out.println("Before rehashing");
        System.out.println(ht);
        ht.rehash();
        System.out.println("After rehashing");
        System.out.println(ht);

        hf = new HashMod(1);
        ht = new HashSeparateChaining(hf);
        ht.insert(1);
        ht.insert(2);
        ht.insert(3);
        ht.insert(-200);
        ht.delete(20);
        assertTrue(ht.search(1));
        assertTrue(ht.search(2));
        assertTrue(ht.search(3));
        assertTrue(ht.search(-200));
        assertFalse(ht.search(20));
        ht.insert(1);
        ht.insert(2);
        ht.insert(3);
        ht.insert(-200);
        ht.delete(20);
        assertTrue(ht.search(1));
        assertTrue(ht.search(2));
        assertTrue(ht.search(3));
        assertTrue(ht.search(-200));
        assertFalse(ht.search(20));
        ht.insert(1);
        ht.insert(2);
        ht.insert(3);
        ht.insert(-200);
        ht.delete(20);
        assertTrue(ht.search(1));
        assertTrue(ht.search(2));
        assertTrue(ht.search(3));
        assertTrue(ht.search(-200));
        assertFalse(ht.search(20));
        ht.insert(1);
        ht.insert(2);
        ht.insert(3);
        ht.insert(-200);
        ht.delete(20);
        assertTrue(ht.search(1));
        assertTrue(ht.search(2));
        assertTrue(ht.search(3));
        assertTrue(ht.search(-200));
        assertFalse(ht.search(20));

        hf = new HashUniversal(r, 31, 7);
        ht = new HashSeparateChaining(hf);
        ht.insert(100);
        ht.insert(222);
        ht.insert(333);
        ht.insert(-200);
        ht.delete(100);
        assertTrue(ht.search(222));
        assertTrue(ht.search(333));
        assertTrue(ht.search(-200));
        assertFalse(ht.search(100));
    }

    @Test
    public void hashLinearProbing() {
        HashFunction hf = new HashMod(1);
        HashTable ht = new HashLinearProbing(hf);
        ht.insert(89);
        ht.delete(89);
        ht.delete(100);
        ht.insert(200);
        ht.search(100);

        assertTrue(ht.search(200));
        assertEquals(1, ht.getCapacity());

        ht.delete(200);
        ht.delete(100);
        ht.insert(200);
        ht.search(100);
        ht.delete(100);

        assertTrue(ht.search(200));
        assertEquals(1, ht.getCapacity());

        ht.delete(200);
        ht.delete(100);
        ht.insert(200);
        ht.search(100);
        ht.delete(100);

        assertTrue(ht.search(200));
        assertEquals(1, ht.getCapacity());

        ht.delete(200);
        ht.delete(100);
        ht.insert(200);
        ht.search(100);
        ht.delete(100);
        assertTrue(ht.search(200));
        assertEquals(1, ht.getCapacity());
        ht.delete(200);

        //rehash
        ht.insert(1);
        ht.insert(2);
        assertEquals(3, ht.getCapacity());
        ht.insert(3);
        ht.insert(3);
        assertTrue(ht.search(1));
        assertEquals(7, ht.getCapacity());
        System.out.println(ht);

        ht.delete(2);
        System.out.println(ht);
        ht.insert(4);
        ht.insert(4);
        ht.insert(4);
        ht.insert(4);
        ht.delete(200);
        ht.delete(200);
        assertTrue(ht.search(4));
        assertTrue(ht.search(4));
        assertEquals(7, ht.getCapacity());

        hf = new HashMod(7);
        ht = new HashLinearProbing(hf);
        System.out.println(ht);
        ht.insert(0);
        ht.insert(1);
        ht.insert(2);
        ht.insert(3);
        ht.insert(4);
        ht.insert(5);
        ht.insert(6);
        System.out.println(ht);
        ht.insert(9);
        System.out.println(ht);

        Random r = new Random(1);
        hf = new HashMod(1);
        ht = new HashLinearProbing(hf);
        ht.insert(1);
        ht.insert(2);
        ht.insert(3);
        ht.insert(-200);
        ht.delete(20);
        assertTrue(ht.search(1));
        assertTrue(ht.search(2));
        assertTrue(ht.search(3));
        assertTrue(ht.search(-200));
        assertFalse(ht.search(20));
        ht.insert(1);
        ht.insert(2);
        ht.insert(3);
        ht.insert(-200);
        ht.delete(20);
        assertTrue(ht.search(1));
        assertTrue(ht.search(2));
        assertTrue(ht.search(3));
        assertTrue(ht.search(-200));
        assertFalse(ht.search(20));
        ht.insert(1);
        ht.insert(2);
        ht.insert(3);
        ht.insert(-200);
        ht.delete(20);
        assertTrue(ht.search(1));
        assertTrue(ht.search(2));
        assertTrue(ht.search(3));
        assertTrue(ht.search(-200));
        assertFalse(ht.search(20));
        ht.insert(1);
        ht.insert(2);
        ht.insert(3);
        ht.insert(-200);
        ht.delete(20);
        assertTrue(ht.search(1));
        assertTrue(ht.search(2));
        assertTrue(ht.search(3));
        assertTrue(ht.search(-200));
        assertFalse(ht.search(20));

        hf = new HashUniversal(r, 31, 7);
        ht = new HashLinearProbing(hf);
        ht.insert(100);
        ht.insert(222);
        ht.insert(333);
        ht.insert(-200);
        ht.delete(100);
        assertTrue(ht.search(222));
        assertTrue(ht.search(333));
        assertTrue(ht.search(-200));
        assertFalse(ht.search(100));
    }

    @Test
    public void hashQuadProbing() {
        HashFunction hf = new HashMod(1);
        HashTable ht = new HashQuadProbing(hf);
        ht.insert(89);
        ht.delete(89);
        ht.delete(100);
        ht.insert(200);
        ht.search(100);

        assertTrue(ht.search(200));
        assertEquals(1, ht.getCapacity());

        ht.delete(200);
        ht.delete(100);
        ht.insert(200);
        ht.search(100);
        ht.delete(100);

        assertTrue(ht.search(200));
        assertEquals(1, ht.getCapacity());

        ht.delete(200);
        ht.delete(100);
        ht.insert(200);
        ht.search(100);
        ht.delete(100);

        assertTrue(ht.search(200));
        assertEquals(1, ht.getCapacity());

        ht.delete(200);
        ht.delete(100);
        ht.insert(200);
        ht.search(100);
        ht.delete(100);
        assertTrue(ht.search(200));
        assertEquals(1, ht.getCapacity());
        ht.delete(200);

        //rehash
        ht.insert(1);
        ht.insert(2);
        assertEquals(3, ht.getCapacity());
        ht.insert(3);
        ht.insert(3);
        assertTrue(ht.search(1));
        assertEquals(7, ht.getCapacity());
        System.out.println(ht);

        ht.delete(2);
        System.out.println(ht);
        ht.insert(4);
        ht.insert(4);
        ht.insert(4);
        ht.insert(4);
        ht.delete(200);
        ht.delete(200);
        assertTrue(ht.search(4));
        assertTrue(ht.search(4));
        assertEquals(17, ht.getCapacity());

        hf = new HashMod(7);
        ht = new HashQuadProbing(hf);
        System.out.println(ht);
        ht.insert(0);
        ht.insert(1);
        ht.insert(2);
        ht.insert(3);
        ht.insert(4);
        ht.insert(5);
        ht.insert(6);
        System.out.println(ht);
        ht.insert(9);
        System.out.println(ht);

        Random r = new Random(1);
        hf = new HashMod(1);
        ht = new HashQuadProbing(hf);
        ht.insert(1);
        ht.insert(2);
        ht.insert(3);
        ht.insert(-200);
        ht.delete(20);
        assertTrue(ht.search(1));
        assertTrue(ht.search(2));
        assertTrue(ht.search(3));
        assertTrue(ht.search(-200));
        assertFalse(ht.search(20));
        ht.insert(1);
        ht.insert(2);
        ht.insert(3);
        ht.insert(-200);
        ht.delete(20);
        assertTrue(ht.search(1));
        assertTrue(ht.search(2));
        assertTrue(ht.search(3));
        assertTrue(ht.search(-200));
        assertFalse(ht.search(20));
        ht.insert(1);
        ht.insert(2);
        ht.insert(3);
        ht.insert(-200);
        ht.delete(20);
        assertTrue(ht.search(1));
        assertTrue(ht.search(2));
        assertTrue(ht.search(3));
        assertTrue(ht.search(-200));
        assertFalse(ht.search(20));
        ht.insert(1);
        ht.insert(2);
        ht.insert(3);
        ht.insert(-200);
        ht.delete(20);
        assertTrue(ht.search(1));
        assertTrue(ht.search(2));
        assertTrue(ht.search(3));
        assertTrue(ht.search(-200));
        assertFalse(ht.search(20));

        hf = new HashUniversal(r, 31, 7);
        ht = new HashQuadProbing(hf);
        ht.insert(100);
        ht.insert(222);
        ht.insert(333);
        ht.insert(-200);
        ht.delete(100);
        assertTrue(ht.search(222));
        assertTrue(ht.search(333));
        assertTrue(ht.search(-200));
        assertFalse(ht.search(100));
    }

    @Test
    public void hashDouble() {
        Random r = new Random(1);
        HashFunction hf = new HashMod(1);
        HashFunction hf2 = new HashModThen(7, h -> 7 - h);
        HashTable ht = new HashDouble(hf, hf2);

        ht.insert(89);
        ht.delete(89);
        ht.delete(100);
        ht.insert(200);
        ht.search(100);

        assertTrue(ht.search(200));
        assertEquals(1, ht.getCapacity());

        ht.delete(200);
        ht.delete(100);
        ht.insert(200);
        ht.search(100);
        ht.delete(100);

        assertTrue(ht.search(200));
        assertEquals(1, ht.getCapacity());

        ht.delete(200);
        ht.delete(100);
        ht.insert(200);
        ht.search(100);
        ht.delete(100);

        assertTrue(ht.search(200));
        assertEquals(1, ht.getCapacity());

        ht.delete(200);
        ht.delete(100);
        ht.insert(200);
        ht.search(100);
        ht.delete(100);
        assertTrue(ht.search(200));
        assertEquals(1, ht.getCapacity());
        ht.delete(200);

        //rehash
        ht.insert(1);
        ht.insert(2);
        assertEquals(3, ht.getCapacity());
        ht.insert(3);
        ht.insert(3);
        assertTrue(ht.search(1));
        assertEquals(7, ht.getCapacity());
        System.out.println(ht);

        ht.delete(2);
        System.out.println(ht);
        ht.insert(4);
        ht.insert(4);
        ht.insert(4);
        ht.insert(4);
        ht.delete(200);
        ht.delete(200);
        assertTrue(ht.search(4));
        assertTrue(ht.search(4));
        assertEquals(7, ht.getCapacity());

        hf = new HashUniversal(r, 31, 7);
        ht = new HashDouble(hf, hf2);
        System.out.println(ht);
        ht.insert(0);
        ht.insert(1);
        ht.insert(2);
        ht.insert(3);
        ht.insert(4);
        ht.insert(5);
        ht.insert(6);
        System.out.println(ht);
        ht.insert(9);
        System.out.println(ht);

        hf = new HashMod(1);
        ht = new HashQuadProbing(hf);
        ht.insert(1);
        ht.insert(2);
        ht.insert(3);
        ht.insert(-200);
        ht.delete(20);
        assertTrue(ht.search(1));
        assertTrue(ht.search(2));
        assertTrue(ht.search(3));
        assertTrue(ht.search(-200));
        assertFalse(ht.search(20));
        ht.insert(1);
        ht.insert(2);
        ht.insert(3);
        ht.insert(-200);
        ht.delete(20);
        assertTrue(ht.search(1));
        assertTrue(ht.search(2));
        assertTrue(ht.search(3));
        assertTrue(ht.search(-200));
        assertFalse(ht.search(20));
        ht.insert(1);
        ht.insert(2);
        ht.insert(3);
        ht.insert(-200);
        ht.delete(20);
        assertTrue(ht.search(1));
        assertTrue(ht.search(2));
        assertTrue(ht.search(3));
        assertTrue(ht.search(-200));
        assertFalse(ht.search(20));
        ht.insert(1);
        ht.insert(2);
        ht.insert(3);
        ht.insert(-200);
        ht.delete(20);
        assertTrue(ht.search(1));
        assertTrue(ht.search(2));
        assertTrue(ht.search(3));
        assertTrue(ht.search(-200));
        assertFalse(ht.search(20));

        hf = new HashUniversal(r, 31, 7);
        ht = new HashDouble(hf, hf2);
        ht.insert(100);
        ht.insert(222);
        ht.insert(333);
        ht.insert(-200);
        ht.delete(100);
        assertTrue(ht.search(222));
        assertTrue(ht.search(333));
        assertTrue(ht.search(-200));
        assertFalse(ht.search(100));
    }


    //REFERS TO THE BOOK (Figure 55)
    @Test
    public void fig55 () {
        HashFunction hf = new HashMod(10);
        HashTable ht = new HashSeparateChaining(hf);
        ht.insert(0);
        ht.insert(81);
        ht.insert(64);
        ht.insert(49);
        ht.insert(9);
        ht.insert(36);
        ht.insert(25);
        ht.insert(16);
        ht.insert(4);
        ht.insert(1);
        System.out.println("Fig. 5.5");
        System.out.println(ht);
    }


    @Test
    public void fig511 () {
        HashFunction hf = new HashMod(10);
        HashTable ht = new HashLinearProbing(hf);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);
        System.out.println("Fig. 5.11");
        System.out.println(ht);
    }

    @Test
    public void fig513 () {
        HashFunction hf = new HashMod(10);
        assertEqualsL(9, hf.apply(89));
        HashTable ht = new HashQuadProbing (hf);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);
        System.out.println("Fig. 5.13");
        System.out.println(ht);
    }

    @Test
    public void fig518 () {
        HashFunction hf = new HashMod(10);
        HashFunction hf2 = new HashModThen(7, h -> 7 - h);
        HashTable ht = new HashDouble (hf, hf2);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);
        System.out.println("Fig. 5.18");
        System.out.println(ht);
    }

    void assertEqualsL (long i, long j) {
        assertEquals(i,j);
    }

}