import java.util.*;

public class Ascending implements Comparator<Five> {
  public int compare(Five a, Five b) {
    if(a.age < b.age)
      return -1;
    if(a.age > b.age)
      return 1;
    return a.name.compareTo(b.name);
  }
}
