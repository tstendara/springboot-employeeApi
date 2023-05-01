# Employee API

This is a basic RESTful API for managing employees.

## Technologies

This project was built using the following technologies:

- Java
- Gradle
- Spring Boot
- Docker

## Endpoints
<a href="https://www.postman.com/tsten/workspace/employeeapi/overview" target="_blank">Click here to view the endpoints in Postman</a>

> **Note:** Please note that to run the endpoints on your localhost, it's recommended to use the Postman app instead of the website. 


| Method | Endpoint | Description |
| ------ | -------- | ----------- |
| POST   | /api/v1/employees/ | Create a new employee |
| GET    | /api/v1/employees/ | Retrieve all employees |
| GET    | /api/v1/employees/{id} | Retrieve an employee by ID |
| PUT    | /api/v1/employees/{id} | Update an employee by ID |
| DELETE | /api/v1/employees/{id} | Delete an employee by ID |

The expected request body for creating and updating an employee is:
```
{
"firstName":"John",
"lastName": "Doe",
"jobRole": "Software Engineer"
}
```

## Running the application  

Navigate to the root of the directory and run the following commands:


`./gradlew build` - This will run tests using in-memory H2 DB and once all tests pass, it will continue to  build a JAR file for the application that can be found in the `build/libs` directory.


`docker-compose up` - This will start two containers - one for MySQL and another for the application JAR file. The JAR container will be available at http://localhost:8080, while the MySQL container will be available at localhost:3306.








