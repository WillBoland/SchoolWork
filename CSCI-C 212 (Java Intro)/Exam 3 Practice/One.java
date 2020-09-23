//Write simplest shortest program that reports mouse motion.

import java.awt.event.*;
import javax.swing.*;

public class One extends JFrame implements MouseMotionListener {
  public One() {
    this.setVisible(true);
    this.setSize(400, 400);
    this.addMouseMotionListener(this);
  }
  public void mouseDragged(MouseEvent e) {System.out.println("dragged");}
  public void mouseMoved(MouseEvent e) {System.out.println("moved");}
  
  public static void main(String[] args) {
    One a = new One();
  }
}
