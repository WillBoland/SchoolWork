import java.util.*;

public class DijkstraSearch
{
  
  //shortestPath gets the shortest path given a list of cities
  public static List<TicketToRide> shortestPath(TicketToRide target) {
    List<TicketToRide> path = new ArrayList<TicketToRide>();
    
    for (TicketToRide vertex = target; vertex != null; vertex = vertex.previous) {
      path.add(vertex);
    }
    
    Collections.reverse(path); //reverse the path, seen from chapter homework
    return path; //duh
  }
  
  //get paths of given city
  public static void getPaths(TicketToRide city) {
    city.minDistance = 0; //min distance set to 0
    PriorityQueue<TicketToRide> queue = new PriorityQueue<TicketToRide>(); //queue
    queue.add(city); //add the city to the queue
    
    while(!queue.isEmpty()) {
      TicketToRide first = queue.poll(); //stack.pull basically
      
      for(Edge edge : first.neighbors) {
        TicketToRide v = edge.target;
        double weight = edge.weight;
        double distanceThroughFirst = first.minDistance + weight;
        
        if (distanceThroughFirst < v.minDistance) {
          queue.remove(v);
          
          v.minDistance = distanceThroughFirst;
          v.previous = first;
          queue.add(v);
        }
      }
    }
  }
  
