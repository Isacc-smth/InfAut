#!/bin/zsh

# Exit immediately if any command fails
set -e

# Clean and compile
mvn clean compile

# Run the application
mvn exec:java -Dexec.mainClass="ctn.infaut.App"
