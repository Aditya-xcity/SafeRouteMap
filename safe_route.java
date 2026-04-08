import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class SafeEdge {
    String destination;
    double distance;
    double safetyScore;

    SafeEdge(String destination, double distance, double safetyScore) {
        this.destination = destination;
        this.distance = distance;
        this.safetyScore = safetyScore;
    }
}

public class safe_route {
    private static final double SAFE_INF = Double.POSITIVE_INFINITY;
    private final Map<String, List<SafeEdge>> nodes = new HashMap<>();

    private double calculateSafetyWeight(double safetyScore) {
        final double maxSafety = 100.0;
        return (maxSafety - safetyScore) * 10.0;
    }

    private double heuristic(String nodeA, String nodeB) {
        return 0.0;
    }

    public void addNode(String node) {
        nodes.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String source, String destination, double distance, double safetyScore) {
        nodes.putIfAbsent(source, new ArrayList<>());
        nodes.putIfAbsent(destination, new ArrayList<>());
        nodes.get(source).add(new SafeEdge(destination, distance, safetyScore));
        nodes.get(destination).add(new SafeEdge(source, distance, safetyScore));
    }

    public List<String> findSafestPathAStar(String startNode, String endNode) {
        Map<String, Double> gScore = new HashMap<>();
        Map<String, Double> fScore = new HashMap<>();
        Map<String, String> cameFrom = new HashMap<>();
        PriorityQueue<RouteNodeScore> openSet =
            new PriorityQueue<>(Comparator.comparingDouble(node -> node.score));

        for (String node : nodes.keySet()) {
            gScore.put(node, SAFE_INF);
            fScore.put(node, SAFE_INF);
        }

        gScore.put(startNode, 0.0);
        fScore.put(startNode, heuristic(startNode, endNode));
        openSet.add(new RouteNodeScore(startNode, fScore.get(startNode)));

        while (!openSet.isEmpty()) {
            RouteNodeScore currentNode = openSet.poll();
            String current = currentNode.node;
            double currentFScore = currentNode.score;

            if (current.equals(endNode)) {
                return reconstructPath(cameFrom, current);
            }

            if (currentFScore > fScore.getOrDefault(current, SAFE_INF)) {
                continue;
            }

            for (SafeEdge neighbor : nodes.getOrDefault(current, Collections.emptyList())) {
                double edgeWeight = neighbor.distance + calculateSafetyWeight(neighbor.safetyScore);
                double tentativeGScore = gScore.get(current) + edgeWeight;

                if (tentativeGScore < gScore.getOrDefault(neighbor.destination, SAFE_INF)) {
                    cameFrom.put(neighbor.destination, current);
                    gScore.put(neighbor.destination, tentativeGScore);
                    double newFScore = tentativeGScore + heuristic(neighbor.destination, endNode);
                    fScore.put(neighbor.destination, newFScore);
                    openSet.add(new RouteNodeScore(neighbor.destination, newFScore));
                }
            }
        }

        return new ArrayList<>();
    }

    private List<String> reconstructPath(Map<String, String> cameFrom, String current) {
        List<String> path = new ArrayList<>();
        path.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(current);
        }

        Collections.reverse(path);
        return path;
    }

    private static class RouteNodeScore {
        String node;
        double score;

        RouteNodeScore(String node, double score) {
            this.node = node;
            this.score = score;
        }
    }
}
