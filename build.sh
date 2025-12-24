#!/bin/bash
set -e

echo "=== Kobweb Portfolio - Render Build Script ==="

# Check if Java is available
if ! command -v java &> /dev/null; then
    echo "Java not found. Installing..."
    # Render provides Java, but we ensure it's the right version
    export JAVA_HOME=/opt/render/project/.jdk
fi

echo "Java version:"
java -version

# Install Kobweb CLI (needed for export)
echo "Installing Kobweb CLI..."
KOBWEB_VERSION="0.9.18"
if [ ! -f "kobweb/kobweb" ]; then
    curl -fsSL "https://github.com/nicksay/kobweb-cli-releases/releases/download/v${KOBWEB_VERSION}/kobweb-linux-x64.tar.gz" -o kobweb.tar.gz
    tar -xzf kobweb.tar.gz
    rm kobweb.tar.gz
fi
export PATH="$PWD/kobweb:$PATH"

echo "Kobweb version:"
kobweb version || echo "Kobweb CLI installed"

# Navigate to site directory
cd site

# Clean previous builds
echo "Cleaning previous builds..."
../gradlew clean --no-daemon || true

# Export static site for production
echo "Building static site for production..."
../gradlew kobwebExport \
    -PkobwebReuseServer=false \
    -PkobwebEnv=prod \
    -PkobwebExportLayout=STATIC \
    --no-daemon \
    --stacktrace

echo "=== Build Complete ==="
echo "Static files are in: site/build/kobweb/site"

# List the output directory
ls -la build/kobweb/site/ || echo "Output directory not found - check build logs"

