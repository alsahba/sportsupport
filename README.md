# SportSupport App

This project is about sports center management system. Users can register themselves, can purchase memberships, can request for weekly training plans from their trainers.
There are five main roles for now. Owners can do pretty much everything, managers manage their branches, trainers can control their trainees' training plans.
The project is not finished, in most respects, we cannot count it as yet started. Although most of the essential use cases are finished, there are still many that need to be added.

Tech-stack:
- Spring Boot with Java 17 (Secured with the Spring Security)
- Redis
- Liquibase
- Postgresql
- Docker
- Kafka
- Swagger3

Also, hexagonal architecture attempted to apply, implementation of it inspired by the Tom Hombergs' implementation for the book 'Get Your Hands Dirty on Clean Architecture: A Hands-on Guide to Creating Clean Web Applications with Code Examples in Java'.

Meaning of the 'apply attempt'  is about that this is neither completely true nor completely false implementation for hexagonal architecture. I'm still in learning phase of it like majority of our development community. So, please feel free for contributing the code or reaching me that the points that you want the touch.

If you want to try project without complete dockerization, you can use the following command for infrastructure setup:

```docker-compose -f infra_docker-compose.yml -p sport_support_infra up ```

You can find the api documentation in this address:

```http://localhost:8080/swagger-ui/index.html/```

The test data for this project is managed by liquibase. You can find the changelog in the project root. 
You can run the following command to update the database:

```mvn liquibase:update```



