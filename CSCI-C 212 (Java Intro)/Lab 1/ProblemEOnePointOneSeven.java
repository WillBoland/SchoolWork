import javax.swing.JOptionPane;

public class ProblemEOnePointOneSeven {
 //E1.17
  public static void main(String[] args) {
    String name = JOptionPane.showInputDialog("What is your name?"); //asks for user input
    String disregardedUserRequest = JOptionPane.showInputDialog("My name is Hal! What would you like me to do?");//asks user what they'd like me (Hal) to do
    System.out.println("I'm sorry, " + name + ". I'm afraid I can't do that."); //disregards their request, printing their name while referencing 2001 a space odyssey
  }
}