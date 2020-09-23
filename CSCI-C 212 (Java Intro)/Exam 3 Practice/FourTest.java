import java.util.*;

public class FourTest {
  public static void main(String[] args) {
    ArrayList<Four> f = new ArrayList<>();
    for(int i = 0; i < 10; i++) {
      f.add(new Four(("Bob" + i), (int)(Math.random() * 100)));
    }
    System.out.println(f);
    Collections.sort(f);
    System.out.println(f);
  }
}
