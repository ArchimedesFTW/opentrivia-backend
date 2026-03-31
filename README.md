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
