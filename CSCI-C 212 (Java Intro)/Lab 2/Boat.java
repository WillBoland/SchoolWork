import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;

/**
 * Draws a boat
 */
public class Boat extends JComponent {
  int width, height;
  
  /**
   * constructs a Boat with given width and height
   * @param width the width of the boat
   * @param height the height of the boat
   */
  public Boat(int width, int height) {
    this.width = width;
    this.height = height;
  }
  
  /**
   * draws the boat
   * @param g the Graphics of the baot
   */
  public void paintComponent(Graphics g) {
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, 500, 500); //background color white
    
    Color brownColor = new Color(147, 112, 51); //brownColor for later reference
    g.setColor(brownColor); //sets color to brown
    g.fillRect(200, 250, 100, 15); //Body of ship
    
    Polygon lowerBodyPolygon = new Polygon //lowerBodyPolygon for the lower portion of the ship
      (new int[] {200, 300, 280, 220},
       new int[] {265, 265, 285, 285},
       4);
    g.fillPolygon(lowerBodyPolygon); //actually draws the polygon
    
    g.fillRect(240, 160, 5, 100); //pole for sail
    
    g.setColor(Color.GRAY); //sets color Gray for Sail
    Polygon sailPolygon = new Polygon //sailPolygon for the sail...
      (new int[] {243, 243, 300},
       new int[] {165, 235, 235},
       3);
    g.fillPolygon(sailPolygon); //actually draws the sailPolygon I made
  }
}