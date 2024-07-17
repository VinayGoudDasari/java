import java.util.*;

class Edge implements Comparable<Edge> {
    String source, destination;
    int weight;

    Edge(String source, String destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

class Subset {
    String parent;
    int rank;
}

public class Kruskal {

    // Find set of a node u
    static String find(Map<String, Subset> subsets, String u) {
        if (!subsets.get(u).parent.equals(u)) {
            subsets.get(u).parent = find(subsets, subsets.get(u).parent);
        }
        return subsets.get(u).parent;
    }

    // Union of two sets x and y
    static void union(Map<String, Subset> subsets, String x, String y) {
        String rootX = find(subsets, x);
        String rootY = find(subsets, y);

        if (subsets.get(rootX).rank < subsets.get(rootY).rank) {
            subsets.get(rootX).parent = rootY;
        } else if (subsets.get(rootX).rank > subsets.get(rootY).rank) {
            subsets.get(rootY).parent = rootX;
        } else {
            subsets.get(rootY).parent = rootX;
            subsets.get(rootX).rank++;
        }
    }

    public static List<Edge> kruskal(List<Edge> edges, Set<String> vertices) {
        // Sort edges in non-decreasing order of their weight
        Collections.sort(edges);

        // Create subsets for each vertex
        Map<String, Subset> subsets = new HashMap<>();
        for (String vertex : vertices) {
            Subset subset = new Subset();
            subset.parent = vertex;
            subset.rank = 0;
            subsets.put(vertex, subset);
        }

        List<Edge> result = new ArrayList<>();

        // Process edges one by one
        for (Edge edge : edges) {
            String x = find(subsets, edge.source);
            String y = find(subsets, edge.destination);

            // If including this edge does not form a cycle
            if (!x.equals(y)) {
                result.add(edge);
                union(subsets, x, y);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge("A", "B", 3));
        edges.add(new Edge("A", "C", 7));
        edges.add(new Edge("A", "E", 5));
        edges.add(new Edge("B", "C", 1));
        edges.add(new Edge("B", "D", 4));
        edges.add(new Edge("C", "D", 2));
        edges.add(new Edge("C", "E", 6));
        edges.add(new Edge("D", "E", 7));

        Set<String> vertices = new HashSet<>();
        vertices.add("A");
        vertices.add("B");
        vertices.add("C");
        vertices.add("D");
        vertices.add("E");

        List<Edge> mst = kruskal(edges, vertices);

        for (Edge edge : mst) {
            System.out.println(edge.source + " - " + edge.destination + ": " + edge.weight);
        }
    }
}
