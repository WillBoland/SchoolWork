//R3.18

public class CarTest {
  public static void main(String[] args) {
    Car myHybrid = new Car(50); //50 miles per gallon
    myHybrid.addGas(20); //Tank holds 20 gallons of fuel now
    myHybrid.drive(100); //Drives 100 miles
    myHybrid.addGas(13); //Tank has 13 gallons + previous amount after driving 100 miles
    myHybrid.drive(300); //Drives 300 miles
    
    System.out.println("Expected gas remaining: 25 Gallons");
    System.out.println("Actual gas remaining: " + myHybrid.getGasInTank());
  }
}