import java.util.*;

public class One {
  public static void main(String[] args) {
    String word = args[0]; //args is an array inside the interactions panel. it contains a word at index 0.
    HashMap<String, Integer> hash = new HashMap<String, Integer>(); //creates a new hashmap of String, integer
    
    //iterates through word
    for(int i = 0; i < word.length(); i++) {
      String letter = word.substring(i, i+1); //gets the character as a string
      //if hash has the key
      if(hash.containsKey(letter)) {
        hash.put(letter, hash.get(letter) + 1); //increments the letter value has by 1. 
      } else { //if hash doesnt have the key
        hash.put(letter, 1); //puts letter in hash. no duplicates
      }
    }
    System.out.println(hash);
  }
}
