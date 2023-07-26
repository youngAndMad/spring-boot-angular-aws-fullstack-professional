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

+ open `src/main/resources/application.yml`
+ change `spring.datasource.username` and `spring.datasource.password` as per your PostgreSQL installation

**4. Run the app using maven**

```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080>
