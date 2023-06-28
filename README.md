# basicspringapp

This is a basic Spring application for managing books and authors.

## Prerequisites

Before running the application, make sure you have the following software installed on your system:

- Java Development Kit (JDK) 8 or higher
- Maven

## Getting Started

Follow the steps below to build and run the application:

1. Clone the repository: git clone https://github.com/alfastep/basicspringapp.git
2. Navigate to the project directory: cd basicspringapp
3. Build the project using Maven: ./mvnw clean package
   
This command will compile the source code, run tests, and package the application into a JAR file located at `target/basicspringapp-0.0.1-SNAPSHOT.jar`.

4. Run the application: java -jar target/basicspringapp-0.0.1-SNAPSHOT.jar
   
This command will start the Spring application, and you should see the application running at `http://localhost:8080`.

5. Access the application in your web browser:
   
Open your web browser and navigate to `http://localhost:8080` to access the application. You can now interact with the book and author management features.

## Configuration

The application uses an in-memory H2 database by default. If you want to configure a different database or modify any other application settings, you can update the `application.properties` file located in the `src/main/resources` directory.

## Contributing

Contributions to this project are welcome. If you find any issues or have suggestions for improvements, feel free to open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).
