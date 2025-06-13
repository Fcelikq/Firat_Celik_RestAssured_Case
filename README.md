# Pet Store API Test Automation

This project contains automated tests for the Pet Store API using Rest Assured and TestNG.

## Project Structure

```
src/
├── main/java/
│   ├── base/           # Base test configuration
│   ├── constants/      # Constants and configuration
│   ├── helper/         # Helper classes and utilities
│   └── models/         # Request and response models
└── test/
    ├── java/          # Test classes
    └── resources/     # Test resources
```

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Dependencies

- Rest Assured 5.5.1
- TestNG 7.7.1
- Lombok 1.18.30
- Jackson 2.17.0

## Running Tests

To run all tests:
```bash
mvn clean test
```

To run specific test class:
```bash
mvn test -Dtest=AddPetTest
```

## Test Cases

The project includes tests for the following operations:
- Adding a new pet
- Finding pets by status
- Updating pet information
- Deleting a pet
- Uploading pet images

## Configuration

The base URL and other configuration values can be modified in `src/main/java/constants/Constants.java`. 