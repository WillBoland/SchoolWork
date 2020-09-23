import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;

public class Boats extends JComponent {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setSize(500, 500);
    frame.add(new Boats());
    frame.setVisible(true);
    frame.add(new Boats());
  }
  
  AdjustableBoat a, b, c, d, e;
  
  public Boats() {
    this.a = new AdjustableBoat(10, 10, 99, 200);
    this.b = new AdjustableBoat(30, 10, 200, 90);
    this.c = new AdjustableBoat(200, 421, 100, 121);
    this.d = new AdjustableBoat(120, 90, 201, 430);
    this.e = new AdjustableBoat(0, 2, 193, 192);
  }
  
  public void paintComponent(Graphics g) {
    for(int i = 0; i <= 1000; i++) {
      AdjustableBoat boat = new AdjustableBoat((int)Math.pow(i, i), 200, 200, 200);
      boat.draw(g);
    }
    a.draw(g);
    b.draw(g);
    c.draw(g);
    d.draw(g);
    e.draw(g);
  }
}