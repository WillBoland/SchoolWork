import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;

/**
 * Draws a boat
 */
public class AdjustableBoat extends JComponent {
  int x, y, width, height;
  
  /**
   * constructs a Boat with given width and height
   * @param width the width of the boat
   * @param height the height of the boat
   */
  public AdjustableBoat(int x, int y,int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }
  
  /**
   * scales the boat to desired dimentions
   * @param value value desired for scale
   * @return int returns the value needed for scale
   */
  int scale(int value) {
    return (int) (this.width / 500.0 * value);
  }
  
  /**
   * draws the boat
   * @param g the Graphics of the baot
   */
  public void draw(Graphics g) {
    
    Color brownColor = new Color(147, 112, 51); //brownColor for later reference
    g.setColor(brownColor); //sets color to brown
    g.fillRect(x + scale(200), y + scale(250), scale(100), scale(15)); //Body of ship
    
    Polygon lowerBodyPolygon = new Polygon //lowerBodyPolygon for the lower portion of the ship
      (new int[] {x + scale(200), x + scale(300), x + scale(280), x + scale(220)},
       new int[] {y + scale(265), y + scale(265), y + scale(285), y + scale(285)},
       4);
    g.fillPolygon(lowerBodyPolygon); //actually draws the polygon
    
    g.fillRect(x + scale(240), y + scale(160), scale(5), scale(100)); //pole for sail
    
    g.setColor(Color.GRAY); //sets color Gray for Sail
    Polygon sailPolygon = new Polygon //sailPolygon for the sail...
      (new int[] {x + scale(243), x + scale(243), x + scale(300)},
       new int[] {y + scale(165), y + scale(235), y + scale(235)},
       3);
    g.fillPolygon(sailPolygon); //actually draws the sailPolygon I made
  }
}