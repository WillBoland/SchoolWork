import java.util.Random;

//problem 6.10 includes classes: Die, DiceGames, PlayDiceGames
public class Die {
  int numberOfSides;
  
  public Die(int sides) {
    numberOfSides = sides;
  }
  
  public int roll() {
    Random generator = new Random();
    
    return generator.nextInt(numberOfSides) + 1;
  }
}
