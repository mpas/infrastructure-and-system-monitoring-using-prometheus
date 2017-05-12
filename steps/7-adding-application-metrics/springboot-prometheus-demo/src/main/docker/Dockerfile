FROM java:8u66-jdk

## Publish the 8080 port
EXPOSE 8080

# Create app that holds the application.jar file
RUN mkdir -p /app
WORKDIR /app

COPY /app/application.jar application.jar
COPY /app/docker-entrypoint.sh docker-entrypoint.sh

# Set file permissions
RUN chmod +x docker-entrypoint.sh

# Set start script as default command
CMD ["./docker-entrypoint.sh"]