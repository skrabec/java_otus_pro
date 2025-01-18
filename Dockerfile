FROM maven:3.9.6-eclipse-temurin-17

# Install curl if not present (needed for Allure installation)
RUN apt-get update && apt-get install -y curl

# Install Allure
RUN curl -o allure-2.24.1.tgz -Ls https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/2.24.1/allure-commandline-2.24.1.tgz && \
    tar -zxvf allure-2.24.1.tgz -C /opt/ && \
    ln -s /opt/allure-2.24.1/bin/allure /usr/bin/allure && \
    rm allure-2.24.1.tgz

WORKDIR /app
COPY . .

# Cache Maven packages
RUN mvn dependency:go-offline

# Run specific test with forkCount=0
CMD ["mvn", "clean", "test", "-DforkCount=0", "-Dtest=Assigment1#"]