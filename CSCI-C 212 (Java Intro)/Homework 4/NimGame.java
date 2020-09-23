import java.util.Random;
import java.util.Scanner;

//Assume valid input
public class NimGame {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    Random randomGenerator = new Random();
    int pileSize = randomGenerator.nextInt(91) + 10; // 10 =< pileSize =< 100
    boolean humanIsFirst = randomGenerator.nextInt(2) == 0;
    NimComputer computer = new NimComputer(randomGenerator.nextInt(2) == 1); //creates computer and determines if smart
    
    //human first
    if (humanIsFirst) {
      while(pileSize != 0) {
        
        //If pile size is 1, there is only one possible move left, therefor player loses. Same for computer.
        if (pileSize == 1) {
          System.out.println("You Lost!!!");
          break;
        }
        
        System.out.println("The pile size is: " + pileSize);
        System.out.print("Enter a number between 1 and " + pileSize/2 + ": ");
        int humanInput = Integer.parseInt(in.nextLine());
        //make sure they don't cheat!
        while(humanInput != 1 && (humanInput > pileSize/2 || humanInput <= 0)) {
          System.out.print("Try a valid number: ");
          humanInput = Integer.parseInt(in.nextLine());
        }
        pileSize -= humanInput;
        
        if (pileSize == 1) {
          System.out.println("You Won!!!");
          break;
        }
        System.out.println("The pile size is now: " + pileSize);
        int computerTookOut = computer.takeTurn(pileSize);
        pileSize -= computerTookOut;
        System.out.println("The computer took out: " + computerTookOut);
        
      }
    } else {
      //computer first
      while(pileSize != 0) {
        if (pileSize == 1) {
          System.out.println("You Won!!!");
          break;
        }
        System.out.println("The pile size is: " + pileSize);
        int computerTookOut = computer.takeTurn(pileSize);
        pileSize -= computerTookOut;
        System.out.println("The computer took out: " + computerTookOut);
        
        if (pileSize == 1) {
          System.out.println("You Lost!!!");
          break;
        }
        
        System.out.println("The pile size is: " + pileSize);
        System.out.print("Enter a number between 1 and " + pileSize/2 + ": ");
        int humanInput = Integer.parseInt(in.nextLine());
        //make sure they don't cheat!
        while(humanInput != 1 && (humanInput > pileSize/2 || humanInput <= 0)) {
          System.out.print("Try a valid number: ");
          humanInput = Integer.parseInt(in.nextLine());
        }
        pileSize -= humanInput;
      }
    }
  }
}
