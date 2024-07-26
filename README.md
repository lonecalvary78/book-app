## Demo Book API
[![Build and deploy Book App](https://github.com/lonecalvary78/book-app/actions/workflows/build-and-deploy.yaml/badge.svg)](https://github.com/lonecalvary78/book-app/actions/workflows/build-and-deploy.yaml)
### Getting Started
Since this application running on the container, you execute the command below
```
mvn clean install -U -Dquarkus.container-image.build=true -Dquarkus.container-image.name=book-app -Dquarkus.container-image.tag=latest
```

That command will do build the image through the maven build execution

### Running the application 
To run the application on the docker container with the specific enviroment, please follow the instruction below

#### Development
```
docker run --name=book-app -d -p 8080:8080 -e TARGET_ENV=dev book-app:latest
```

#### Testing
```
docker run --name=book-app -d -p 8080:8080 -e TARGET_ENV=testing book-app:latest
```

#### Production
```
docker run --name=book-app -d -p 8080:8080 -e TARGET_ENV=prod book-app:latest
```
