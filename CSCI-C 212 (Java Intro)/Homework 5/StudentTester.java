//P3.7

public class StudentTester {
  public static void main(String[] args) {
    Student will = new Student("Will"); //creates student with name "Will"
    will.addQuiz(100); //tests addQuiz()
    will.addQuiz(50);
    will.addQuiz(200);
    will.addQuiz(155);
    
    String name  = will.getName(); //tests getName()
    int totalScore = will.getTotalScore(); //tests getTotalScore()
    double averageScore = will.getAverageScore(); //tests getAverageScore()
    
    System.out.println(name); //expected: Will
    System.out.println(totalScore); //expected: 505
    System.out.println(averageScore); //expected: 126.25
  }
}
