import java.util.Arrays;

public class MergeSort<T> {
  
  //sorts a generic type given a comparable type
  public static <T extends Comparable<T>> T[] sort(T[] arr) {
    if(arr.length <= 1) {
      return arr;
    }
    else {
      return merge(longest(arr), sort(rest(arr)));
    }
  }
  
  //finds the longest in a given comparable type
  @SuppressWarnings("unchecked") //no warnings
  public static <T extends Comparable<T>> T[] longest(T[] arr) {
    T[] result = (T[])new Object[0]; //cast to T
    
    for(int i = 0; result.length == 0 || i < arr.length; ) {
      if(result[result.length - 1].compareTo(arr[0]) < 0) {
        result = Arrays.copyOf(result, result.length + 1);
        result[result.length - 1] = arr[i];
      }
    }
    return result;
  }
  
  //merges two type arrays
  @SuppressWarnings("unchecked") //no warnings
  public static <T extends Comparable<T>> T[] merge(T[] first, T[] second) {
    T[] result = (T[])new Object[0]; //cast to T
    
    for(int i = 0, j = 0; i < first.length || j < second.length; ) {
      if(i < first.length && j < second.length) {
        if(first[i].compareTo(second[j]) < 0) {
          result = Arrays.copyOf(result, result.length + 1);
          result[result.length - 1] = first[i++];
        } else {
          result = Arrays.copyOf(result, result.length + 1);
          result[result.length - 1] = second[j++];
        }
      } else if(i < first.length) {
        result = Arrays.copyOf(result, result.length + 1);
        result[result.length - 1] = first[i++];
      } else {
        result = Arrays.copyOf(result, result.length + 1);
        result[result.length - 1] = second[j++];
      }
    }
    return result;
  }
  
  //finds the rest after longest
  @SuppressWarnings("unchecked") //no warnings
  public static <T extends Comparable<T>> T[] rest(T[] arr) {
    T[] result = (T[])new Object[0]; //cast to T
    
    for(int i = longest(arr).length; i < arr.length; i++) {
      result = Arrays.copyOf(result, result.length + 1);
      result[result.length - 1] = arr[i];
    }
    return result;
  }
}
