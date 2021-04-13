package com.myCompanyName.myProjectName.common.rest.error;

import java.util.UUID;

import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

import com.myCompanyName.myProjectName.generated.model.Error;

import static java.util.Objects.nonNull;

@Component
public class RestErrorBuilder {

    private final Tracer tracer;

    public RestErrorBuilder(final Tracer tracer) {
        this.tracer = tracer;
    }

    public Error buildError(Exception e, RestErrorCode errorCode) {
        String errorId;
        // get spring sleuth trace id
        Span currentSpan = tracer.currentSpan();
        if (nonNull(currentSpan)) {
            errorId = currentSpan.context().traceId();
        } else {
            errorId = UUID.randomUUID().toString();
        }

        return new Error().id(errorId).code(errorCode.getErrorCode()).message(e.getMessage());
    }

}
