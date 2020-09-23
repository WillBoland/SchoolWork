import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


// ------------------------------------------------------------------------
// Solve the followign 5 eercises using the five approaches.
// Examples and explanations of the five approaches can be found in Main.java.
// ------------------------------------------------------------------------

public class Exercises {
    private static List<Integer> ints = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,2,8,8,8));

    // ------------------------------------------------------------------------
    // Exercise I: multiply the elements in the list
    //
    // Thinking point:
    // Take note that there isn't a method using the map-reduce approach. Why?
    // ------------------------------------------------------------------------

    static int mul1 () {
        //for loop approach
        int product = ints.get(0);
        for(int index = 1; index < ints.size(); index += 1) {
            product *= ints.get(index);
        }
        return product;
    }

    static int mul2 () {
        //foreach approach
        int product = 1;
        for (int num: ints) {
            product *= num;
        }
        return product;
    }

    static int mul3 () {
        //iterator approach
        Iterator<Integer> iter = ints.iterator();
        int product = 1;
        while(iter.hasNext()) {
            product *= iter.next();
        }
        return product;
    }

    static int mul5 () {
	    //reduce approach
        int answer = ints.stream().reduce(1, (product, k) -> product * k);
        return answer;
    }

    // ------------------------------------------------------------------------
    // Exercise II: check if all elements in the list are even
    // ------------------------------------------------------------------------

    static boolean even1 () {
        //for loop approach
        for(int index = 0; index < ints.size(); index += 1) {
            if(ints.get(index) % 2 != 0) return false;
        }
        return true;
    }

    static boolean even2 () {
        //foreach approach
        for(int num: ints) {
            if(num % 2 != 0) return false;
        }
        return true;
    }

    static boolean even3 () {
        //iterator approach
        Iterator<Integer> iter = ints.iterator();
        while(iter.hasNext()) {
            if(iter.next() % 2 != 0) return false;
        }
        return true;
    }

    static boolean even4 () {
	    //map-reduce approach
        return ints.stream().map(k->k%2).reduce(0, (sum, k) -> sum + k) == 0;
    }

    static boolean even5 () {
	    //reduce approach
        return ints.stream().reduce(0, (sum, k) -> sum + k%2) == 0;
    }

    // ------------------------------------------------------------------------
    // Exercise III: compute the maximum number in the list
    //
    // Thinking point:
    // Take note that there isn't a method using the map-reduce approach. Why?
    // ------------------------------------------------------------------------

    static int max1 () {
        //for loop approach
        int max = Integer.MIN_VALUE;
        for(int index = 0; index < ints.size(); index += 1) {
            max = (ints.get(index) > max ? ints.get(index) : max);
        }
        return max;
    }

    static int max2 () {
        //foreach approach
        int max = Integer.MIN_VALUE;
        for(int current: ints) {
            max = (current > max ? current : max);
        }
        return max;
    }

    static int max3 () {
        //iterator appraoch
        Iterator<Integer> iter = ints.iterator();
        int max = Integer.MIN_VALUE;
        while(iter.hasNext()) {
            int compare = iter.next();
            max = (compare > max ? compare : max);
        }
        return max;
    }

    static int max5 () {
	    //reduce approach
        return ints.stream().reduce(Integer.MIN_VALUE, (max, current) -> (current > max ? current : max));
    }

    // ------------------------------------------------------------------------
    // Exercise IV: count occurrences of c in the list
    // ------------------------------------------------------------------------

    static int count1 (int c) {
        //for loop approach
        int occurences = 0;
        for(int index = 0; index < ints.size(); index += 1) {
            occurences += (c == ints.get(index) ? 1 : 0);
        }
        return occurences;
    }

    static int count2 (int c) {
        //foreach approach
        int occurences = 0;
        for(int current: ints) {
            occurences += (c == current ? 1 : 0);
        }
        return occurences;
    }

    static int count3 (int c) {
        //iterator approach
        Iterator<Integer> iter = ints.iterator();
        int occurences = 0;
        while(iter.hasNext()) {
            int current = iter.next();
            occurences += (c == current ? 1 : 0);
        }
        return occurences;
    }

    static int count4 (int c) {
	    //map-reduce approach
        return ints.stream().map(k -> k).reduce(0, (occurences, k) -> (k == c ? occurences + 1 : occurences));
    }

    static int count5 (int c) {
	    //reduce approach
        return ints.stream().reduce(0, (occurences, k) -> (k == c ? occurences + 1 : occurences));
    }

    // ------------------------------------------------------------------------
    // Exercise V: triplicate each element in the list
    //
    // Thinking point:
    // Take note that there isn't a solution for this using the reduce approach. Why?
    // ------------------------------------------------------------------------
    
    static List<Integer> trip1 () {
        //for loop approach
        List<Integer> triplicate = new ArrayList<Integer>(ints.size() * 3);
        for(int index = 0; index < ints.size(); index += 1) {
            int current = ints.get(index);
            triplicate.add(current);
            triplicate.add(current);
            triplicate.add(current);
        }
        return triplicate;
    }

    static List<Integer> trip2 () {
        //foreach approach
        List<Integer> triplicate = new ArrayList<Integer>(ints.size() * 3);
        for(int current: ints) {
            triplicate.add(current);
            triplicate.add(current);
            triplicate.add(current);
        }
        return triplicate;
    }

    static List<Integer> trip3 () {
        //iterator approach
        List<Integer> triplicate = new ArrayList<Integer>(ints.size() * 3);
        Iterator<Integer> iter = ints.iterator();
        while(iter.hasNext()) {
            int current = iter.next();
            triplicate.add(current);
            triplicate.add(current);
            triplicate.add(current);
        }
        return triplicate;
    }

    static List<Integer> trip4 () {
	    //map-reduce approach
        //list.stream().flatMap(k->(Stream.of(k/2,k/3)).collect(Collectors.toList());
        return ints.stream().flatMap(k -> (Stream.of(k, k, k))).collect(Collectors.toList());
    }

}
