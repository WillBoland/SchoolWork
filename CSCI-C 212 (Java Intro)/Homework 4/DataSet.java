//ADDS STANDARD DEVIATION FOR PROBLEM E6.2 TO EXISTING DATASET CLASS

/**
 * Calculates the largest, smallest, range, and average of given double inputs
 */
public class DataSet {
  private int count = 0;
  private double sum = 0;
  private double sumOfSquares = 0;
  private double largest = Double.MIN_VALUE; //set largest to the smallest possible value. if we set largest to 0, and all inputs are negative numbers, we will get incorrect output
  private double smallest = Double.MAX_VALUE; //set smallest to the largest possible double because if set to 0, and all inputs are > 0, we will get wrong output
  private double range = 0;
  
  /**
   * adds value to the dataset's list of values. Use this to add doubles to DataSet
   * @param value the double you want to add to DataSet
   */
  public void add(double value) {
    if (value > largest) {
      largest = value;
    }
    
    if (value < smallest) {
      smallest = value;
    }
    
    count += 1;
    sum += value;
    sumOfSquares += (value * value);
  }
  
  /**
   * returns the largest value in DataSet
   * @return the largest value
   */
  public double getLargest() {
    return largest;
  }
  
  /**
   * returns the smallest value in DataSet
   * @return the smallest value
   */
  public double getSmallest() {
    return smallest;
  }
  
  /**
   * returns the range of the values held in DataSet
   * @return the range value
   */
  public double getRange() {
    return largest - smallest;
  }
  
  /**
   * returns the average value in DataSet
   * @return the average value
   */
  public double getAverage() {
    return sum / count;
  }
  
  /**
   * returns how many doubles there are
   * @return the amount of doubles held
   */
  public int getCount() {
    return count;
  }
  
  /**
   * returns the standard deviation of the set
   * @return the standard deviation of the set
   */
  public double getStandardDeviation() {
    double standardDeviation = sumOfSquares - (1/count) * Math.pow(sum,2); //Numerator of fraction of whats in book
    standardDeviation = standardDeviation / (count - 1); //Denominator of fraction of whats in book
    return Math.sqrt(standardDeviation); //Lastly, the square root of the whole thing of whats in book...hopefully
  }
}
