import javax.swing.JOptionPane;

public class ProblemEOnePointOneSix {
  //E1.16
  public static void main(String[] args) {
    String name = JOptionPane.showInputDialog("What is your name?"); //asks for user input
      System.out.println("Hello, " + name + "!"); //we can use "+" to combine strings together within the print statement
  }
}