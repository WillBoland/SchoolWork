/**
 * Student that has a name and quizes with scores
 */
public class Student {
  private String studentName; //the name of the student
  private int quizzesTaken; //the number of quizzes taken by the student
  private int totalScore; //the total score of all the quizzes
  
  /**
   * Creates a student with a name given
   * @param name the name the student is given
   */
  public Student(String name) {
    studentName = name;
  }
  
  /**
   * gets the name of the student
   * @return returns the name of the student
   */
  public String getName() {
    return studentName;
  }
  
  /**
   * adds a quiz and score to the students "file"
   * @param score the score the student recieved on the quiz
   */
  public void addQuiz(int score) {
    quizzesTaken += 1;
    totalScore += score;
  }
  
  /**
   * gets the total score
   * @return returns the total score the student has recieved of all the quizzes
   */
  public int getTotalScore() {
    return totalScore;
  }
  
  /**
   * returns the average score the student recieves on quizzes
   * @returns the average score, returns NaN if no tests taken
   */
  public double getAverageScore() {
    return (double)totalScore/quizzesTaken;
  }
}
