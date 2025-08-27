#!/bin/zsh

# Exit immediately if any command fails
set -e

export GDK_BACKEND=x11

# Clean and compile
mvn clean compile

# Force X11 + GTK2
# Run the application
mvn exec:java \
  -Dexec.mainClass="ctn.infaut.App" \
  -Dexec.jvmArgs="-Djdk.gtk.version=2 -Dprism.verbose=true"

