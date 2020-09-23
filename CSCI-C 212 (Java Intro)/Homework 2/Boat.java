import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;

/**
 * Draws a boat
 */
public class Boat extends JComponent {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    int width, height;
    width = height = args.length >= 1 ? Integer.parseInt(args[0]) : 500; //gets size from command line, setting width and height = 500 by default
    frame.setSize(width+20, height+40);
    Boat sailboat = new Boat(width, height);
    frame.add(sailboat);
    frame.setVisible(true);
  }
    
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
  public void paintComponent(Graphics g) {
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, scale(500), scale(500)); //background color white
    
    Color brownColor = new Color(147, 112, 51); //brownColor for later reference
    g.setColor(brownColor); //sets color to brown
    g.fillRect(scale(200), scale(250), scale(100), scale(15)); //Body of ship
    
    Polygon lowerBodyPolygon = new Polygon //lowerBodyPolygon for the lower portion of the ship
      (new int[] {scale(200), scale(300), scale(280), scale(220)},
       new int[] {scale(265), scale(265), scale(285), scale(285)},
       4);
    g.fillPolygon(lowerBodyPolygon); //actually draws the polygon
    
    g.fillRect(scale(240), scale(160), scale(5), scale(100)); //pole for sail
    
    g.setColor(Color.GRAY); //sets color Gray for Sail
    Polygon sailPolygon = new Polygon //sailPolygon for the sail...
      (new int[] {scale(243), scale(243), scale(300)},
       new int[] {scale(165), scale(235), scale(235)},
       3);
    g.fillPolygon(sailPolygon); //actually draws the sailPolygon I made
  }
}