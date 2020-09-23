/*grade for this problem 1: 95/100
  grade for this problem 2: 93/100

I used .newInt instead of .nextInt, just like I had in problem one.
My comments used the wrong slash.
I don't print the array, but that is only because it specifies to fill, not print, the array.
The array is written over during the second for loop, but obviously could be held in another variable; however, the problem wanted both to be called "values"

Overall, these were two simple fixes. One was a commenting error and the other was using a function that doesn't exist.
 */
import java.util.Arrays;
import java.util.Random;

public class RandomArray {
    public static void main(String[] args) {
        int[] values = new int[10];
        Random rand = new Random();
        for(int i = 0; i < 10; i++) {
            values[i] = rand.nextInt(99) + 1; //1-100 inclusive;
        }

        //next problem;

        for(int i = 0; i < 10; i++) {
            int randNum = rand.nextInt(99) + 1;
            for(int x = 0; x < i; x++) {
                if(randNum == values[x]) {
                    randNum = rand.nextInt(99) + 1;
                    x = 0;
                }
            }
            values[i] = randNum;
        }
    }
}
