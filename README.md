# mpr-project

This is a Java-based web application project that uses Spring Boot and Gradle for its development. The project also incorporates JavaScript for some of its functionalities. The application uses simple Data Transfer Object (DTO) and an in-memory H2 database. The primary purpose of this project is to develop and enhance testing skills. The project is structured into separate modules for the backend and frontend, allowing for clear separation of concerns and easier maintenance.

## Technologies Used

- **Java**: The primary programming language used for the development of this project.
- **Spring Boot**: A Java-based framework used to create stand-alone, production-grade Spring-based applications.
- **Gradle**: A powerful build tool, especially for Java projects. It handles tasks like library downloads, packaging, testing, and deployment.
- **JavaScript**: Used to enhance the user interface and user experience.
- **H2 Database**: An in-memory database used for development and testing purposes.

 ## Testing

The project uses various types of tests to ensure the quality and correctness of the code. The testing frameworks used in this project are JUnit, Mockito, RestAssured and Selenium.

- **Unit Tests**: These tests verify the functionality of individual components of the application, such as methods or classes. They are written using JUnit and Mockito. For example, the `getPersonByIdShouldReturnPersonWithGivenId()` method in the `PersonServiceTest` class tests the `getPersonById()` method in the `PersonService` class.

- **Integration Tests**: These tests check the interaction between different components of the application. For instance, the `findPersonByIdShouldReturnCheck200()` method in the `PersonControllerIntegrationTest` class tests the interaction between the `PersonController` and the `PersonService`.

- **End-to-End Tests**: These tests verify the flow of an application from start to finish. They ensure that the integrated components of an application function as expected together, from the user's perspective. For example, the `clickSubmitButtonShouldNavigateToTheMainPage()` method in the `SeleniumDeletePersonTest` class tests the entire flow of deleting a person.

- **Exception Tests**: These tests ensure that the code behaves correctly in the event of an error. For example, the `getPersonByIdShouldThrowPersonNotFoundException()` method in the `PersonServiceTest` class tests that the `getPersonById()` method throws an exception when the `PersonRepository` returns an error status.

- **Mock Tests**: These tests use mock objects to simulate the behavior of real objects. For example, the `addPersonShouldSavePerson()` method in the `PersonServiceTest` class uses a mock `PersonRepository` to simulate the behavior of the actual `PersonRepository`.

- **Rest API Tests**: These tests verify the functionality of the REST API endpoints. They are written using RestAssured. For example, the `addPersonShouldReturnCode202()` method in the `RestAssureControllerTest` class tests the `addPerson()` endpoint in the `PersonController`.

## How to Run

You can run this project by cloning the repository and importing it into IntelliJ IDEA. Then, use the built-in Gradle wrapper to build and run the project.

Please note that you need to have Java installed on your machine to run this project.
