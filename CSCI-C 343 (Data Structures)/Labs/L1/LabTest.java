package com.company;

import static org.junit.Assert.*;

public class LabTest {

    @org.junit.Test
    public void maxNumber() {
        assertEquals(1, Lab.maxNumber(1, -2, -3));
        assertEquals(-20, Lab.maxNumber(-20, -50, Integer.MIN_VALUE));
        assertEquals(Integer.MAX_VALUE, Lab.maxNumber(Integer.MAX_VALUE, -200, 9999));
        assertEquals(0, Lab.maxNumber(0, -2, -2000));
    }

    @org.junit.Test
    public void allCaps() {
        assertEquals("THIS SHOULD WORK", Lab.allCaps("this should work"));
        assertEquals("this also works".toUpperCase(), Lab.allCaps("this also works"));
        assertEquals("".toUpperCase(), Lab.allCaps(""));
    }

    @org.junit.Test
    public void subsequenceIndex() {
        assertEquals(-1, Lab.subsequenceIndex(new int[] {1, 2, 3, 4}, new int[] {3, 5}));
        assertEquals(0, Lab.subsequenceIndex(new int[] {1, 2, 3, 4}, new int[] {}));
        assertEquals(4, Lab.subsequenceIndex(new int[] {1, 2, 3, 4, 6, 5, 2, 3}, new int[] {6, 5}));
    }
}