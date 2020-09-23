//problem 8

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Screen extends JComponent {
  ArrayList<Circle> circles = new ArrayList<>();
  public Screen() {
    for(int i = 0; i < 100; i++) {
      circles.add(new Circle((int)Math.random() * 100,
                             (int)Math.random() * 100,
                             (int)Math.random() * 100,
                             new Color(i, i, i));
    }
  }
  public void paintComponent(Graphics g) {
    for(Circle c: circles) {
      c.draw(g);
    }
  }
  public static void main(String[] args) {
    Screen s = new Screen();
    JFrame f = new JFrame();
    f.add(s);
    f.setVisible(true);
    f.setSize(400, 400);
  }
}
