# Google Image Downloader
Using this service, you can search for mixed words in Google and save the results of the found photos
This service is written as full reactive programming (asynchronous) and works as no-blocking
After receiving the number of photos desired by the user and receiving and displaying its information to the user
asynchronously, it will start receiving resizing and saving the photos to postgres database.

## Requirements
- Java >=17
- Maven >=3.6
- Postgres >=15
- Docker Engine >=24

## Setup and run

1. Run bellow command for compile project
```shell
cd <PROJECT_DIR>
./mvnw clean package
```

2. [Setup](#configuration) application.yml
3. Run bellow command for run project
```shell
cd <PROJECT_DIR>
java -jar target/<application_name>.jar
```
### Run by docker
```shell
cd <PROJECT_DIR>
docker compose up -d
```

## Configuration

### Database configuration
```yaml
spring:
  datasource:
    url: "jdbc:postgresql://<HOST>:<PORT>/image_downloader"
    username: "<USERNAME>"
    password: "<PASSWORD>"
```

### Google properties
Set bellow properties for Google search :
```yaml
google:
  api-key: "<API_KEY>"
  cx: "<PROGRAMMABLE_SEARCH_ENGINE>"
```
Set bellow property for image resize :
```yaml
image:
  resize:
    width: "<WIDTH_SIZE>"
    height: "<HEIGHT_SIZE>"
```

## Using
The project includes Swagger documentation. To use it, you can open the following URL in your browser while
the application is running:
```http request
http://localhost:8081/webjars/swagger-ui/index.html
```