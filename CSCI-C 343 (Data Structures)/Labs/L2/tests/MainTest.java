import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    // create a test that ensures that each elementary statement of m is executed at least once
    public void m1() {
        assertEquals(34, Main.m(2, 10)); //if statement TT
        assertEquals(-4, Main.m(3, 10)); //if statement TF
        assertEquals(14, Main.m(2, 30));//if statement FT
        assertEquals(166, Main.m(3, 180));//if statement FF
        assertEquals(20, Main.m(30, 20));//while if, FT
    }

    @Test
    // create a test that ensures that each branch of m is executed at least once
    public void m2() {
	// below is an example of a test for m2
	// step through it and take note of what parts of the program is hits and doesn't hit
	// take note that this example test alone doesn't cover everything it should
        assertEquals(50, Main.m(20,10));//while if, if
        assertEquals(50, Main.m(60, 50));//while if, else
        assertEquals(38, Main.m(34, 10));//while, if
        assertEquals(12, Main.m(31, 30));//while, else
    }

    @Test
    // create a test that ensures that each branch of m is executed at least once
    // and each condition is exercised at least once
    public void m3() {
        // below is an example of a test for m3
        // step through it and take note of what parts of the program is hits and doesn't hit
        // take note that this example test alone doesn't cover everything it should
        assertEquals(-8, Main.m(21,10)); //while, else TF
        assertEquals(50, Main.m(30, 10));//while if, TT
        assertEquals(38, Main.m(34, 10));//while, TT
        assertEquals(34, Main.m(2, 10));//if TT
        assertEquals(-8, Main.m(31, 10));//while, TF
        assertEquals(-4, Main.m(3, 10));//else statement TF
        assertEquals(20, Main.m(30, 20));//while if, else FT
        assertEquals(12, Main.m(31, 30));//while, FF
        assertEquals(14, Main.m(2, 30));//else statement FT
        assertEquals(0,Main.m(5,10));   //else FF
    }

    @Test
    // create a test that ensures that each path from start to end is executed at least once
    public void m4() {
	// below is an example of a test for m4  
	// step through it and take note of what parts of the program is hits and doesn't hit
	// take note that this example test alone doesn't cover everything it should
        assertEquals(34, Main.m(2, 10));//if TT
        assertEquals(-4, Main.m(3, 10));//else statement TF
        assertEquals(14, Main.m(2, 30));//else statement FT
        assertEquals(0,Main.m(5,10));   //else FF
        assertEquals(50, Main.m(30, 10));//while if, TT
        assertEquals(38, Main.m(34, 10));//while, TT
        assertEquals(-8, Main.m(31, 10));//while, TF
        assertEquals(20, Main.m(30, 20));//while if, else FT
        assertEquals(12, Main.m(31, 30));//while, FF
    }


}
