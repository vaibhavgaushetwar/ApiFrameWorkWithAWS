# Use a base image with Maven and Java
FROM maven:3.8.3-openjdk-11

# Set the working directory inside the container
WORKDIR /VaibhavGaushetwar

# Copy only the pom.xml file first
COPY pom.xml .
COPY src ./src
# Switch the working directory inside the container
WORKDIR /VaibhavGaushetwar

# Run Maven to compile and install the project
CMD ["mvn", "clean", "install"]