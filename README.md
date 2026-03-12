# Java 17 Codespace Setup

This repository is configured to run in **GitHub Codespaces** with **Java 17** and GitHub Copilot.

## Included files

- `.devcontainer/devcontainer.json` – Codespaces/Dev Container configuration
- `.devcontainer/Dockerfile` – Base image with Java 17

## What you get

- Java 17 runtime and toolchain
- Maven and Gradle support
- VS Code Java extensions
- GitHub Copilot + Copilot Chat extensions preinstalled

## How to use

1. Open the repository in GitHub.
2. Click **Code** → **Codespaces** → **Create codespace on main**.
3. Wait for the container build to finish.
4. Verify Java:
   ```bash
   java -version
   ```

You should see a Java 17 version in the output.
