import java.util.Random;

public class LabSixTest {
  public static void main(String[] args) {
     //some more test cases--------------------------------------------------------------------------------
    int[] arr = new int[0];
    
    //tests 1998 cases. mainly for copy of and for toString.
    for(int i = -999; i <= 999; i++) {
      arr = LabSix.copyOf(arr, arr.length+1);
      arr[arr.length-1] = i;
    }
    System.out.println(LabSix.toString(arr));
    
    //I feel like this adequetly tests the sorting method by generating 1998 random numbers and then sorting.
    Random rand = new Random();
    arr = new int[0];
    for(int i = -999; i <= 999; i++) {
      arr = LabSix.copyOf(arr, arr.length+1);
      arr[arr.length-1] = rand.nextInt();
    }
    LabSix.sort(arr);
    System.out.println(LabSix.toString(arr));
  }
}
