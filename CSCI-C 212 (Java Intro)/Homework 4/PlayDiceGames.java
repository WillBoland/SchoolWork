public class PlayDiceGames {
  public static void main(String[] args) {
    DiceGames gameOne = new DiceGames(6); //for game one
    DiceGames gameTwo = new DiceGames(6); //for game two
    
    //plays game one a million times. 0 is played up to and including 999999, therefor a total of one million times
    for(int i = 0; i < 1000000; i++) {
      gameOne.playGameOne();
    }
    System.out.println("Wins: " + gameOne.getWins());
    System.out.println("Losses: " + gameOne.getLosses());
    System.out.println("Money: " + gameOne.getMoney());
    
    for(int i = 0; i < 1000000; i++) {
      gameTwo.playGameTwo();
    }
    System.out.println("\nWins: " + gameTwo.getWins());
    System.out.println("Losses: " + gameTwo.getLosses());
    System.out.println("Money: " + gameTwo.getMoney());
    
  }
}
