//R3.17

public class Counter {
  private int value; //holds value of the amount of clicks
  
  public Counter(int initialValue) {
    value = initialValue;
  }
  
  //adds one to value
  public void click() {
    value = value + 1;
  }
  
  //removes one from value
  public void unclick() {
    value = value - 1;
  }
  
  //returns number of clicks (value)
  public int getValue() {
    return value;
  }
  
  //resets clicks (value) to 0
  public void reset() {
    value = 0;
  }
}