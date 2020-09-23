import java.util.ArrayList;

public class Sequence {
  private ArrayList<Integer> values;
  
  public Sequence() { values = new ArrayList<Integer>(); }
  
  public Sequence(int[] values){ 
    this();
    for (int v : values)
      this.values.add( v);
  } 
  
  public void add(int n) { values.add(n); }
  
  public String toString() { return values.toString(); }
  
  public Sequence append(Sequence other) {
    Sequence newSequence = new Sequence();
    for(int v: this.values)
      newSequence.add(v);
    for(int v: other.values)
      newSequence.add(v);
    return newSequence;
  }

  public static Sequence sort(Sequence w) {
    if (w.values.size() <= 1) return w; 
    else return merge(longest(w), sort(rest(w)));
  }
  
  public static Sequence longest(Sequence w) {
    Sequence result = new Sequence(); 
    for (int i = 0; result.values.size() == 0 || i < w.values.size() && result.values.get(result.values.size()-1).compareTo(w.values.get(i)) < 0; )
      result.add( w.values.get(i) ); 
    return result; 
  }
  
  public static Sequence merge(Sequence a, Sequence b) {
    Sequence result = new Sequence(); 
    for (int i = 0, j = 0; i < a.values.size() || j < b.values.size();    ) {
      if (i < a.values.size() && j < b.values.size()) 
        if (a.values.get(i).compareTo(b.values.get(j)) < 0) result.add( a.values.get(i++) ); 
        else result.add( b.values.get(j++) ); 
      else if (i < a.values.size()) result.add( a.values.get(i++) ); 
      else result.add( b.values.get(j++) );
    }
    return result; 
  }
  
  public static Sequence rest(Sequence w) {
    Sequence result = new Sequence(); 
    for (int i = longest(w).values.size(); i < w.values.size(); i++)
      result.add( w.values.get(i) ); 
    return result;
  }
}
