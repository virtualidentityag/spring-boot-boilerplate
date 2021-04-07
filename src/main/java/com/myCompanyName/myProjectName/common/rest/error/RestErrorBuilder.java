package com.myCompanyName.myProjectName.common.rest.error;

import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

import com.myCompanyName.myProjectName.generated.model.Error;

@Component
public class RestErrorBuilder {

  private Tracer tracer;

  public RestErrorBuilder(final Tracer tracer) {
    this.tracer = tracer;
  }

  public Error buildError(Exception e, RestErrorCode errorCode) {
    // get spring sleuth trace id
    String errorId = tracer.currentSpan().context().traceId();

    return new Error()
        .id(errorId)
        .code(errorCode.getErrorCode())
        .message(e.getMessage());
  }

}
