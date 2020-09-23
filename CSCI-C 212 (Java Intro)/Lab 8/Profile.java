import java.util.*;
import java.io.*;

/**
 * takes a file containing strings as votes and creates a hash with the count for votes
 */
public class Profile {
  public static void main(String[] args) throws Exception {
    HashMap<String, Integer> votesMap = new HashMap<String, Integer>();
    ArrayList<Candidate> candidates = new ArrayList<Candidate>();
      
    Scanner input = new Scanner(new File(args[0]));
    
    while(input.hasNext()) {
      String candidate = input.next();
      if(votesMap.containsKey(candidate)) {
        votesMap.put(candidate, votesMap.get(candidate) + 1);
      } else {
        votesMap.put(candidate, 1);
      }
    }
    
    //allows iteration over hashmap ---- see: https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html#keySet--
    for(String key: votesMap.keySet()) {
      candidates.add(new Candidate(key, votesMap.get(key))); //adds to the arraylist of comparable objects
    }
    Collections.sort(candidates);
    Collections.reverse(candidates);
    
    if(args.length == 1) {
      int count = 1;
      for(Candidate person: candidates) {
        System.out.println(count + ". " + person);
        count++;
      }
    } else {
      for(int i = 0; i < Integer.parseInt(args[1]); i++) {
        int place = i + 1;
        System.out.println(place + ". " + candidates.get(i));
      }
    }
  }
}
