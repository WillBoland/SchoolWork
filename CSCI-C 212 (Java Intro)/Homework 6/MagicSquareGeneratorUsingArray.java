import java.util.Scanner;

public class MagicSquareGeneratorUsingArray {
    public static void main(String[] args) {

        System.out.print("Put in the number of rows for your magic square. Must be odd and positive: ");
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        while(size % 2 == 0 || size < 1) {
            System.out.print("Number must be odd and positive. Try again: ");
            size = in.nextInt();
        }

        int[][] square = new int[size][size]; // create array of desired size
        int i = size -  1; // set i
        int j = size/2; // and j to the next available position
        int k = 1; // set k to next available number to place
        while (k <= size * size) { // for as long as this is a valid number
            square[i][j] = k; // start by placing k then look for the new location of the next number (k + 1)
            int a = i + 1;  //increase row
            int b = j + 1;  //increase column

            //if a or b is equal to the size, set to 0.
            if(a == size)
                a = 0;
            if(b== size)
                b = 0;

            if (square[a][b] == 0) { // if the location is empty
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
    public static String toString(int[][] m) {
        String result = "\n";
        for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m[row].length; col++) {
                result = result + String.format( " %2d", m[row][col] );
                // also: https://www.cs.indiana.edu/classes/c212-dgerman/fall2015/backmatter.jpg
            }
            result = result + "\n";
        }
        return result; // + "\n";
    }
}
