import java.awt.Graphics; //imports graphics
import javax.swing.JFrame; //imports JFrame for viewing

/**
 * Displays a boat
 */
public class BoatViewer {
  /**
   * main method
   * @param args not used
   */
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    int width = 500, height = 500;
    frame.setSize(width + 20, height + 40);
    
    Boat sailBoat = new Boat(width, height);
    frame.add(sailBoat);
    frame.setVisible(true); //Call visible after adding in order to call paint component
  }
}