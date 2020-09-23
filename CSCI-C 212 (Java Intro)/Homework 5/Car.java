//E3.12

/**
 * Car that can drive, add gas, and have a fuel effieciency in Miles/Gallon
 */
public class Car {
  private double fuelEfficiency; // Miles per Gallon
  private double gasInTank; //the amount of gas in tank
  
  /**
   * Creates a Car with a fuel effieciency of Miles/Gallon
   * @param fuelEfficiency the fuel efficiency of the car
   */
  public Car(double fuelEfficiency) {
    this.fuelEfficiency = fuelEfficiency;
    gasInTank = 0;
  }
  
  /**
   * Drives the car a certain amount of miles
   * @param miles the miles you want to drive. Can never be more than how much fuel it will consume
   */
  public void drive(double miles) {
    gasInTank -= miles/fuelEfficiency;
  }
  
  /**
   * Returns the amount of gas left in the tank
   * @return the amount of gas left in the tank, given as type double
   */
  public double getGasInTank() {
    return gasInTank;
  }
  
  /**
   * adds gas to the tank of the car
   * @param amount of gas to add to the car
   */
  public void addGas(double amount) {
    gasInTank += amount;
  }
}