import java.util.Scanner;

public class FactorPrinter {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.print("Enter an int to find the factor: ");
    int input = Integer.parseInt(in.nextLine());
    FactorGenerator factory = new FactorGenerator(input);
  }
}
