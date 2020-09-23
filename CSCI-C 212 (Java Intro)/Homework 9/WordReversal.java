import java.util.*;

public class WordReversal {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter sentences to be reversed: ");
    String[] in = input.nextLine().split("\\."); //escape regex
    
    for(String sentence: in) {
      System.out.print(reverseSentence(sentence) + " ");
    }
  }
  
  public static String reverseSentence(String sentence) {
    Stack<String> stack = new Stack<>();
    String[] words = sentence.split(" ");
    String reversed = "";
    for(String word: words) {
      stack.push(word.toLowerCase().replaceAll("[!-?{-}]", ""));
    }
    while(!stack.empty()) {
      reversed += (stack.size() < 2) ? stack.pop() : stack.pop() + " ";
    }
    return (reversed.trim()) + ".";
  }
}
