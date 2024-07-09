package Day8;

import java.util.*;

public class BFS {
    private int v;
    private LinkedList<Integer>[] adjacencyList;

    // Constructor
    public BFS(int v) {
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

    // BFS traversal method
    public void bfs(int startVertex) {
        boolean[] visited = new boolean[v];
        LinkedList<Integer> queue = new LinkedList<>();
        List<Integer> visitedNodes = new ArrayList<>();

        // Mark the starting node as visited and enqueue it
        visited[startVertex] = true;
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            visitedNodes.add(vertex);

            Iterator<Integer> iterator = adjacencyList[vertex].listIterator();
            while (iterator.hasNext()) {
                int adjacentVertex = iterator.next();
                if (!visited[adjacentVertex]) {
                    visited[adjacentVertex] = true;
                    queue.add(adjacentVertex);
                }
            }
        }

        // Print visited nodes
        System.out.println("Visited nodes:");
        for (int node : visitedNodes) {
            System.out.print(node + " ");
        }
        System.out.println();

        // Print not visited nodes
        System.out.println("Not visited nodes:");
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    // Main method to test the BFS implementation
    public static void main(String[] args) {
        BFS graph = new BFS(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        System.out.println("BFS traversal starting from vertex 0:");
        graph.bfs(0);
    }
}

