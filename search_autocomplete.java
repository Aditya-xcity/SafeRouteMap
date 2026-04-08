import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Locale;

class Location {
    String displayName;
    double lat;
    double lon;

    Location(String displayName, double lat, double lon) {
        this.displayName = displayName;
        this.lat = lat;
        this.lon = lon;
    }
}

public class search_autocomplete {
    private final Deque<String> recentSearches = new ArrayDeque<>();
    private static final String SEARCH_API_URL = "https://nominatim.openstreetmap.org/search";

    public List<Location> geocodeAddress(String address) {
        List<Location> mockResponse = new ArrayList<>();
        mockResponse.add(new Location("Delhi, India", 28.7041, 77.1025));
        mockResponse.add(new Location("New Delhi, India", 28.6139, 77.2090));
        return mockResponse;
    }

    public List<Location> getSuggestions(String inputText, List<Location> data) {
        String lowerInput = inputText.toLowerCase(Locale.ROOT);
        List<Location> suggestions = new ArrayList<>();

        for (Location item : data) {
            String lowerName = item.displayName.toLowerCase(Locale.ROOT);
            if (lowerName.contains(lowerInput)) {
                suggestions.add(item);
            }
        }

        return suggestions;
    }

    public void saveRecentSearch(String searchQuery) {
        if (!recentSearches.contains(searchQuery)) {
            recentSearches.addFirst(searchQuery);
            if (recentSearches.size() > 10) {
                recentSearches.removeLast();
            }
        }
    }

    public List<String> getRecentSearches() {
        return Collections.unmodifiableList(new ArrayList<>(recentSearches));
    }
}
