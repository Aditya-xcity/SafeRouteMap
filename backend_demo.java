import java.util.List;

public class backend_demo {
    public static void main(String[] args) {
        System.out.println("=== Map Safety Project: Java Backend Tests ===");
        System.out.println();

        System.out.println("[1] Testing Search & Autocomplete...");
        search_autocomplete searchSystem = new search_autocomplete();
        List<Location> results = searchSystem.geocodeAddress("Delhi");
        System.out.println("Found locations for 'Delhi':");
        for (Location loc : results) {
            System.out.println(" - " + loc.displayName + " (Lat: " + loc.lat + ", Lon: " + loc.lon + ")");
        }

        searchSystem.saveRecentSearch("Delhi");
        searchSystem.saveRecentSearch("Mumbai");
        System.out.println("Recent Searches:");
        for (String recent : searchSystem.getRecentSearches()) {
            System.out.println(" - " + recent);
        }
        System.out.println();

        System.out.println("[2] Testing Shortest Path Algorithm (Dijkstra)...");
        route_algorithm cityGraph = new route_algorithm();
        cityGraph.addNode("A");
        cityGraph.addNode("B");
        cityGraph.addNode("C");
        cityGraph.addEdge("A", "B", 5.0, 10.0);
        cityGraph.addEdge("B", "C", 3.0, 6.0);
        cityGraph.addEdge("A", "C", 10.0, 20.0);

        PathResult shortestPath = cityGraph.findShortestPath("A", "C");
        System.out.print("Shortest Path from A to C: ");
        for (String node : shortestPath.path) {
            System.out.print(node + " ");
        }
        System.out.println();
        System.out.println("Total Distance: " + shortestPath.totalDistance);
        System.out.println();

        System.out.println("[3] Testing Safe Route Calculation (A* Algorithm)...");
        safe_route safeCityGraph = new safe_route();
        safeCityGraph.addNode("A");
        safeCityGraph.addNode("B");
        safeCityGraph.addNode("C");
        safeCityGraph.addEdge("A", "B", 5.0, 90.0);
        safeCityGraph.addEdge("B", "C", 3.0, 20.0);
        safeCityGraph.addEdge("A", "C", 10.0, 100.0);

        List<String> safestPath = safeCityGraph.findSafestPathAStar("A", "C");
        System.out.print("Safest Path from A to C: ");
        for (String node : safestPath) {
            System.out.print(node + " ");
        }
        System.out.println();
        System.out.println("(Notice how it prioritizes the safer A->C route based on distance + safety weighting.)");
    }
}
