//Write simplest program that reports mouse events other than motion.

import java.awt.event.*;
import javax.swing.*;

public class Two extends JFrame implements MouseListener {
  public Two() {
    this.setVisible(true);
    this.setSize(400, 400);
    this.addMouseListener(this);
  }
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mouseClicked(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {}
  public void mousePressed(MouseEvent e) {}
  
  public static void main(String[] args) {
    Two b = new Two();
  }
}
