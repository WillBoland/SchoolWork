public class ProblemROnePointNine {
  public static void main(String[] args) {
    
    //NOTE: Quotation marks should be excluded, it is just to show what exactly should be printed
    
    //R1.9
    //Compile-Time error should state that println has no method that allows two arguments to be passed of type string
    System.out.println("Hello", "World!"); //Error is as expected.
    System.out.println("Hello, World!");//This is how we should fix it, assuming you want the comma and space to also be printed
    //This will now print "Hello, World!"
    
  }
}