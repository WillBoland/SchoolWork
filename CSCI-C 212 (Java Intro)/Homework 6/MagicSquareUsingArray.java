import java.util.Arrays;
import java.util.Scanner;

public class MagicSquareUsingArray {
    public static void main(String[] args) {
        int[][] magicSquare = new int[4][4]; //the magic square grid

        Scanner in = new Scanner(System.in);
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                System.out.print("Input number for row " + (i+1) + ", column " + (j+1) + ": ");
                magicSquare[i][j] = in.nextInt();
            }
        }

        //prints what the square looks like
        System.out.println(toString(magicSquare));

        System.out.println("Is your square a magic square: " + checkMagicSquare(magicSquare));
    }

    //checks if the 4X4 is a magic number. It IS if they use 1,2...16 once, and sums of rows, columns, diagnols are equal to 34 (sum)
    static boolean checkMagicSquare(int[][] magicSquare) {
        final int SUM_OF_NUMBERS = 34; //34 because a 4X4 magic square has to be 34 on diags, rows, columns.

        int[] numbersFromMagicSquare = new int[16]; //to check for duplicate numbers easier
        int index = 0;
        //adds all numbers to the list of numbers
        for(int i = 0; i < 4; i++) {
            for(int number: magicSquare[i]) {
                numbersFromMagicSquare[index] = number;
                index += 1;
            }
        }
        Arrays.sort(numbersFromMagicSquare); //sorts to check for duplicates easier
        //checks for duplicates inside of magic square by using the sorted list of inserted numbers.
        for(int i = 0; i < numbersFromMagicSquare.length - 2; i++) {
            if(numbersFromMagicSquare[i] == numbersFromMagicSquare[i + 1]) {
                System.out.println("Failed because of duplicated numbers in magic square.");
                return false;
            }
        }

        //checks to see if a number is larger than 16 or smaller than 1.
        for(int number: numbersFromMagicSquare) {
            if(number < 1 || number > 16) {
                System.out.println("Failed because numbers out of the range [1, 16].");
                return false;
            }
        }

        //if the sum of row or sum of column doesn't equal 34, return false
        for(int i = 0; i < 4; i++) {
            int sumOfRow = 0;
            int sumOfColumn = 0;
            for(int j = 0; j < 4; j++) {
                sumOfRow += magicSquare[i][j];
                sumOfColumn += magicSquare[j][i];
            }
            if(sumOfRow != SUM_OF_NUMBERS || sumOfColumn != SUM_OF_NUMBERS) {
                System.out.println("Failed because the sum of row or column is wrong.");
                return false;
            }
        }

        //checks to see if the first diagnol is good
        int sum = 0; //sum for the diagnols.
        for(int i = 0; i < 4; i++) {
            sum += magicSquare[i][i];
        }
        if(sum != SUM_OF_NUMBERS) {
            System.out.println("Failed because the sum of first diagnol is wrong.");
            return false;
        }

        //checks to see if the reverse diagnol is true
        sum = 0;
        index = 3; //index for reverse.
        for(int i = 0; i < 4; i++) {
            sum += magicSquare[i][index];
            index -= 1;
        }
        if(sum != SUM_OF_NUMBERS) {
            System.out.println("Failed because the sum of reverse diagnol is wrong.");
            return false;
        }

        return true; //if it passes all fail cases
    }



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
