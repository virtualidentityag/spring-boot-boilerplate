package com.myCompanyName.myProjectName;

import static org.hamcrest.MatcherAssert.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.springframework.test.web.servlet.ResultMatcher;

public class ApiMatchers {

  public static <T extends Object> ResultMatcher responseMatchesModel(Class<T> modelClass) {
    return result ->
        assertThat("Json response does not match structure of model class",
            result.getResponse().getContentAsString(), matchesStructure(modelClass));
  }

  private static <T extends Object> BaseMatcher<String> matchesStructure(
      final Class<T> modelClass) {
    return new BaseMatcher<String>() {

      @Override
      public void describeTo(Description description) {
        description
            .appendText("a json string that matches the structure of response model " + modelClass);
      }

      @Override
      public boolean matches(Object o) {
        try {
          Set<ConstraintViolation<T>> constraintViolations = getConstraintViolations((String) o);
          return constraintViolations == null || constraintViolations.isEmpty();
        } catch (Exception e) {
          return false;
        }
      }

      private Set<ConstraintViolation<T>> getConstraintViolations(String o) throws Exception {
        String jsonInString = o;
        ObjectMapper objectMapper = new ObjectMapper();

        T responseModel = objectMapper.readValue(jsonInString, modelClass);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(responseModel);
      }

      @Override
      public void describeMismatch(Object item, Description description) {
        try {
          Set<ConstraintViolation<T>> constraintViolations = getConstraintViolations((String) item);
          if (constraintViolations != null) {
            description.appendText("the json breaks the following constraints: \r\n");
            constraintViolations.stream().forEach(constraintViolation -> description.appendText(
                constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage()
                    + "\r\n"));
          }
        } catch (Exception e) {

        }
        description.appendText("json is ").appendValue(item);
      }
    };
  }
}
