public class DiceGames {
  private int money = 0;
  private Die diceOne;
  private Die diceTwo;
  
  private int wins = 0;
  private int losses = 0;
  
  public DiceGames(int sidesForDice) {
    diceOne = new Die(sidesForDice);
    diceTwo = new Die(sidesForDice);
  }
  
  public void playGameOne() {
    for(int i = 0; i < 4; i++) {
      if(diceOne.roll() == 6) {
        wins += 1;
        money += 1;
        return;
      }
    }
    money -= 1;
    losses += 1;
  }
  
  public void playGameTwo() {
    for(int i = 0; i < 24; i++) {
      if(diceOne.roll() == 6 && diceTwo.roll() == 6) {
        wins += 1;
        money += 1;
        return;
      }
    }
    money -= 1;
    losses += 1;
  }
  
  public int getLosses() {
    return losses;
  }
  
  public int getWins() {
    return wins;
  }
  
  public int getMoney() {
    return money;
  }
}