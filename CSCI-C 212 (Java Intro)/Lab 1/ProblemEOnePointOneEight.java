import java.net.URL; //import URL class
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ProblemEOnePointOneEight {
  //E1.18
  public static void main(String[] args) throws Exception {
    URL imageLocation = new URL("http://www.fyrstertech.com/favicon.ico"); //image is extremely small
    JOptionPane.showMessageDialog(null, "Welcome to Fyrster Tech", "Title", JOptionPane.PLAIN_MESSAGE, new ImageIcon(imageLocation));//new title
  }
}