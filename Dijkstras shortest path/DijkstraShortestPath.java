import java.util.*;

public class DijkstraShortestPath {

    static class Node implements Comparable<Node>{
        int vertex;
        int distance;

        Node(int vertex, int distance){
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other){
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static void dijkstra(int[][] graph, int source){
        int n = graph.length;
        int[] distances = new int[n];
        boolean[] visited = new boolean[n];

        //initialize distances to infinity and visited to false
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0));

        while(!pq.isEmpty()){
            Node current = pq.poll();
            int currentVertex = current.vertex;

            if(visited[currentVertex]) continue;
            visited[currentVertex] = true;

            for(int neighbor = 0; neighbor <n; neighbor++){
                int edgeWeight = graph[currentVertex][neighbor];

                if(edgeWeight > 0 && !visited[neighbor]){
                    int newDistance = distances[currentVertex] + edgeWeight;

                    if(newDistance < distances[neighbor]){
                        distances[neighbor] = newDistance;
                        pq.add(new Node(neighbor, newDistance));
                    }
                }
            }
        }

        printShortestPaths(distances, source);
    }

    private static void printShortestPaths(int[] distances, int source){
        System.out.println("Shortest paths from source vortex " + source + ":");
        for(int i = 0; i <distances.length; i++){
            System.out.println(" To vertex " + i + " - Distance: " + distances[i]);
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 10, 20, 0, 0},
                {10, 0, 5, 16, 0},
                {20, 5, 0, 20, 1},
                {0, 16, 20, 0, 2},
                {0, 0, 1, 2, 0}
        };

        int source = 0;
        dijkstra(graph, source);
    }

}
