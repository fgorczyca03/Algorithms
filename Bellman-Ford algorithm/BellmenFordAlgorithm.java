import java.util.*;

public class BellmenFordAlgorithm {

    static class Edge{
        int source, destination, weight;

        Edge(int source, int destination, int weight){
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static void bellmanFord(List<Edge> edges, int vertices, int source){
        int[] distances = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        //Relax all edges |V| - 1 times
        for(int i = 1; i<vertices; i++){
            for(Edge edge : edges){
                int u = edge.source;
                int v = edge.destination;
                int weight = edge.weight;

                if(distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]){
                    distances[v] = distances[u] + weight;
                }
            }
        }

        //check for negative weight cycles
        for(Edge edge : edges){
            int u = edge.source;
            int v = edge.destination;
            int weight = edge.weight;

            if(distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]){
                System.out.println("Graph contains a negative weight cycle");
                return;
            }
        }

        printShortestPaths(distances, source);
    }

    private static void printShortestPaths(int[] distances, int source){
        System.out.println("Shortest Paths from source vertex " + source + ":");
        for(int i = 0; i < distances.length; i++){
            System.out.println(" To vertex " + i +" -Distance: " + (distances[i] == Integer.MAX_VALUE ? "Infinity": distances[i]));
        }
    }

    public static void main(String[] args) {
        int vertices = 5;

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 6));
        edges.add(new Edge(0, 2, 7));
        edges.add(new Edge(1, 2, 8));
        edges.add(new Edge(1, 3, 5));
        edges.add(new Edge(1, 4, -4));
        edges.add(new Edge(2, 3, -3));
        edges.add(new Edge(2, 4, 9));
        edges.add(new Edge(3, 1, -2));
        edges.add(new Edge(4, 0, 2));
        edges.add(new Edge(4, 3, 7));

        int source = 0;
        bellmanFord(edges, vertices, source);
    }

}
