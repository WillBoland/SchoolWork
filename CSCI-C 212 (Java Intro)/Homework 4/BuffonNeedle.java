import java.util.Random;

public class BuffonNeedle {
  private double hits = 0; //private so there is no accidental manipulation in test class
  private double tries = 0; //same as above
  
  public void dropNeedle() {
    Random randomGenerator = new Random();
    double yLow = randomGenerator.nextDouble() * 2; //random low between 0-2
    double angle = Math.toRadians(randomGenerator.nextDouble() * 180); //random angle between 0-180 degrees then to radians
    
    double yHigh = yLow + Math.sin(angle); //the high part of the needle
    
    if (yHigh >= 2) {
      hits += 1;
    }
    tries += 1;
  }
  
  public double getQuotient() {
    return hits/tries;
  }
}