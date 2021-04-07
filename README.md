# Getting Started

## Create new project 
1. To create a new project, create a new github repository and select this project as repository template.
2. Create initial git tag "0.0.1"
3. Import the project in your favourite IDE.
4. Go to folder `/scripts/init-script` and run:
    <pre><code>npm install
   npm start</code></pre>
5. Delete folder `/scripts/init-script`
6. (Optional) Adapt the CI/CD workflow under[.github/workflows/onPush.yml](.github/workflows/onPush.yml) to your needs (e. g. turn on docker push to registry).

## Quick Start

Before you build and run the app make sure that following prerequisites are met:

1. JDK 15 is installed.
2. Gradle (>= 6.6.1) is installed

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

    gradlew openApiGenerate


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

## Code Structure
An important goal is to preserve long-term maintainability of the code. 
This can only be reached by a well-structured codebase. In concrete terms this means that the code must be structured 
in a modular way by avoiding cyclic dependencies and reducing unnecessary accessability to module-internal code. 
This leads to a Modular Monolith (or Modulith) from which modules [can be extracted into external services at any time with minimal effort](https://martinfowler.com/bliki/MonolithFirst.html).

To enforce a modular codebase, the [moduliths](https://github.com/odrotbohm/moduliths) library is used. 
The module model is explained [here](https://github.com/odrotbohm/moduliths#modules) and [here](https://github.com/odrotbohm/moduliths#modules.complex).

In the end it's about reducing accessibility of classes to a minimum, hiding internals of modules and preventing cyclic dependencies.  

## Logging
[Spring Sleuth](https://spring.io/projects/spring-cloud-sleuth) is included in this boilerplate to support trace ids which are added to all log messages that originate from a request. 
Further, trace ids are also forwarded automatically to other services called by Spring's RestTemplate that are also based on this boilerplate or include Spring Sleuth. This means that distributed tracing is supported out of the box.     

## Application Monitoring
A health-check endpoint is provided under `/actuator/health`
 
An endpoint with git and build info is provided under `/actuator/info` 

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

