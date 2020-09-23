public class BuffonNeedleTest {
  public static void main(String[] args) {
    BuffonNeedle needle = new BuffonNeedle();
    
    for(int i = 0; i < 10000; i++) {
      needle.dropNeedle();
    }
    System.out.println(needle.getQuotient());
  }
}