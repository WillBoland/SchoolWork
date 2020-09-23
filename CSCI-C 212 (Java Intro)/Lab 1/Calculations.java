import java.math.BigDecimal;

public class Calculations {
  //Calculations at end of Lab 01
  public static void main(String[] args) {
    //Instantiates objects according to String inside BigDecimal
    BigDecimal one = new BigDecimal("1");
    BigDecimal two = new BigDecimal("2");
    BigDecimal three = new BigDecimal("3");
    BigDecimal four = new BigDecimal("4");
    BigDecimal five = new BigDecimal("5");
    
    System.out.println(one.add(two)); //prints 3
    System.out.println(two.multiply(three)); //prints 6
    
    BigDecimal result = three.subtract(four); //start with inside ()'s first
    result = two.subtract(result); //move to next set
    result = one.subtract(result); //final operation
    
    System.out.println(result); //prints -2
    System.out.println(one.subtract(two.subtract(three.subtract(four)))); //ALTERNATIVE WAY instead of using result. prints -2
    
    result = one.subtract(two); //reassigns result
    result = result.subtract(three);
    result = result.subtract(four);
    System.out.println(result); //prints -8
    
    BigDecimal partOne = two.multiply(three);
    BigDecimal partTwo = four.multiply(five);
    result = partOne.subtract(partTwo);
    System.out.println(result); //prints -14
  }
}