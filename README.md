# Map Safety Project

A Java-based demo project for map search, route finding, and safe-route calculation, with a simple HTML UI.

## Features

- Address search and autocomplete demo
- Shortest path calculation (Dijkstra)
- Safe route calculation (A*-style scoring with safety)
- Browser-based map UI page

## Project Structure

- `backend_demo.java` - Runs backend demos/tests
- `search_autocomplete.java` - Search/autocomplete module
- `route_algorithm.java` - Route algorithm module
- `safe_route.java` - Safe route module
- `map_ui.html` - Frontend map UI
- `seat_reservation_demo.java` - Runnable synchronization seat booking demo
- `run_map_safety_project.bat` - Windows script to compile and run the project

## Requirements

- JDK 8 or newer
- Windows PowerShell / Command Prompt

## Run (Recommended)

Use the provided script:

```bat
run_map_safety_project.bat
```

This script will:

1. Compile all `.java` files
2. Run `backend_demo`
3. Open `map_ui.html` in your browser

## Manual Run

```bat
javac *.java
java backend_demo
```

Then open `map_ui.html` manually in your browser if needed.

## Notes

- The project currently contains generated `.class` files from previous builds.
- See `.gitignore` to keep generated files out of version control.
