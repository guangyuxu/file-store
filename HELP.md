# Build
Please make sure you have installed the JDK 11 and setup the $JAVA_HOME for JDK11
```
gradlew clean build
```
# Http server
### Run with IDE
```
main class: com.woven.gavin.fs.api.ApiApplication
VMOption: -Dfile.upload.path=~/tmp
```

### Run with java command
```
# 1. build project
cd file-store/
./gradlew clean build
# 2. run server 
java -jar file-store/api/build/libs/*.jar -D
```

### Run with Docker
##### 1. build project
```
cd file-store/
./gradlew clean build
```
##### 2. build docker
```
cd api
docker build -t woven/gavin:latest -f DockerFile .
```
##### 3. create mounted folders for Linux/Macos 
```
mkdir -p /tmp/woven/app-log /tmp/woven/gc-log /tmp/woven/uploaded
```
##### 4. run docker by docker or docker-compose
Option 1, Run by docker
```
docker run --rm -d -p 10001:10001 -p 11619:11619 \
-v /tmp/woven/app-log:/woven/app-log \
-v /tmp/woven/gc-log:/woven/gc-log \
-v /tmp/woven/uploaded:/woven/uploaded \
woven/gavin
```
Option 2, Run by docker-compose
```
docker-compose up
```
### Parameters
* file.upload.path

Currently, the folder, ${file.upload.path}/upload, is used to upload only. It is ~/ by default.

### Validate the server
* http://localhost:10001

### Swagger UI
* http://localhost:10001/swagger-ui.html

# Run command client
```
java -jar command-client/build/libs/command-client-*.jar
```
### commands
```
file-list
file-upload <fileName with full path>
file-delete <fileName>
```