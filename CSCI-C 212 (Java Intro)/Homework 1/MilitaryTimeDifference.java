import java.util.Scanner; //imports Scanner class

/**
 * Finds the difference in hours and mintutes between two military times.
 */
public class MilitaryTimeDifference {
  
  /**
   * main function
   * @param args not used
   */
  public static void main(String[] args) {
    Scanner userInput = new Scanner(System.in); //new scanner object
    
    System.out.print("Enter the first time: ");
    String inputOne = userInput.nextLine(); //user's first inputted time
    
    System.out.print("Enter the second time: ");
    String inputTwo = userInput.nextLine(); //user's second inputted time
    
    int timeOne = Integer.parseInt(inputOne.substring(0, 2)); //Substrings the inputOne hour and parses into Int
    timeOne = (timeOne * 60) + Integer.parseInt(inputOne.substring(3)); //Converts the hours to minutes, then adds the minutes from input onto total minutes
    
    int timeTwo = Integer.parseInt(inputTwo.substring(0,2)); //same proccess as timeOne
    timeTwo = (timeTwo * 60) + Integer.parseInt(inputTwo.substring(3)); //same proccess as timeOne
    
    int difference = timeTwo - timeOne + 1440; //difference between timeTwo and timeOne, plus a whole day to account for next-day dates
    difference = difference % 1440; //Takes the remainder (modulo) of 1440 (which is 24 hours) in order to remove the day we added on, only if it is needed

    System.out.println(difference / 60 + " hours and " + difference % 60 + " minutes."); //prints the hours and minutes between the two times by turning minutes into hours and finding the remainder
  }
}