  public static void main(String[] args)
  {
    TicketToRide Atl = new TicketToRide("Atlanta");
    TicketToRide Cha = new TicketToRide("Charleston");
    TicketToRide Mia = new TicketToRide("Miami");
    TicketToRide Van = new TicketToRide("Vancouver");
    TicketToRide Cal = new TicketToRide("Calgary");
    TicketToRide Sea = new TicketToRide("Seattle");
    TicketToRide Por = new TicketToRide("Portland");
    TicketToRide SLC = new TicketToRide("Salt Lake City");
    TicketToRide San = new TicketToRide("San Francisco");
    TicketToRide Los = new TicketToRide("Los Angeles");
    TicketToRide Las = new TicketToRide("Las Vegas");
    TicketToRide Pho = new TicketToRide("Phoenix");
    TicketToRide ElP = new TicketToRide("El Paso");
    TicketToRide Santa = new TicketToRide("Santa Fe");
    TicketToRide Den = new TicketToRide("Denver");
    TicketToRide Hel = new TicketToRide("Helena");
    TicketToRide Win = new TicketToRide("Winnipeg");
    TicketToRide Dul = new TicketToRide("Duluth");
    TicketToRide Oma = new TicketToRide("Omaha");
    TicketToRide Kan = new TicketToRide("Kansas City");
    TicketToRide Okl = new TicketToRide("Oklahoma City");
    TicketToRide Dal = new TicketToRide("Dallas");
    TicketToRide Hou = new TicketToRide("Houston");
    TicketToRide New = new TicketToRide("New Orleans");
    TicketToRide Lit = new TicketToRide("Little Rock");
    TicketToRide StL = new TicketToRide("St. Louis");
    TicketToRide Chi = new TicketToRide("Chicago");
    TicketToRide SSM = new TicketToRide("Sault Ste. Marie");
    TicketToRide Tor = new TicketToRide("Toronto");
    TicketToRide Mon = new TicketToRide("Montreal");
    TicketToRide Bos = new TicketToRide("Boston");
    TicketToRide NeY = new TicketToRide("New York");
    TicketToRide Pit = new TicketToRide("Pittsburg");
    TicketToRide Was = new TicketToRide("Washington");
    TicketToRide Nas = new TicketToRide("Nashville");
    TicketToRide Ral = new TicketToRide("Raleigh");
    
    Atl.neighbors = new Edge[]{ new Edge(Ral, 2),new Edge(Cha,2),new Edge(Mia,5),new Edge(New,4),new Edge(Nas,1) };
    Cha.neighbors = new Edge[]{ new Edge(Ral, 2),new Edge(Atl,2),new Edge(Mia,4) };
    Mia.neighbors = new Edge[]{ new Edge(Cha, 4),new Edge(Atl,5),new Edge(New,6) };
    Van.neighbors = new Edge[]{ new Edge(Cal, 3), new Edge(Sea, 1) };
    Cal.neighbors = new Edge[]{ new Edge(Van, 3), new Edge(Sea, 4), new Edge(Hel, 4),new Edge(Win, 6)};
    Sea.neighbors = new Edge[]{ new Edge(Van, 1),new Edge(Cal, 4),new Edge(Hel, 6),new Edge(Por, 1) };
    Por.neighbors = new Edge[]{ new Edge(Sea, 1),new Edge(SLC, 6),new Edge(San, 5) };
    SLC.neighbors = new Edge[]{ new Edge(Por, 6),new Edge(Hel,3),new Edge(Den,3),new Edge(Las, 3),new Edge(San, 5) };
    San.neighbors = new Edge[]{ new Edge(Por, 5),new Edge(SLC,5),new Edge(Los,3)};
    Los.neighbors = new Edge[]{ new Edge(San, 3),new Edge(Las, 2),new Edge(Pho,3),new Edge(ElP,6)};
    Las.neighbors = new Edge[]{ new Edge(SLC, 3),new Edge(Los,2) };
    Pho.neighbors = new Edge[]{ new Edge(Los, 3),new Edge(Den,5),new Edge(Santa,3),new Edge(ElP,3) };
    ElP.neighbors = new Edge[]{ new Edge(Los, 6),new Edge(Pho,3),new Edge(Santa,2),new Edge(Okl,5),new Edge(Dal,4),new Edge(Hou,6) };
    Santa.neighbors = new Edge[]{ new Edge(Den, 2),new Edge(Okl,3),new Edge(ElP,2),new Edge(Pho,3) };
    Den.neighbors = new Edge[]{ new Edge(SLC, 3),new Edge(Hel,4),new Edge(Oma,4),new Edge(Kan,4),new Edge(Okl,4),new Edge(Santa,2),new Edge(Pho,5) };
    Hel.neighbors = new Edge[]{ new Edge(Sea, 6),new Edge(Cal,4),new Edge(Win,4),new Edge(Dul,6),new Edge(Oma,5),new Edge(Den,4),new Edge(SLC,3) };
    Win.neighbors = new Edge[]{ new Edge(Cal, 6),new Edge(Hel,4),new Edge(Dul,4),new Edge(SSM,6) };
    Dul.neighbors = new Edge[]{ new Edge(Win, 4),new Edge(SSM,3),new Edge(Tor,6),new Edge(Chi,3),new Edge(Oma,2),new Edge(Hel,6) };
    Oma.neighbors = new Edge[]{ new Edge(Hel, 5),new Edge(Dul,2),new Edge(Chi,4),new Edge(Kan,1),new Edge(Den,4) };
    Kan.neighbors = new Edge[]{ new Edge(Oma, 1),new Edge(StL,2),new Edge(Okl,2),new Edge(Den,4) };
    Okl.neighbors = new Edge[]{ new Edge(Den,4),new Edge(Kan,2),new Edge(Lit,2),new Edge(Dal,2),new Edge(ElP,5),new Edge(Santa,3) };
    Dal.neighbors = new Edge[]{ new Edge(Lit, 2),new Edge(Hou,1),new Edge(ElP,4),new Edge(Okl,2) };
    Hou.neighbors = new Edge[]{ new Edge(ElP, 6),new Edge(Dal,1),new Edge(New,2) };
    New.neighbors = new Edge[]{ new Edge(Hou, 2),new Edge(Lit,3),new Edge(Atl,4),new Edge(Mia,6) };
    Lit.neighbors = new Edge[]{ new Edge(Nas, 3),new Edge(New,3),new Edge(Dal,2),new Edge(Okl,2),new Edge(StL,2) };
    StL.neighbors = new Edge[]{ new Edge(Chi, 2),new Edge(Pit,5),new Edge(Nas,2),new Edge(Lit,2),new Edge(Kan,2) };
    Chi.neighbors = new Edge[]{ new Edge(Pit, 3),new Edge(StL,5),new Edge(Tor,3),new Edge(Dul,3),new Edge(Oma,4) };
    SSM.neighbors = new Edge[]{ new Edge(Win, 6),new Edge(Dul,3),new Edge(Tor,2),new Edge(Mon,5) };
    Tor.neighbors = new Edge[]{ new Edge(SSM, 2),new Edge(Mon,3),new Edge(Pit,2),new Edge(Dul,6),new Edge(Chi,4) };
    Mon.neighbors = new Edge[]{ new Edge(Bos, 2),new Edge(NeY,3),new Edge(Tor,3),new Edge(SSM,5) };
    Bos.neighbors = new Edge[]{ new Edge(Mon, 2),new Edge(NeY,2) };
    NeY.neighbors = new Edge[]{ new Edge(Was, 2),new Edge(Pit,2),new Edge(Mon,3),new Edge(Pit,2),new Edge(Bos,2) };
    Pit.neighbors = new Edge[]{ new Edge(Tor, 2),new Edge(NeY,2),new Edge(Was,2),new Edge(Ral,2),new Edge(Nas,4),new Edge(StL,5),new Edge(Chi,3) };
    Was.neighbors = new Edge[]{ new Edge(NeY, 2),new Edge(Pit,2),new Edge(Ral,2) };
    Nas.neighbors = new Edge[]{ new Edge(StL, 2),new Edge(Lit,3),new Edge(Atl,1),new Edge(Ral,3),new Edge(Pit,4) };
    Ral.neighbors = new Edge[]{ new Edge(Cha, 2),new Edge(Atl,2),new Edge(Nas,3),new Edge(Pit,2),new Edge(Was,2) };
    
    
    Scanner in=new Scanner(System.in); //scanner
    System.out.println("Enter your starting point.");
    String start = in.nextLine(); //get first city
    System.out.println("Enter your destination.");
    String end = in.nextLine(); //end destination
    
    for(TicketToRide city: TicketToRide.locations) { //for all of the locations get the paths if it is the start
      if(start.equals(city.toString())){
        getPaths(city);
      }
    }
    
    List<TicketToRide> secondPath = null;
    
    for(TicketToRide city: TicketToRide.locations) { //get end city path
      if(end.equals(city.toString())) { //check to see if it is end
        secondPath = shortestPath(city);
        if(secondPath != null) { //found it
          System.out.println("The shortest path from " + start + " to " + end + " is: " + secondPath);
        } else {
          System.out.println("There is no path there.");
        }
      }
    }
  }
}
