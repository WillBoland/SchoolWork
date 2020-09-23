public class PrimeGenerator {
  int primeNumber;
  
  public PrimeGenerator(int primeNumber) {
    this.primeNumber = primeNumber;
    
    for(int i = 2; i <= primeNumber; i++) {
      int prime = nextPrime(i);
      if (prime != -1) {
        System.out.println(i);
      }
    }
  }
  
  private int nextPrime(int numberToCheck) {
    if(isPrime(numberToCheck)) {
      return numberToCheck;
    }
    return -1;
  }
  
  private boolean isPrime(int number) {
    for(int i = 2; i < number; i++) {
      if (number%i == 0) {
        return false;
      }
    }
    return true;
  }
}
