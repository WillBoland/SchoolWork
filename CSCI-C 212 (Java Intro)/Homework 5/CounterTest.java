//R3.17

public class CounterTest {
  public static void main(String[] args) {
    Counter countMachine = new Counter(5);
    System.out.println(countMachine.getValue()); //tests the creation with value present as well as getValue() EXPECTED: 5
    
    countMachine.click();
    System.out.println(countMachine.getValue()); //tests click method. EXPECTED: 6
    
    countMachine.unclick();
    System.out.println(countMachine.getValue()); //tests unclick method. EXPECTED: 5
    
    countMachine.reset();
    System.out.println(countMachine.getValue()); //tests reset method. EXPECTED: 0
  }
}