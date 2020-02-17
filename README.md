# Getting Started

## Create new project 
1. To create a new project, create a new github repository and select this project as repository template.
2. Import the project in your favourite IDE.
3. Go to folder `init-script` and run:
    <pre><code>npm install
   npm start</code></pre>
4. Delete folder `init-script`
7. (Optional) Adapt the CI/CD workflow under[.github/workflows/onPush.yml](.github/workflows/onPush.yml) to your needs (e. g. turn on docker push to registry).

## Quick Start

Before you build and run the app make sure that following prerequisites are met:

1. JDK 13 is installed.

You can compile and run the application locally with
<pre><code>gradlew bootRun</code></pre>

## Testing
Two 'types' of tests are existing:
* test - for Unittests
    <pre><code>gradlew test</code></pre>
* testIntegration - for Integrationtests
    <pre><code>gradlew testIntegration</code></pre>

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
To generate the API (and model classes) the gradle task **generateApi** has to be executed.
This will generate the API Interface and the model classes.

    gradlew generateApi


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

#### 4. Exception Handling
Adapt and extend the classes `RestExceptionHandler` and `RestErrorCode` to your needs. 

## Build and deploy project
A full build including all unit and integration tests can be triggered by running 
<pre><code>gradlew build</code></pre>

## Semantic Versioning
This projects version is maintained by [semantic-release](https://github.com/semantic-release/semantic-release) tool.

Semantic-release automatically determines the version number. This requires formalized conventions for commit messages. It follows the [Angular JS Git Commit Guidelines](https://github.com/angular/angular.js/blob/master/DEVELOPERS.md#-git-commit-guidelines).

## Reference Documentation
For further reference, please consider the following sections:

* [Spring HATEOAS](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/htmlsingle/#boot-features-spring-hateoas)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/htmlsingle/#production-ready)

## Guides
The following guides illustrate how to use some features concretely:

* [Building a Hypermedia-Driven RESTful Web Service](https://spring.io/guides/gs/rest-hateoas/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

