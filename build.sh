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
export GRADLE_OPTS="-Xmx2g -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8"

echo "=== Building Kobweb Static Site ==="
chmod +x gradlew

# Build the static site
./gradlew :site:kobwebExport \
    --no-daemon \
    --no-configuration-cache \
    -Dorg.gradle.jvmargs="-Xmx2g" \
    -Dkotlin.daemon.jvmargs="-Xmx1g"

echo "=== Restructuring for static hosting ==="
SITE_DIR="site/.kobweb/site"

# Move index.html and JS from system/ to root
cp "$SITE_DIR/system/index.html" "$SITE_DIR/"
cp "$SITE_DIR/system/ashwa.js" "$SITE_DIR/"
cp "$SITE_DIR/system/ashwa.js.map" "$SITE_DIR/" 2>/dev/null || true

# Move resources to root level
if [ -d "$SITE_DIR/resources" ]; then
    cp -r "$SITE_DIR/resources/"* "$SITE_DIR/" 2>/dev/null || true
fi

# Create logo.png from kobweb-logo.png if needed
if [ ! -f "$SITE_DIR/logo.png" ] && [ -f "$SITE_DIR/kobweb-logo.png" ]; then
    cp "$SITE_DIR/kobweb-logo.png" "$SITE_DIR/logo.png"
    echo "Created logo.png from kobweb-logo.png"
fi

echo "=== Build Complete ==="
echo "Contents of site/.kobweb/site:"
ls -la "$SITE_DIR/"

