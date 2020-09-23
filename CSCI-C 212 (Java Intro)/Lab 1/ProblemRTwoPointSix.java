public class ProblemRTwoPointSix {
  //R2.6
  public static void main(String[] args) {
    int mystery = 1;//mystery is 1
    mystery = mystery + 1;//mystery is 2
    int mystery = 1 - 2 * mystery; //ERROR: Variable is already instantiated. We should either remove int at begining to assign mystery new value or assing a new variable
  }
}