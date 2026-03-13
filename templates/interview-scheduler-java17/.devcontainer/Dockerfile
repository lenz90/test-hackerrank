FROM mcr.microsoft.com/devcontainers/java:1-17-bullseye

# Optional but commonly used Java tools
RUN apt-get update \
    && apt-get install -y --no-install-recommends sqlite3 \
    && rm -rf /var/lib/apt/lists/*
