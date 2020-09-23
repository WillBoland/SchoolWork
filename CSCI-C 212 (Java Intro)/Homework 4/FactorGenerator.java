/**
 * Finds all the factors of the number. Book never specified prime factors, so assume all factors
 */
public class FactorGenerator {
  int numberToFactor;
  
  public FactorGenerator(int numberToFactor) {
    this.numberToFactor = numberToFactor;
    
    int previous = 0; //Do all printing once factorGenerator is created
    while(hasMoreFactors(previous)) {
      previous = nextFactor(previous);
      System.out.println(previous);
    }
  }
  
  //finds ALL factors of a number
  private int nextFactor(int previousFactor) {
    for (int i = previousFactor + 1; i <= numberToFactor; i++) {
      if (numberToFactor % i == 0) {
        return i;
      }
    }
    return -1; //-1 signals end, but should never be returned
  }
  
  
  private boolean hasMoreFactors(int previousFactor) {
    if (previousFactor == numberToFactor) {
      return false;
    }
    return true;
  }
}
