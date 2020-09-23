import java.util.Scanner;

public class PrimePrinter {
  public static void main(String[] args) {
    System.out.print("Number to find prime numbers up to: ");
    Scanner in = new Scanner(System.in);
    PrimeGenerator generator = new PrimeGenerator(in.nextInt());
  }
}
