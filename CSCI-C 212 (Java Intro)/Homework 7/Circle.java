import java.awt.Graphics;
import java.awt.Color;

/**
 * circle that is drawn given graphics. has x, y, and radius
 */
public class Circle {
  private int x, y, radius; //the x, y cordinate and the radius
  Color inner, border; //the inner color and the border color
  
  /**
   * gets the current x value of the circle
   */
  public int x() { return this.x; } 
  
  /**
   * gets the current y value of the circle
   */
  public int y() { return this.y; } 
  
  /**
   * creates a circle with a give (x, y) pair, radius, and inner/outer colors
   */
  public Circle(int x, int y, int r, Color inner, Color border) {
    this.x = x;
    this.y = y; 
    this.radius = r;
    this.inner = inner;
    this.border = border;
  }
  
  /**
   * draws the circle given graphics... USED IN SNAKE for instance
   */
  public void draw(Graphics g) {
    g.setColor(this.inner); 
    g.fillOval(this.x - this.radius, this.y - this.radius, 2 * this.radius, 2 * this.radius);  
    g.setColor(this.border); 
    g.drawOval(this.x - this.radius, this.y - this.radius, 2 * this.radius, 2 * this.radius);  
  }
  
  /**
   * moves the circle to the given x, y.
   */
  public void moveTo(int x, int y) {
    this.x = x;
    this.y = y; 
  }
}