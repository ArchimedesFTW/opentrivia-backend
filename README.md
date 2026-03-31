# Build/Run Instructions
To build and host the server on Linux, run:

```
sudo apt-get update && sudo apt-get install -y git openjdk-21-jdk
git clone https://github.com/ArchimedesFTW/opentrivia-backend.git
cd opentrivia-backend
chmod +x gradlew
./gradlew bootJar -x test
java -jar build/libs/*.jar
```
You can verify that the app is working by going to:
http://localhost:8080/swagger-ui/

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
