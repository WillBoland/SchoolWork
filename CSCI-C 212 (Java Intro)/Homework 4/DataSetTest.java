//NOW TESTS E6.2

import java.util.Scanner;

public class DataSetTest {
  public static void main(String[] args) {
    DataSet data = new DataSet();
    Scanner in = new Scanner(System.in);
    System.out.print("Enter floating-point value, type \"exit\" to exit");
    
    //while doubles are entered, keep going
    while (in.hasNextDouble()) {
      double currentValue = in.nextDouble();
      data.add(currentValue);
    }
    
    System.out.println("Average: " + data.getAverage());
    System.out.println("Range: " + data.getRange());
    System.out.println("Smallest: " + data.getSmallest());
    System.out.println("Largest: " + data.getLargest());
    System.out.println("Standard Deviation: " + data.getStandardDeviation());
    System.out.println("Count: " + data.getCount());
  }
}
