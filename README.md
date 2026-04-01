# Build/Run Instructions
To build and host the server on debian(-based) systems, run:

```
sudo apt-get update && sudo apt-get install -y git openjdk-21-jdk
git clone https://github.com/ArchimedesFTW/opentrivia-backend.git
cd opentrivia-backend
chmod +x gradlew
./gradlew bootJar -x test
java -jar build/libs/*.jar
```
You can verify that the app is working by going to:
http://localhost:8080/swagger-ui/index.html

## Code Architecture
The backend is set up with Java 21, Spring Framework, and Swagger. To keep a clear overview, different types of classes are split into their respective folders. Below, I elaborate on the most relevant folders:

### `controller`
Containing the 2 main API endpoints:
- `/questions` - retrieve a given number of questions
- `/checkanswers` - retrieve the correct answer for each question

### `dto`
Containing all Data Transfer Objects (dto). These classes describe the structure that the frontend should send and receive. These are compiled to the frontend.

### `entity`
Questions are saved, as OpenTrivia Database doesn't provide a way to look up received questions again. As a result, when `/checkanswers` is called, we cannot easily retrieve the question again from OpenTrivia Database. By giving each question a unique ID, and storing it in a database, we can look up the correct answer when `/checkanswers` is called.

### `service`
Does the saving of the questions and the main 'business logic'.

## Testing
I tested the API requests using Postman. I verified that the API behaves as expected on correct inputs and throws the correct errors on failure. The most interesting error that can occur is when Open Trivia Database is 'overloaded' (it only accepts API requests every 5 seconds). Using `@Retryable` on the external API call, this is taken into account, and I verified with Postman that it behaves as expected. The `@Retryable` retries on any Exception to make the external call more robust.

Currently, the backend returns the error code and name (e.g. 400 Bad Request). As a future step, I would also return the error message and update the error notification in the front-end to show the error message (instead of the error name).
