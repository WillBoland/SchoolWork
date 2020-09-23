//Write the simplest shortest program that sorts objects via the Comparator interface.
import java.util.Collections;
import java.util.*;
public class Five {
  int age;
  String name;
  public Five(String name, int age) {
    this.name = name;
    this.age = age;
  }
  public String toString() {
    return this.name + " is " + ((age == 1) ? age + " year old." : age + " years old." );
  }
  
  public static void main(String[] args) {
    ArrayList<Five> f = new ArrayList<>();
    f.add(new Five("Sindy", 1));
    f.add(new Five("Sam", 5));
    f.add(new Five("Sam", 50));
    f.add(new Five("tot", 2));
    f.add(new Five("bob", 500));
    f.add(new Five("bobb", 2));
    Collections.sort(f, new Ascending());
    System.out.println(f);
  }
}
