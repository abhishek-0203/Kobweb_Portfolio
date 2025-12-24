#!/bin/bash
set -e

echo "=== Installing Java 17 ==="
curl -fsSL https://download.oracle.com/java/17/archive/jdk-17.0.12_linux-x64_bin.tar.gz -o jdk.tar.gz
tar -xzf jdk.tar.gz
export JAVA_HOME=$PWD/jdk-17.0.12
export PATH=$JAVA_HOME/bin:$PATH
java -version

echo "=== Building Kobweb Static Site ==="
cd site
chmod +x ../gradlew
../gradlew kobwebExport -PkobwebReuseServer=false -PkobwebEnv=prod -PkobwebExportLayout=STATIC --no-daemon --stacktrace

echo "=== Build Complete ==="
ls -la build/kobweb/site/

