#!/bin/bash

echo "Stopping all services..."
docker-compose down

echo "Cleaning up..."
docker system prune -f
docker volume prune -f