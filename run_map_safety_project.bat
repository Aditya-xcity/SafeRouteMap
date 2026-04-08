@echo off
setlocal

echo ========================================
echo   Map Safety Project - Java Runner
echo ========================================
echo.

where javac >nul 2>nul
if errorlevel 1 (
    echo [ERROR] javac not found. Install JDK and add it to PATH.
    echo         Download: https://adoptium.net/
    pause
    exit /b 1
)

echo [1/3] Compiling Java files...
javac *.java
if errorlevel 1 (
    echo.
    echo [ERROR] Compilation failed.
    pause
    exit /b 1
)

echo.
echo [2/3] Running Java backend demo...
java backend_demo
if errorlevel 1 (
    echo.
    echo [ERROR] Runtime failed.
    pause
    exit /b 1
)

echo.
echo [3/3] Opening map UI in browser...
if exist "map_ui.html" (
    start "" "map_ui.html"
) else (
    echo [WARN] map_ui.html not found. Skipping UI launch.
)

echo.
echo Done.
pause
exit /b 0
