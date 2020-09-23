import javax.swing.Timer; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.JComponent; 
import java.awt.Graphics; 
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * BigBang takes a world and is the container for it. BigBang should work for any world, such as tetris (mentioned 10/15/2018)
 */
public class BigBang extends JComponent implements ActionListener, KeyListener, MouseListener {
  private World world; //the world
  private Timer timer; //timer for updating scene
  private int counter; //not used but would be updated everytime an action is performed
  
  public void mouseEntered(MouseEvent e) { } //required for MouseListener interface (not used)
  public void mouseExited(MouseEvent e) { } //required for MouseListener interface (not used)
  public void mousePressed(MouseEvent e) { } //required for MouseListener interface (not used)
  public void mouseReleased(MouseEvent e) { } //required for MouseListener interface (not used)
  public void mouseClicked(MouseEvent e) { } //required for MouseListener interface (not used)

  //required for KeyListener Inteferface
  public void keyPressed(KeyEvent e) { 
    this.world.keyHandler(e);
  } 
  public void keyReleased(KeyEvent e) { } //required for KeyListener Inteferface (not used)
  public void keyTyped(KeyEvent e) { } //required for KeyListener Inteferface (not used)
  
  //used to paint (draw) the world
  public void paintComponent(Graphics g) {
    this.world.draw(g); 
  }
  
  public void actionPerformed(ActionEvent e) {
    // System.out.println( counter++ ); 
    this.world.update(); //updates world.
    if(world.gameOver()) {
      timer.stop();
    }
    this.repaint(); //redraws/paints the scene
  }
  
  /**
   * instantiates BigBang given a world. default time for timer is 300
   */
  public BigBang(World world) {
    this.world = world;
    this.timer = new Timer(300, this); 
  }
  
  /**
   * instantiates a bigbang given a rate for the timer and a world
   */
  public BigBang(int rate, World world) {
    this.world = world;
    this.timer = new Timer(rate, this); 
  }
  
  /**
   * starts the timer for updating
   */
  public void start() {
     this.timer.start(); 
  }
}