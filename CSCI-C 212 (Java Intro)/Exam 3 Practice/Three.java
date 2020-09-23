//Write simplest program that can keep and report time.

import java.awt.event.*;
import javax.swing.*;

public class Three implements ActionListener {
  public Three() {
    Timer t = new Timer(1000, this);
    t.start();
  }
  int count = 1;
  public void actionPerformed(ActionEvent e) {
    System.out.println(count++);
  }
  
  public static void main(String[] args) {
    Three t = new Three();
  }
}
