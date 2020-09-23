/*grade for this problem is: 95/100.
-5 total because I used rand.newInt instead of .nextInt and I tried initializing an ArrayList of type Integers instead of type Integer. These were simple errors.
The program prints nothing if the user inputs a negative value. I figure this is acceptable according to the question.
 */

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class MatrixPrinter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input Size: ");
        int size = in.nextInt();
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>(); //used integers instead of integer. Same for rest of code.
        ArrayList<Integer> row = new ArrayList<>();
        Random rand = new Random();

        for(int i = 0; i < size; i++) {
            for(int x = 0; x < size; x++) {
                int num = rand.nextInt(100) - 50; //used .newInt instead of .nextInt
                row.add(num);
            }
            System.out.println(row);
            matrix.add(row);
            row = new ArrayList<Integer>();
        }
    }
}
