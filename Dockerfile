FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app
COPY . .

# Cache Maven packages
RUN mvn dependency:go-offline

# Default command to run tests
CMD ["mvn", "test"]