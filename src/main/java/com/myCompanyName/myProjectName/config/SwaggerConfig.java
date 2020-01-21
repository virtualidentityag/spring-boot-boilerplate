package com.myCompanyName.myProjectName.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicate;
import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  private static final String MODEL_REF_ERROR = "Error";
  private String basePackage = "com.myCompanyName";

  @Bean
  public Docket api(TypeResolver typeResolver) {
    Predicate<RequestHandler> predicate = RequestHandlerSelectors
        .basePackage(basePackage);
    ResponseMessage responseMessage500 = new ResponseMessageBuilder()
        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message("An application error occured")
        .responseModel(new ModelRef(MODEL_REF_ERROR))
        .build();
    ResponseMessage responseMessage404 = new ResponseMessageBuilder()
        .code(HttpStatus.NOT_FOUND.value())
        .message("Resource not found")
        .responseModel(new ModelRef(MODEL_REF_ERROR))
        .build();
    ResponseMessage responseMessage400 = new ResponseMessageBuilder()
        .code(HttpStatus.BAD_REQUEST.value())
        .message("Bad request")
        .responseModel(new ModelRef(MODEL_REF_ERROR))
        .build();
    ResponseMessage responseMessage409 = new ResponseMessageBuilder()
        .code(HttpStatus.CONFLICT.value())
        .message(
            "Indicates that the request could not be processed because of conflict in the current state of the resource, such as an edit conflict between multiple simultaneous updates.")
        .responseModel(new ModelRef(MODEL_REF_ERROR))
        .build();
    ArrayList responseMessages = new ArrayList();
    responseMessages.add(responseMessage400);
    responseMessages.add(responseMessage404);
    responseMessages.add(responseMessage409);
    responseMessages.add(responseMessage500);
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(predicate)
        .build()
        .useDefaultResponseMessages(false)
        .globalResponseMessage(RequestMethod.GET,
            responseMessages)
        .additionalModels(typeResolver.resolve(Error.class))
        ;
  }
}
