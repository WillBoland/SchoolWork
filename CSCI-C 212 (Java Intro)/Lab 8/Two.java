import java.util.*; //for Scanner()
import java.io.*; //for File()

public class Two {
  public static void main(String[] args) throws Exception {
    HashMap<String, Integer> hash = new HashMap<String, Integer>();
    
    Scanner input = new Scanner(new File(args[0])); //reads file given
    
    while( input.hasNext() ) {
      String token = input.next();
      if(hash.containsKey(token)) {
        hash.put(token, hash.get(token) + 1);
      } else {
        hash.put(token, 1);
      }
    }
    System.out.println(hash);
    input.close(); 
  }
}
