public class Candidate implements Comparable<Candidate> {
  private String name;
  private int votes;
  
  public Candidate(String name, int votes) {
    this.name = name;
    this.votes = votes;
  }
  
  public void addVote() {
    this.votes += 1;
  }
  
  public int getVote() {
    return votes;
  }
  
  public String getName() {
    return name;
  }
  
  //----------------------------------interface Comparable----------------------------------//
  public int compareTo(Candidate other) {
    if(this.votes < other.votes)
      return -1;
    else if (this.votes > other.votes)
      return 1;
    else
      return this.name.compareTo(other.name);
  }
  
  //----------------------------------overrides----------------------------------//
  public String toString() {
    return name + " = " + votes;
  }
}
