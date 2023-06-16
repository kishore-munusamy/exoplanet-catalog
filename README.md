# Exoplanet Catalog

The Exoplanet Catalog is a Spring Boot application that provides information about exoplanets. It retrieves data from an external API.

## Prerequisites

Before running the application, make sure you have the following prerequisites installed:

- Java Development Kit (JDK) 8 or higher
- Apache Maven
- Docker (optional, for containerization)
- Kubernetes (optional, for deployment)

## Getting Started

Follow the steps below to set up and run the Exoplanet Catalog application.

### 1. Clone the Repository

Clone the repository to your local machine:

git clone https://github.com/kishore-munusamy/exoplanet-catalog.git

### 2. Build the Project
Navigate to the project directory and build the project using Maven:

- cd exoplanet-catalog
- mvn clean install

This command compiles the source code, runs tests, and creates an executable JAR file.

### 3. Run the Application
To run the application, use the following command:

- java -jar target/exoplanet-catalog-1.0-SNAPSHOT.jar

The application will start and be accessible at http://localhost:8080.

### 4. API Endpoints
The Exoplanet Catalog provides the following API endpoints:

- **GET http://localhost:8080/api/exoplanets/orphans**
  Gets the count of orphan planets.
- **GET http://localhost:8080/api/exoplanets/hottest-star** 
  Gets the name of the planet orbiting the hottest star.
- **GET http://localhost:8080/api/exoplanets/timeline** 
  Gets a timeline of planet counts categorized by size.
You can access these endpoints by making HTTP requests to the appropriate URLs.

### 5. Execute Tests
You can execute the tests using the following command:

- mvn clean test

This command will run all the unit tests in the project.

### 6. Containerization with Docker (Optional)
To containerize the application with Docker, make sure Docker is installed on your machine.

Build the Docker image using the provided Dockerfile:

- mvn clean install

This command will build a Docker image tagged as kishoremunusamy6011/exoplanet-catalog:LATEST.

Run the Docker container:

- docker run -p 8080:8080 kishoremunusamy6011/exoplanet-catalog:LATEST

The application will be accessible at http://localhost:8080, running inside the Docker container.

### 7. Deployment with Kubernetes (Optional)
To deploy the application to a Kubernetes cluster, make sure kubectl is installed and properly configured to connect to your cluster.

Apply the Kubernetes deployment and service configuration files provided:

- kubectl create namespace exoplanet-catalog 
- kubectl apply -f src/main/kubernetes/exoplanet-catalog-deployment.yaml -n exoplanet-catalog 
- kubectl apply -f src/main/kubernetes/exoplanet-catalog-service.yaml -n exoplanet-catalog

Verify the newly created pods, deployments and service

- kubectl get pods -n exoplanet-catalog
- kubectl get deployments -n exoplanet-catalog
- kubectl get services -n exoplanet-catalog

This will create a deployment and a service for the application in your Kubernetes cluster.

To access the application, use the external IP or hostname assigned to the service.
and it will be accessible at http://localhost:8080.