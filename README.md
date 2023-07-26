# spring-boot-angular-aws-fullstack-professional

## Used technologies
* Spring boot, Spring Security, Spring Data JPA
* Apache Kafka(message broker)
* PostgreSQl database
* Mail sending
* HTML
* CSS
* TypeScript
* Angular
* Amazon S3
* JWT tokens

* ## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/youngAndMad/spring-boot-angular-aws-fullstack-professional
```

**2. Create PostgreSQL database**
```bash
create database fs
```

**3. Change PostreSQL username and password as per your installation**

+ open `server/src/main/resources/application.yml`
+ change `spring.datasource.username` & `spring.datasource.password` as per your PostgreSQL installation

**4. Get credentials from Google App for mail sending**

+ open `mail-sender/src/main/resources/application.yml`
+ change `username` & `password` as per your Google App settings

**5. Get credentials from AWS for S3 bucket **

+ open `server/src/main/resources/application.yml`
+ change `clowd.aws.credentials.secret-key` & `clowd.aws.credentials.access-key` & `clowd.aws.bucket.name` as per your AWS S3 bucket

**6. Run the app using maven**

```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080>
Open API Docs link: <http://localhost:8080/swagger-ui/index.html>

Claster of email verification by verification_token
![image](https://github.com/youngAndMad/spring-boot-angular-aws-fullstack-professional/assets/113981956/9fb3a109-5bc9-4f0a-ae2f-f2bd63c4a0b9)

