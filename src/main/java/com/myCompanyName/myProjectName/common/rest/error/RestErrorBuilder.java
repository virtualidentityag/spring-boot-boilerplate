package com.myCompanyName.myProjectName.common.rest.error;

import com.myCompanyName.myProjectName.generated.model.Error;
import java.util.UUID;

public class RestErrorBuilder {

  public Error buildError(Exception e, RestErrorCode errorCode) {
    return new Error()
        .id(UUID.randomUUID().toString())
        .code(errorCode.getErrorCode())
        .message(e.getMessage());
  }

}
