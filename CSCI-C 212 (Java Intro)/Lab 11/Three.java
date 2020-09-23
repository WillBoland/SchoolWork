//problem 3

import java.awt.event.*;
import javax.swing.*;


public class Three extends JFrame implements ActionListener {
  public Three() {
    this.setVisible(true);
    this.setSize(400, 400);
    Timer t = new Timer(1000, this);
    t.start();
  }
  int count = 0;
  public void actionPerformed(ActionEvent e) {
    System.out.println(count++);
  }
  public static void main(String[] args) {
    Three hi = new Three();
  }
}
