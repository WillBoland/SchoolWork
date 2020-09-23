public class SequenceTest {
  public static void main(String[] args) {
    Sequence sorted = new Sequence(new int[] {0, 1, 2, 4, 6, 9, 200, 500});
    sorted = Sequence.sort(sorted);
    System.out.println(sorted); //tests sort()
    
    sorted = new Sequence(); //tests add()
    for(int i = 0; i < 100; i++) {
      sorted.add(i);
    }
    System.out.println(sorted);
    
    Sequence a = new Sequence(new int[] {0, 1, 2, 4, 6, 9, 200, 500});
    Sequence b = new Sequence(new int[] {-9, 4, 2, 6, 7, 100, 10000, 20});
    sorted = Sequence.merge(a, b); //tests merge()
    System.out.println(sorted);

    sorted = new Sequence(new int[] {1, 9, 4, 6, 9, 200, 500});;
    sorted = Sequence.longest(sorted);
    System.out.println(sorted); //tests longest
    
    sorted = new Sequence(new int[] {1, 9, 4, 6, 9, 200, 500});;
    sorted = Sequence.rest(sorted);
    System.out.println(sorted);
    
    sorted = new Sequence();
    sorted = Sequence.sort(sorted);
    System.out.println(sorted); //tests sort()
    
    sorted = new Sequence(new int[] {2, 1});
    sorted = Sequence.sort(sorted);
    System.out.println(sorted); //tests sort()
    
    sorted = new Sequence(new int[] {2});
    sorted = Sequence.sort(sorted);
    System.out.println(sorted); //tests sort()
  }
}
