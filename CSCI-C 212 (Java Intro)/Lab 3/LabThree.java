import java.math.BigDecimal;
import java.math.*;

/**
 * contains answers to Lab 3
 */
public class LabThree {
  public static void main(String[] args) {
    //1
    BigDecimal one = new BigDecimal("1");
    BigDecimal two = new BigDecimal("2");
    System.out.println(one.add(two)); //expected 3
    
    //2
    BigDecimal three = new BigDecimal("3");
    System.out.println(two.multiply(three)); //expected 6
    
    //3
    BigDecimal four = new BigDecimal("4");
    BigDecimal answer = new BigDecimal("0");
    answer = three.subtract(four);
    answer = two.subtract(answer);
    answer = one.subtract(answer);
    System.out.println(answer); //expected -2
    
    //4
    answer = one.subtract(two);
    answer = answer.subtract(three);
    answer = answer.subtract(four);
    System.out.println(answer); //expected -8
    
    //5
    BigDecimal five = new BigDecimal("5");
    answer = two.multiply(three);
    answer = answer.add(four.multiply(five));
    System.out.println(answer); //expected 26
    
    //6
    System.out.println("\n\t\\\"".length()); //expected 4
    
    //7
    System.out.println("tomato".charAt(2) - 'n'); //expected -1 due to char being a number
    
    //8
    System.out.println((char)('a' + 3)); //expected 'd'
    
    //9
    System.out.println(13 % 7); //expected 6
    
    //10
    System.out.println(5 % 7); //expected 5
    
    //11
    System.out.println(13 / 7); //expected 1
    
    //12
    System.out.println(5 / 7); //expected 0
    
    //13
    System.out.println(false && false || true); //expected true
    
    //14
    System.out.println(false && (false || true)); //expected false
    
    //15
    System.out.println(2/3 * 3); //expected 0
    
    //16
    System.out.println(3 * 2 / 3); //expected 2
    
    Boolean b = true; //default value for showing simplification
    int n = 0;
    //17
    //Simplified: b
    
    //18
    //true
    
    //19
    if (n == 0) {
      b = false;
    }
    b = true;
    
    //20
    b = ((n < 3) || (n > 5)); //most simplified
    System.out.println(b); //expected true
    
    //21
    if (n == 0) {
      b = false;
    }
    b = true;
    
    //22
    //Simplified: !b
    
    //23
    //n == 4;
    
    //24
    //n > 5;
    
    //25
    //false
    
    //26
    //n > 3
    
    //27
    //Compile-time error!!!
    
    //28
    int x = 18;
    int y = 10;
    y = 2; //AFTER the if statement
    
    //29
    y = 10;
    y = 10; //AFTER if statement
    
    //30
    y = 6;
    y = --y + y--; //expected 10
    
    //31
    //prints: 6
    
    //32
    //prints: 12
    
    //33
    //prints: 38
    
    //34
    //prints: 115
    
    //35
    //prints: 0
    
    //36
    //infinite
    
    //37
    //not infinite
    
    //38
    //not infinite
    
    //39
    //infinite
    
    //40
    boolean overlap = false;
    int distance = (Math.sqrt(Math.pow((circleTwo.x - circleOne.x), 2) + Math.pow((circleTwo.y - circleOne.y), 2))); //calculates distance between two points
    int overlapDistance = circleOne.radius + circleTwo.radius;
    if (distance <= overlapDistance) {
      overlap = true;
    }
  }
  
  //41
  //checks for when rectangles can't overlap by getting the corners (area) of both so we can see if they are above, or next to eachother
  boolean overlap = true;
  if (rectangleOne.x > (rectangleTwo.x + rectangleTwo.width) || (rectangleOne.x + rectangleOne.width) < rectangleTwo.x
        || rectangleOne.y < (rectangleTwo.y + rectangleTwo.height) || (rectangleOne.y + rectangleOne.height) > rectangleTwo.y)
  {
    overlap = false;
  }
}









