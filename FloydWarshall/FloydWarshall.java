package FloydWarshall;

public class FloydWarshall {
    public static final int INF = Integer.MAX_VALUE;

    public static int[][] findShortestPaths(int[][] graph){
        int v = graph.length;
        int[][] dist = new int[v][v];

        for(int i = 0; i<v; i++){
            for(int j = 0; j<v; j++){
                dist[i][j] = graph[i][j];
            }
        }

        //core floyd algorithm
        for(int k = 0; k<v; k++){
            for(int i = 0; i<v; i++){
                for(int j = 0; j<v; j++){
                    if(dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }

    //print distance matrix
    public static void printDistanceMatrix(int[][] dist){
        int v = dist.length;
        System.out.println("Shortest distances between every pair of vertices:");
        for(int i = 0; i<v; i++){
            for(int j = 0; j<v; j++){
                if(dist[i][j] == INF){
                    System.out.print("INF\t");
                } else {
                    System.out.print(dist[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Example usage
        int[][] graph = {
            {0,   5,   INF, 10},
            {INF, 0,   3,   INF},
            {INF, INF, 0,   1},
            {INF, INF, INF, 0}
        };

        int[][] shortestDistances = findShortestPaths(graph);
        printDistanceMatrix(shortestDistances);
    }

}
