package com.myCompanyName.myProjectName.common.rest.error;

import com.myCompanyName.myProjectName.generated.model.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestErrorLogger {

  private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
  private static final String HANDLED_ERROR = "Error occured: {} - {}";

  public void log(Exception e, Error error) {
    logger.error(HANDLED_ERROR, error.getId(), error.getMessage(), e);
  }

}
