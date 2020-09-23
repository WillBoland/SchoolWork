import java.util.Random;

public class NimComputer {
  boolean isSmart;
  private static Random randomGenerator = new Random();
  
  public NimComputer(boolean isSmart) {
    this.isSmart = isSmart;
  }
  
  /**
   * takes turn and returns the amount the computer took
   * @param pileSize the size of the pile at that stage of the game
   * @return the amount the computer took
   */
  public int takeTurn(int pileSize) {
    if (isSmart) {
      return playSmart(pileSize);
    }
    return playDumb(pileSize);
  }
  
  private int playSmart(int pileSize) {
    //int size = pileSize;
    for (int i = 1; i <= pileSize/2; i++) {
      int size = pileSize;
      if (size - i == 3 || size - i == 7 || size - i == 15 || size - i == 31 || size - i == 63) {
        return i;
      }
    }
    return randomGenerator.nextInt((pileSize/2-1)+1)+1;
  }
  
  private int playDumb(int pileSize) {
    return randomGenerator.nextInt((pileSize/2-1)+1)+1;
  }
}