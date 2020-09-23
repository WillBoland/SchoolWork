//Write the simplest shortest program that sorts objects using Comparable.

public class Four implements Comparable<Four> {
  int age;
  String name;
  public Four(String name, int age) {
    this.name = name;
    this.age = age;
  }
  public String toString() {
    return ("Name: " + this.name + ", Age: " + this.age);
  }
  public int compareTo(Four other) {
    if(this.age < other.age)
      return -1;
    else if(this.age > other.age)
      return 1;
    else return this.name.compareTo(other.name);
  }
}
