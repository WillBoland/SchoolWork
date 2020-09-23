import java.awt.Graphics;
import javax.swing.JFrame; 
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyEvent;

/**
 * holds the logic of the game, including updating the scene, handling the snake using snake methods, etc.
 */
public class Game implements World {
  
  Food food; //the food the snake eats. currently composed of circles
  Snake snake; //snake for the game. see class Snake
  int times;   //counter that counts up... not needed.
  
  /**
   * instantiates Game with a new snake moving eastbound as well as creates food
   */
  public Game() {
    this.snake = new Snake(50, 80, "east");  
    this.food = new Food(); 
  }
  
  /**
   * draws the snake, food, and the text according the World interface
   */
  public void draw(Graphics g) { 
    Font font = new Font("SansSerif", Font.BOLD, 20);
    g.setFont(font);
    g.setColor(Color.WHITE);
    g.drawString(this.times++ + "", 20, 40); 

    g.drawString("(" + this.snake.x() + ", " + this.snake.y() + ")" , 140, 180); 

    this.snake.draw(g);
    this.food.show(g); 
    
  }
  
  /**
   * moves the snake according to the World interface.-----------------------------------------------------------------------
   */
  public void update() { 
    this.snake.move();
    
    double distance = Math.sqrt( Math.pow(snake.centerX() - food.centerX(), 2) + Math.pow(snake.centerY() - food.centerY(), 2) );
    if(distance <= 20) {
      this.food = new Food();
      this.snake.addToBody();
    }
    
  }
  
  /**
   * determines if game is over and time should stop
   */
  public boolean gameOver() {
    return (snake.collisionWithSelf() || snake.outOfBounds());
  }
  
  /**
   * handles key presses according to World interface. sets the direction of the snake
   */
  public void keyHandler(KeyEvent e) {  
    int EAST = 39; 
    // System.out.println( e.getKeyCode() ); 
    int code = e.getKeyCode(); 
    if (code == 37) { // West 
      this.snake.setDirection("west"); 
    } else if (code == KeyEvent.VK_UP) { // North or 38 (not VK_KP_UP      
      this.snake.setDirection("north"); 
    } else if (code == EAST) { 
      this.snake.setDirection("east"); 
    } else if (code == 40) { // South 
      this.snake.setDirection("south"); 
    } else {
      this.snake.setDirection("no direction"); 
      // System.out.println( KeyEvent.VK_UP );
    }
  }
  
  /**
   * makes a new bigbang with a new Game(), sets the frame and key listener then starts the timer.
   */
  public static void main(String[] args) {
    BigBang b = new BigBang( 50, new Game() ); 
    JFrame f = new JFrame(); 
    f.add(b); 
    f.addKeyListener(b); 
    f.setSize(400, 400); 
    f.setVisible(true); 
    b.start(); 
  }
  
}
