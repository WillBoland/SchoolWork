//problem 4

import java.util.*;

public class Human implements Comparable<Human> {
  private int age;
  private String name;
  public Human(String name, int age) {
    this.name = name;
    this.age = age;
  }
  
  public String toString() {
    return this.name + " is " + ((age == 1) ? "1 year old" : age + " years old");
  }
  
  public int compareTo(Human other) {
    if(this.age < other.age)
      return -1;
    else if(this.age > other.age)
      return 1;
    return this.name.compareTo(other.name);
  }
  
  public static void main(String[] args) {
    ArrayList<Human> p = new ArrayList<>();
    p.add(new Human("bob", 100));
    p.add(new Human("Adam", 99));
    p.add(new Human("Cindy", 99));
    p.add(new Human("Adam", 7));
    System.out.println(p);
    Collections.sort(p);
    System.out.println(p);
  }
}
