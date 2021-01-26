package com.myCompanyName.myProjectName.common.rest.error;

import static com.myCompanyName.myProjectName.common.rest.error.RestErrorCode.SERVER_ERROR;
import static com.myCompanyName.myProjectName.common.rest.error.RestErrorCode.VALIDATION_EXCEPTION;

import com.myCompanyName.myProjectName.generated.model.Error;
import javax.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler {

  private RestErrorBuilder restErrorBuilder = new RestErrorBuilder();
  private RestErrorLogger restErrorLogger = new RestErrorLogger();

  @ExceptionHandler({Exception.class})
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public Error exception(final Exception e) {
    Error error = restErrorBuilder.buildError(e, SERVER_ERROR);
    restErrorLogger.log(e, error);
    return error;
  }

  @ExceptionHandler({ValidationException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Error validationException(final Exception e) {
    Error error = restErrorBuilder.buildError(e, VALIDATION_EXCEPTION);
    restErrorLogger.log(e, error);
    return error;
  }

  // TODO add your own exception handlers

}
