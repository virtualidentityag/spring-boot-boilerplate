package com.myCompanyName.myProjectName.common.rest.error;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.TraceContext;
import org.springframework.cloud.sleuth.Tracer;

import com.myCompanyName.myProjectName.generated.model.Error;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestErrorBuilderTest {

    private static final String ANY_TRACE_ID = "traceId";
    private static final String ANY_EXCEPTION_MESSAGE = "exceptionMessage";
    private static final RestErrorCode ANY_ERROR_CODE = RestErrorCode.SERVER_ERROR;

    @InjectMocks
    private RestErrorBuilder restErrorBuilder;

    @Mock
    private Tracer tracer;

    @Test
    void GIVEN_no_trace_context_WHEN_buildError_THEN_error_id_equals_UUID() {
        // Arrange
        Exception e = mock(Exception.class);
        when(tracer.currentSpan()).thenReturn(null);

        // Act
        Error error = restErrorBuilder.buildError(e, ANY_ERROR_CODE);
        Executable action = () -> UUID.fromString(error.getId());

        // Assert
        assertDoesNotThrow(action);
    }

    @Test
    void WHEN_buildError_THEN_current_traceId_is_loaded() {
        // Arrange
        mockTraceId();
        Exception e = mock(Exception.class);

        // Act
        restErrorBuilder.buildError(e, ANY_ERROR_CODE);

        // Assert
        verify(tracer.currentSpan().context()).traceId();
    }

    @Test
    void WHEN_buildError_THEN_error_id_equals_current_traceId() {
        // Arrange
        mockTraceId();
        Exception e = mock(Exception.class);

        // Act
        Error error = restErrorBuilder.buildError(e, ANY_ERROR_CODE);

        // Assert
        assertThat(error.getId()).isEqualTo(ANY_TRACE_ID);
    }

    @Test
    void GIVEN_restError_WHEN_buildError_THEN_error_code_equals_restErrorCode() {
        // Arrange
        Exception e = mock(Exception.class);

        // Act
        Error error = restErrorBuilder.buildError(e, ANY_ERROR_CODE);

        // Assert
        assertThat(error.getCode()).isEqualTo(ANY_ERROR_CODE.getErrorCode());
    }

    @Test
    void GIVEN_exception_WHEN_buildError_THEN_error_message_equals_exceptionMessage() {
        // Arrange
        Exception e = mock(Exception.class);
        when(e.getMessage()).thenReturn(ANY_EXCEPTION_MESSAGE);

        // Act
        Error error = restErrorBuilder.buildError(e, ANY_ERROR_CODE);

        // Assert
        assertThat(error.getMessage()).isEqualTo(ANY_EXCEPTION_MESSAGE);
    }

    private void mockTraceId() {
        Span span = mock(Span.class);
        when(tracer.currentSpan()).thenReturn(span);
        TraceContext traceContext = mock(TraceContext.class);
        when(traceContext.traceId()).thenReturn(ANY_TRACE_ID);
        when(span.context()).thenReturn(traceContext);
    }
}
