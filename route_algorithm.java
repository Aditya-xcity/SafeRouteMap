import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class RouteEdge {
    String destination;
    double weight;
    double time;

    RouteEdge(String destination, double weight, double time) {
        this.destination = destination;
        this.weight = weight;
        this.time = time;
    }
}

class PathResult {
    List<String> path;
    double totalDistance;

    PathResult(List<String> path, double totalDistance) {
        this.path = path;
        this.totalDistance = totalDistance;
    }
}

public class route_algorithm {
    private static final double INF = Double.POSITIVE_INFINITY;
    private final Map<String, List<RouteEdge>> nodes = new HashMap<>();

    public void addNode(String node) {
        nodes.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String source, String destination, double weight, double time) {
        nodes.putIfAbsent(source, new ArrayList<>());
        nodes.putIfAbsent(destination, new ArrayList<>());
        nodes.get(source).add(new RouteEdge(destination, weight, time));
        nodes.get(destination).add(new RouteEdge(source, weight, time));
    }

    public PathResult findShortestPath(String startNode, String endNode) {
        Map<String, Double> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<RouteNodeDistance> pq =
            new PriorityQueue<>(Comparator.comparingDouble(node -> node.distance));

        for (String node : nodes.keySet()) {
            distances.put(node, INF);
        }

        distances.put(startNode, 0.0);
        pq.add(new RouteNodeDistance(startNode, 0.0));

        while (!pq.isEmpty()) {
            RouteNodeDistance currentNode = pq.poll();
            String current = currentNode.node;
            double currentDist = currentNode.distance;

            if (current.equals(endNode)) {
                return reconstructPath(previous, startNode, endNode, distances.get(endNode));
            }

            if (currentDist > distances.getOrDefault(current, INF)) {
                continue;
            }

            for (RouteEdge neighbor : nodes.getOrDefault(current, Collections.emptyList())) {
                double distance = distances.get(current) + neighbor.weight;
                if (distance < distances.getOrDefault(neighbor.destination, INF)) {
                    distances.put(neighbor.destination, distance);
                    previous.put(neighbor.destination, current);
                    pq.add(new RouteNodeDistance(neighbor.destination, distance));
                }
            }
        }

        return new PathResult(new ArrayList<>(), INF);
    }

    private PathResult reconstructPath(
        Map<String, String> previous,
        String startNode,
        String endNode,
        double totalDistance
    ) {
        List<String> path = new ArrayList<>();
        String current = endNode;

        while (current != null) {
            path.add(current);
            current = previous.get(current);
        }

        Collections.reverse(path);
        if (!path.isEmpty() && !path.get(0).equals(startNode)) {
            return new PathResult(new ArrayList<>(), INF);
        }
        return new PathResult(path, totalDistance);
    }

    private static class RouteNodeDistance {
        String node;
        double distance;

        RouteNodeDistance(String node, double distance) {
            this.node = node;
            this.distance = distance;
        }
    }
}
