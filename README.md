# Google Image Downloader
Using this service, you can search for mixed words in Google and save the results of the found photos
This service is written as full reactive programming (asynchronous) and works as no-blocking
After receiving the number of photos desired by the user and receiving and displaying its information to the user
asynchronously, it will start receiving resizing and saving the photos to postgres database.

### More info
The stored photos are stored in the image table, and in the data field, the photo is stored as an oid of LOB type
You can use the following sql command to get the photo binary
```sql
select lo_get(data) from image limit 1;
```

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
first set environment variable in docker compose file
```shell
SERVER_PORT: 8081
SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/image_downloader
SPRING_DATASOURCE_USERNAME: postgres
SPRING_DATASOURCE_PASSWORD: postgres
GOOGLE_APIKEY: ""
GOOGLE_CX: ""
IMAGE_RESIZE_WIDTH: 100
IMAGE_RESIZE_HEIGHT: 100
```
next run docker compose by bellow command
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
  cx: "<SEARCH_ENGINE_ID>"
```
You can refer to [this link](https://console.cloud.google.com/apis/credentials/key) to get `api-key` <br>
And refer to [this link](https://programmablesearchengine.google.com/controlpanel/all) to get `cx`

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