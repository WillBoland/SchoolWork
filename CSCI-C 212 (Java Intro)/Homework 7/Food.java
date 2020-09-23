import java.awt.Graphics; 
import java.awt.Color;
import java.util.Random;

/**
 * food for the snake to eat. I decided to change this to a singular piece of food because i believe that Game should handle an array of food.
 */
public class Food {
  private int x, y;
  private Circle foodItem;
  final private int RADIUS = Snake.R;
  /**
   * makes food using randomly generated cordinates
   */
  public Food() {
    this.foodItem = new Circle((int) (25 + new Random().nextInt(350)), //using random because I prefer it
                               (int) (25 + new Random().nextInt(350)), //using random because i prefer it
                               RADIUS, 
                               Color.YELLOW, 
                               Color.GREEN);
    this.x = foodItem.x();
    this.y = foodItem.y();
  }
  
  /**
   * shows the food on the screen
   */
  public void show(Graphics g) {
      foodItem.draw(g); 
  }
  
  /**
   * returns the x cordinate of the food
   */
  public int x() {
    return x;
  }
  
    /**
   * returns the center of the snake head, for better collision detection
   */
  public int centerX() { return this.x + RADIUS; }
  
  /**
   * returns the y cordinate of the food
   */
  public int y() {
    return y;
  }
  
    /**
   * returns the center of the snake head, for better collision detection
   */
  public int centerY() { return this.y + RADIUS; }
}