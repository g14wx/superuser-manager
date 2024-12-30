#!/bin/sh
# Get the application port from environment variable or use default
PORT="${SERVER_PORT:-8080}"

# Try to connect to the health endpoint
response=$(curl -s -w "%{http_code}" http://localhost:${PORT}/actuator/health)
status=$?

# Check if curl command was successful and the response was 200
if [ $status -eq 0 ] && [ "$response" = "200" ]; then
    exit 0
else
    exit 1
fi