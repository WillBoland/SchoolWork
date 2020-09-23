/**
 * Holds the current grade in numeric and number fr=ormat out of 0-4
 */
public class Grade {
  
  /**
   * Returns the letter grade for the given value
   * @param numericGrade the grade represented as a number 0-4
   * @return returns the letter grade of type String
   */
  public static String getNumericGrade(double numericGrade) {
    if (numericGrade > 4) {
      return "A+";
    } else if (numericGrade >= 3.85) {
      return "A";
    }
    else if (numericGrade >= 3.5) {
      return "A-";
    }
    else if (numericGrade >= 3.15) {
      return "B+";
    }
    else if (numericGrade >= 2.85) {
      return "B";
    }
    else if (numericGrade >= 2.5) {
      return "B-";
    }
    else if (numericGrade >= 2.15) {
      return "C+";
    }
    else if (numericGrade >= 1.85) {
      return "C";
    }
    else if (numericGrade >= 1.5) {
      return "C-";
    }
    else if (numericGrade >= 1.15) {
      return "D+";
    }
    else if (numericGrade >= 0.85) {
      return "D";
    }
    else if (numericGrade >= 0.35) {
      return "D-";
    } 
    else if (numericGrade < 0.35 && numericGrade >= 0) {
      return "F"; //if all above fails, return F
    }
    else {
      return "Invalid Input";
    }
  }
}
