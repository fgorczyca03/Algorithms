import java.util.*;

class Edge implements Comparable<Edge>{
    int source, destination, weight;

    public Edge(int source, int destination, int weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other){
        return Integer.compare(this.weight, other.weight);
    }
}

class Subset{
    int parent, rank;

    public Subset(int parent, int rank){
        this.parent = parent;
        this.rank = rank;
    }
}

public class KruskalMST {

    private int V;
    private List<Edge> edges;

    public KruskalMST(int v){
        V = v;
        edges = new ArrayList<>();
    }

    //add edge to graph
    public void addEdge(int source, int destination, int weight){
        edges.add(new Edge(source, destination, weight));
    }

    //function to find parent of a vertex
    private int find(Subset[] subsets, int i){
        if(subsets[i].parent != i){
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    //function to perform union of 2 sets
    private void union (Subset[] subsets, int x, int y){
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if(subsets[xroot].rank < subsets[yroot].rank){
            subsets[xroot].parent = yroot;
        } else if(subsets[yroot].rank < subsets[xroot].rank){
            subsets[yroot].parent = xroot;
        }else{
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    public List<Edge> findMST(){
        List<Edge> result = new ArrayList<>();

        Collections.sort(edges);
        Subset[] subsets = new Subset[V];
        for(int i = 0; i<V; i++){
            subsets[i] = new Subset(i, 0);
        }

        int edgeCount = 0;
        int index = 0;

        //keep adding edges until we have V-1 edges in MST
        while(edgeCount < V -1 && index<edges.size()){
            Edge nextEdge = edges.get(index++);

            int x = find(subsets, nextEdge.source);
            int y = find(subsets, nextEdge.destination);

            //add edge if it doesnt create a cycle
            if(x!=y){
                result.add(nextEdge);
                union(subsets, x, y);
                edgeCount++;
            }
        }

        return result;
    }

    public static void printMST(List<Edge> result){
        int totalWeight = 0;
        System.out.println("Edges in the Minimum Spanning Tree: ");
        for(Edge edge: result){
            System.out.println(edge.source + " -- " + edge.destination + " -- " + edge.weight);
            totalWeight += edge.weight;
        }

        System.out.println("Total MST weight: " + totalWeight);
    }

    public static void main(String[] args) {
        // Example usage
        KruskalMST graph = new KruskalMST(4);
        
        // Adding edges to the graph
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);

        List<Edge> mst = graph.findMST();
        printMST(mst);
    }

}
