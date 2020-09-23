import java.util.ArrayList;

public class TicketToRide implements Comparable<TicketToRide> {
  static final ArrayList<TicketToRide> locations = new ArrayList<TicketToRide>(); //locations of game
  public final String name; //name of location
  
  public Edge[] neighbors; //neighbors to locations
  public double minDistance = Double.MAX_VALUE;//use max value so we never hit end
  public TicketToRide previous; //last location
  
  //initiates a game
  public TicketToRide(String name) {
    this.name = name; 
    this.locations.add(this); //adds self
  }
  
  //returns the name
  public String toString() { 
    return this.name;
  }
  
  //for compare
  public int compareTo(TicketToRide other)
  {
    return Double.compare(this.minDistance, other.minDistance);
  }
}
