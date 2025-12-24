# Stage 1: Build the static site
FROM gradle:8.14-jdk17 AS builder

WORKDIR /app

# Copy all project files (Gradle needs full project structure)
COPY . .

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
COPY nginx.conf /etc/nginx/nginx-site.conf

# Create nginx config that uses PORT env variable (Render requirement)
RUN echo 'server { \
    listen ${PORT:-80}; \
    server_name localhost; \
    root /usr/share/nginx/html; \
    index index.html; \
    gzip on; \
    gzip_types text/plain text/css application/json application/javascript text/xml application/xml application/xml+rss text/javascript; \
    location ~* \.(js|css|png|jpg|jpeg|gif|ico|svg|woff|woff2|ttf|eot|webp)$ { \
        expires 1y; \
        add_header Cache-Control "public, immutable"; \
    } \
    location / { \
        try_files $uri $uri/ /index.html; \
    } \
}' > /etc/nginx/templates/default.conf.template

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]

