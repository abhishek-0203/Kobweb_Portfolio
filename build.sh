#!/bin/bash
set -e

echo "=== Installing Java 17 ==="
# Use Adoptium (Eclipse Temurin) Java 17 - stable and widely supported
curl -fsSL https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.9%2B9/OpenJDK17U-jdk_x64_linux_hotspot_17.0.9_9.tar.gz -o jdk.tar.gz
tar -xzf jdk.tar.gz
export JAVA_HOME=$PWD/jdk-17.0.9+9
export PATH=$JAVA_HOME/bin:$PATH
rm jdk.tar.gz
java -version

echo "=== Setting Gradle Options ==="
# Reduce memory usage for Render's environment
export GRADLE_OPTS="-Xmx2g -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8"

echo "=== Building Kobweb Static Site ==="
chmod +x gradlew

# Clean and build the static site
./gradlew :site:kobwebExport \
    --no-daemon \
    --no-configuration-cache \
    -Dorg.gradle.jvmargs="-Xmx2g" \
    -Dkotlin.daemon.jvmargs="-Xmx1g"

echo "=== Restructuring output for static hosting ==="
# Create a flat structure for Render static hosting
mkdir -p site/.kobweb/dist

# Copy system files (index.html, JS) to root
cp site/.kobweb/site/system/index.html site/.kobweb/dist/
cp site/.kobweb/site/system/ashwa.js site/.kobweb/dist/
cp -f site/.kobweb/site/system/ashwa.js.map site/.kobweb/dist/ 2>/dev/null || true

# Copy resources to root
cp -r site/.kobweb/site/resources/* site/.kobweb/dist/ 2>/dev/null || true

# Copy pre-rendered pages as their own HTML files
for page in site/.kobweb/site/pages/*.html; do
    if [ -f "$page" ]; then
        filename=$(basename "$page")
        pagename="${filename%.html}"
        if [ "$pagename" = "index" ]; then
            # index.html already copied from system
            continue
        fi
        # Create directory for the page and copy index.html for clean URLs
        mkdir -p "site/.kobweb/dist/$pagename"
        cp "$page" "site/.kobweb/dist/$pagename/index.html"
    fi
done

echo "=== Build Complete ==="
echo "Contents of site/.kobweb/dist:"
ls -la site/.kobweb/dist/

