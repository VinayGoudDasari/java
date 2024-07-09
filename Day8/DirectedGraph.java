package Day8;

import java.util.*;

public class DirectedGraph {
    private int v;
    private LinkedList<Integer>[] adjacencyList;

    // Constructor
    public DirectedGraph(int v) {
        this.v = v;
        adjacencyList = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    // Method to add an edge to the graph (directed)
    public boolean addEdge(int v, int w) {
        // Temporarily add the edge
        adjacencyList[v].add(w);

        // Check if adding this edge creates a cycle
        if (hasCycle()) {
            // If a cycle is found, remove the edge and return false
            adjacencyList[v].remove((Integer) w);
            return false;
        }

        // If no cycle is found, the edge is added permanently
        return true;
    }

    // Utility method to detect cycles in the graph using DFS
    private boolean hasCycle() {
        boolean[] visited = new boolean[v];
        boolean[] recStack = new boolean[v];

        // Call the recursive helper function to detect cycles in different DFS trees
        for (int i = 0; i < v; i++) {
            if (detectCycle(i, visited, recStack)) {
                return true;
            }
        }
        return false;
    }

    // Recursive method to detect cycle in a subgraph reachable from vertex v
    private boolean detectCycle(int node, boolean[] visited, boolean[] recStack) {
        if (recStack[node]) {
            return true;
        }
        if (visited[node]) {
            return false;
        }

        visited[node] = true;
        recStack[node] = true;

        for (int neighbor : adjacencyList[node]) {
            if (detectCycle(neighbor, visited, recStack)) {
                return true;
            }
        }

        recStack[node] = false;
        return false;
    }

    // Method to print the graph
    public void printGraph() {
        for (int i = 0; i < v; i++) {
            System.out.print("Vertex " + i + ":");
            for (Integer neighbor : adjacencyList[i]) {
                System.out.print(" -> " + neighbor);
            }
            System.out.println();
        }
    }

    // Main method to test the graph edge addition and cycle detection
    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph(5);

        System.out.println("Adding edge 0 -> 1: " + graph.addEdge(0, 1));
        System.out.println("Adding edge 0 -> 2: " + graph.addEdge(0, 2));
        System.out.println("Adding edge 1 -> 2: " + graph.addEdge(1, 2));
        System.out.println("Adding edge 2 -> 0: " + graph.addEdge(2, 0)); // Should create a cycle
        System.out.println("Adding edge 2 -> 3: " + graph.addEdge(2, 3));
        System.out.println("Adding edge 3 -> 3: " + graph.addEdge(3, 3)); // Should create a cycle

        System.out.println("\nGraph structure:");
        graph.printGraph();
    }
}

