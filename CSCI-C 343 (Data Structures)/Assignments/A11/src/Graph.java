import java.util.*;

public class Graph {

    private ArrayList<Item> nodes;
    private Hashtable<Item, ArrayList<Item>> neighbors;
    private Hashtable<Edge, Integer> weights;

    Graph(
            ArrayList<Item> nodes,
            Hashtable<Item, ArrayList<Item>> neighbors,
            Hashtable<Edge, Integer> weights) {
        this.nodes = nodes;
        this.neighbors = neighbors;
        this.weights = weights;
    }

    // -----

    ArrayList<Item> getNodes() {
        return nodes;
    }

    Hashtable<Item, ArrayList<Item>> getNeighbors() {
        return neighbors;
    }

    Hashtable<Edge, Integer> getWeights() {
        return weights;
    }

    // -----
    // Computes all shortest paths from a given node
    // Nodes are marked with the shortest path to the source

    void allShortestPaths(Item source) {
        final int INFINITY = Integer.MAX_VALUE;

        //Reset and set to Infinity
        for(Item current: nodes) {
            current.reset();
            current.setValue(Integer.MAX_VALUE);
        }

        //Set start to 0
        source.setValue(0);

        //Run the algo
        WeakHeap heap = new WeakHeap(nodes);
        while(!heap.isEmpty()) {
            Item current = heap.extractMin();

            if(!current.isVisited()) {
                current.setVisited(true);
                for(Item next: neighbors.get(current)) {
                    final int CUR_DIST = next.getValue();
                    final int NEXT_DIST = current.getValue() == INFINITY ? INFINITY : current.getValue() + weights.get(new Edge(current, next));
                    if (NEXT_DIST < CUR_DIST) {
                        heap.updateKey(next.getPosition(), NEXT_DIST);
                        next.setPrevious(current);
                    }
                }
            }
        }
    }

    // -----
    // Point-to-point shortest path; stops as soon as it reaches destination

    ArrayList<Edge> shortestPath(Item source, Item dest) {
        //Same as above; except for 1 small change.
        final int INFINITY = Integer.MAX_VALUE;

        //Reset and set to Infinity
        for(Item current: nodes) {
            current.reset();
            current.setValue(Integer.MAX_VALUE);
        }

        //Set start to 0
        source.setValue(0);

        //Run the algo
        WeakHeap heap = new WeakHeap(nodes);
        while(!heap.isEmpty()) {
            Item current = heap.extractMin();
            if(current.equals(dest)) {
                break;
            }

            if(!current.isVisited()) {
                current.setVisited(true);
                for(Item next: neighbors.get(current)) {
                    final int CUR_DIST = next.getValue();
                    final int NEXT_DIST = current.getValue() == INFINITY ? INFINITY : current.getValue() + weights.get(new Edge(current, next));
                    if (NEXT_DIST < CUR_DIST) {
                        heap.updateKey(next.getPosition(), NEXT_DIST);
                        next.setPrevious(current);
                    }
                }
            }
        }
        return Item.pathToSource(dest);
    }

    // -----

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Nodes:%n%s%n", nodes));
        res.append(String.format("Neighbors:%n%s%n", neighbors));
        res.append(String.format("Weights:%n%s%n", weights));
        return new String(res);
    }
}
