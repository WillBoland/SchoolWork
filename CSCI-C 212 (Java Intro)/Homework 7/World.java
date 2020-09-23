import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * World provides an interface for all games. all games need to draw, update, and handle keyEvents (not mouse clicks). may change later
 */
interface World {
  public void draw(Graphics g); // drawing
  public void update(); // time event handler 
  public void keyHandler(KeyEvent e); // key event handler
  public boolean gameOver(); //game over
} 