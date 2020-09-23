//problem 8

import java.util.*;
import javax.swing.*;
import javax.awt.*;

public class Circle {
  int R;
  int x, y;
  Color c;
  public Circle(int R, int x, int y, Color c) {
    this.R = R;
    this.x = x;
    this.y = y;
    this.c = c;
  }
  public void draw(Graphics g) {
    g.setColor(c);
    g.drawOval(x, y, R);
  }
}
