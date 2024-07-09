package Day8;
import java.util.*;

public class DFS {
    private int v;
    private LinkedList<Integer>[] adjacencyList;

    // Constructor
    public DFS(int v) {
        this.v = v;
        adjacencyList = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    // Method to add an edge to the graph (undirected)
    public void addEdge(int v, int w) {
        adjacencyList[v].add(w);
        adjacencyList[w].add(v); // For undirected graph
    }

    // Recursive DFS method
    public void dfs(int vertex, boolean[] visited) {
        // Mark the current node as visited and print it
        visited[vertex] = true;
        System.out.print(vertex + " ");

        // Recur for all the vertices adjacent to this vertex
        for (int adjacentVertex : adjacencyList[vertex]) {
            if (!visited[adjacentVertex]) {
                dfs(adjacentVertex, visited);
            }
        }
    }

    // Utility method to start DFS traversal
    public void startDFS(int startVertex) {
        // Initially mark all vertices as not visited
        boolean[] visited = new boolean[v];

        // Call the recursive helper function to print DFS traversal
        dfs(startVertex, visited);
    }

    // Main method to test the DFS implementation
    public static void main(String[] args) {
        DFS graph = new DFS(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        System.out.println("DFS traversal starting from vertex 0:");
        graph.startDFS(0);
    }
}

