FROM maven:3.9.6-eclipse-temurin-17
WORKDIR /app
COPY . .
# Cache Maven packages
RUN mvn dependency:go-offline
# Run specific test with forkCount=0
CMD ["mvn", "clean", "test", "-DforkCount=0", "-Dtest=Assigment1#"]