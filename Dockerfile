# Stage 1: Build the static site
FROM gradle:8.14-jdk17 AS builder

WORKDIR /app

# Copy Gradle configuration files first for caching
COPY build.gradle.kts settings.gradle.kts gradle.properties ./
COPY gradle ./gradle
COPY site/build.gradle.kts ./site/

# Copy source code
COPY site/src ./site/src

# Build the static export
RUN cd site && gradle kobwebExport \
    -PkobwebReuseServer=false \
    -PkobwebEnv=prod \
    -PkobwebExportLayout=STATIC \
    --no-daemon \
    --stacktrace

# Stage 2: Serve with Nginx
FROM nginx:alpine

# Copy the static files
COPY --from=builder /app/site/build/kobweb/site /usr/share/nginx/html

# Copy custom nginx config for SPA routing
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]

