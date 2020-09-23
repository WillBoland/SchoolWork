/*grade is a 74/100 because of errors and failure to get what the question needed.
I tried to sort using Arrays instead of Collections, which is what ArrayList needs.
I tried to use two variables under a different name by accident. I also had a useless variable up top.
It prints Goodybye if user enters a letter; however, if they hit enter it should wait until they enter a letter or number because I take the enter key as an acceptable input as long as they enter a double after, incase they just accidentally hit enter without entering a number.

Overall: It could have been way better. If I were to re-do this, I would have made a class with class methods, and then use another class to run and call upon those.
 */

import java.util.ArrayList;
                            //deleted import for Arrays because unused.
import java.util.Scanner;
import java.util.Collections; //import collections for sorting

public class QuestionThree {
    public static void main(String[] args) {
                                            //deleted useless variable
        Double smallest = Double.MAX_VALUE;
        Double largest = Double.MIN_VALUE;
        Double range = 0.0;
        Double sum = 0.0;

        ArrayList<Double> inputs = new ArrayList<>(); //should be Double not Doubles
        Scanner in = new Scanner(System.in);

        System.out.print("Enter a number: ");

        while(in.hasNextDouble()) {
            Double value = in.nextDouble();
            if(value > largest)
                largest = value;
            if(value < smallest)
                smallest = value;
            inputs.add(value);
            Collections.sort(inputs); //sort using Collections not Arrays.
            range = inputs.get(inputs.size() - 1) - inputs.get(0);
            sum += value;

            System.out.println("Max: " + largest + " Min: " + smallest + " Average: " + (sum/inputs.size()) + " Range: " + range); //used max and min instead of largest and smallest variables... oops.
            System.out.println(inputs);
            System.out.print("Enter Number: ");
        }
        System.out.println("Goodbye");
    }
}
