import java.util.Scanner; 
import java.util.Random;

class LabSix {
  public static void main(String[] args) {
    int[] numbers = new int[0]; 
    Scanner s = new Scanner(System.in); 
    System.out.print("Enter: "); 
    String line = s.nextLine();     
    while (! line.toLowerCase().equals("bye")) {
      int number = Integer.parseInt(line); 
      numbers = LabSix.copyOf(numbers, numbers.length+1); 
      numbers[numbers.length-1] = number;
      System.out.println(LabSix.toString(numbers)); 
      System.out.print("Enter: "); 
      line = s.nextLine();     
    }
    LabSix.sort( numbers ); 
    System.out.println(LabSix.toString(numbers)); 
    s.close(); 
  }
  

  public static String toString(int[] arr) {
    if(arr.length == 0) { return "[]"; } //if the array is empty, it looks like this
    
    String arrayInString = "["; //start of string for array always looks like this.
    for(int i = 0; i < arr.length-1; i++) { //iterate through the array, excluding last element because we handle that at the end
      arrayInString += arr[i] + ", ";
    }
    return arrayInString + arr[arr.length-1] + "]"; //finally, finish it all off with the last element and a ]
  }
  

  public static int[] copyOf(int[] arr, int newLength) {
    int[] newArr; //new array for holding a copy, but declare size only after we determine if newLength is negative, shorter, or larger.
    
    if(newLength >= arr.length) { //if the length is same or larger, iterate only the length of the original array. all others filled with zero due to construction.
      newArr = new int[newLength];
      for(int i = 0; i < arr.length; i++) {
        newArr[i] = arr[i];
      }
    } else { //if it is shorter, check if negative.
      try {
      newArr = new int[newLength]; // if negative value, print exception and return original array unchanged.
      } catch(NegativeArraySizeException error) {
        System.out.println("Error: Negative number given for new length. Original array has been returned.");
        return arr;
      }
      for(int i = 0; i < newLength; i++) { //iterate and go only till the new length runs out
        newArr[i] = arr[i];
      }
    }
    return newArr; //return the modified array.
  }
  

  public static void sort(int[] arr) {
    for(int i = 0; i < arr.length; i++) { //iterate through every element of the array.
      for(int x = i + 1; x < arr.length; x++) { //iterate every element ahead of the already iterating array, to see if the previous element is larger or not.
        if(arr[i] > arr[x]) { //if the element before is larger than the one after
          int greaterValue = arr[i]; //store greater value to switch
          arr[i] = arr[x]; //switch places
          arr[x] = greaterValue; //finish switching
        }
      }
    }
  }
}
