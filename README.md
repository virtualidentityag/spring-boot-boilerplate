# Getting Started

## Create new project 
To create a new project, create a new github repository and select this project as repository template.
  
## Implement endpoints
#### 1. Define endpoints in api.yaml
Open [api/api.yaml](api/api.yaml) and adapt/extend the endpoint and  model definitions according to OpenAPI 3.0 specification.

To check your api spec visually, run
 ```
cd api/ui-client/
npm install
node app.js
 ```
and call [http://localhost:9999/](http://localhost:9999/) in your browser.
#### 2. Generate server models
Run 
```
mvn clean compile 
```
to generate model classes and controller interfaces for your defined api.
#### 3. Implement Spring Controllers
Create new Java Spring Controllers which implements generated API interfaces and overwrite the generated default implementations for your endpoints:
```java
@Controller
public class AuthorController extends BaseController implements AuthorsApi {

  @Override
  public ResponseEntity<AuthorList> getAuthors(
      @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
      @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) {

    ...
    return new ResponseEntity<>(authorList, HttpStatus.OK);
  }
...
}
```

## Build and deploy project
tbd


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/maven-plugin/)
* [Spring HATEOAS](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/htmlsingle/#boot-features-spring-hateoas)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/htmlsingle/#production-ready)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a Hypermedia-Driven RESTful Web Service](https://spring.io/guides/gs/rest-hateoas/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

