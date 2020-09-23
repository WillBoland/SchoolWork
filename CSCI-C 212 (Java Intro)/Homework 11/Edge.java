
/**
 * an edge for each city
 */
public class Edge {
  public final TicketToRide target; //target edge
  
  public final double weight; //for dijkstra's
  
  //creates edge with weight and target
  public Edge(TicketToRide target, double weight) {
    this.target = target; 
    this.weight = weight; 
  }
}
