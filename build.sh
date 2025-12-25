#!/bin/bash
set -e

echo "=== Installing Java 17 ==="
# Use Adoptium (Eclipse Temurin) which is more reliable
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

# Clean any previous builds and build the static site
./gradlew :site:kobwebExport \
    -PkobwebReuseServer=false \
    -PkobwebEnv=prod \
    -PkobwebExportLayout=STATIC \
    --no-daemon \
    --no-configuration-cache \
    -Dorg.gradle.jvmargs="-Xmx2g" \
    -Dkotlin.daemon.jvmargs="-Xmx1g" \
    --stacktrace

echo "=== Build Complete ==="
echo "Contents of site/.kobweb/site:"
ls -la site/.kobweb/site/

