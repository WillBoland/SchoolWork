import java.util.ArrayList;
import java.util.Scanner;

public class MagicSquareGeneratorUsingArrayList {
    public static void main(String[] args) {

        System.out.print("Put in the number of rows for your magic square. Must be odd and positive: ");
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        while(size % 2 == 0 || size < 1) {
            System.out.print("Number must be odd and positive. Try again: ");
            size = in.nextInt();
        }

        //SET ALL THINGS TO ZERO-----------------------------------------------
        ArrayList<Integer> row = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> square = new ArrayList<ArrayList<Integer>>(); // create array of desired size
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                row.add(0);
            }
            square.add(row);
            row = new ArrayList<Integer>();
        }

        int i = size -  1; // set i
        int j = size/2; // and j to the next available position
        int k = 1; // set k to next available number to place
        while (k <= size * size) { // for as long as this is a valid number
            square.get(i).set(j, k); // start by placing k then look for the new location of the next number (k + 1)
            int a = i + 1;  //increase row
            int b = j + 1;  //increase column

            //if a or b is equal to the size, set to 0.
            if(a == size)
                a = 0;
            if(b== size)
                b = 0;

            if (square.get(a).get(b) == 0) { // if the location is empty
                i = a; // you have found your new i
                j = b; // and new j
            } else { // if not you need to go up one position
                i = i - 1; // so forget a and b and just decrement i
            }
            k = k + 1; // get the next number to be placed
        }

        System.out.println(toString(square));
    }

    //borrow dgerman's to string.
    public static String toString(ArrayList<ArrayList<Integer>> m) {
        String result = "\n";
        for (int row = 0; row < m.size(); row++) {
            for (int col = 0; col < m.get(row).size(); col++) {
                result = result + String.format( " %2d", m.get(row).get(col) );
                // also: https://www.cs.indiana.edu/classes/c212-dgerman/fall2015/backmatter.jpg
            }
            result = result + "\n";
        }
        return result; // + "\n";
    }
}
