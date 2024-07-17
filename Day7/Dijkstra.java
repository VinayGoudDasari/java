import java.util.*;

public class Dijkstra {

    public static Map<String, Integer> dijkstra(Map<String, Map<String, Integer>> graph, String start) {
        // Priority queue to store (distance, node) and sort by distance
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        priorityQueue.add(new Node(start, 0));

        // Map to store the shortest distance from the start node to each node
        Map<String, Integer> distances = new HashMap<>();
        for (String node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        // Process the priority queue
        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            int currentDistance = currentNode.distance;
            String currentVertex = currentNode.vertex;

            // Explore neighbors
            for (Map.Entry<String, Integer> neighborEntry : graph.get(currentVertex).entrySet()) {
                String neighbor = neighborEntry.getKey();
                int weight = neighborEntry.getValue();
                int distance = currentDistance + weight;

                // If a shorter path to the neighbor is found
                if (distance < distances.get(neighbor)) {
                    distances.put(neighbor, distance);
                    priorityQueue.add(new Node(neighbor, distance));
                }
            }
        }
        return distances;
    }

    public static void main(String[] args) {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        graph.put("A", Map.of("B", 3, "C", 6));
        graph.put("B", Map.of("A", 3, "C", 2, "D", 8));
        graph.put("C", Map.of("A", 6, "B", 2, "D", 1));
        graph.put("D", Map.of("B", 8, "C", 1));

        String startNode = "A";
        Map<String, Integer> distances = dijkstra(graph, startNode);

        for (Map.Entry<String, Integer> entry : distances.entrySet()) {
            System.out.println("Distance from " + startNode + " to " + entry.getKey() + " is " + entry.getValue());
        }
    }
}

class Node {
    String vertex;
    int distance;

    Node(String vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }
}
