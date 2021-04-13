package com.myCompanyName.myProjectName.common.rest.error;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myCompanyName.myProjectName.generated.model.Error;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestExceptionHandlerTest {

    @InjectMocks
    private RestExceptionHandler restExceptionHandler;

    @Mock
    private RestErrorBuilder restErrorBuilder;

    @Mock
    private RestErrorLogger restErrorLogger;

    @BeforeEach
    void setup() {
        when(restErrorBuilder.buildError(any(), any())).thenReturn(mock(Error.class));
    }

    @Test
    void WHEN_exception_THEN_restErrorBuilder_is_called() {
        // Arrange
        Exception e = mock(Exception.class);

        // Act
        restExceptionHandler.exception(e);

        // Assert
        verify(restErrorBuilder).buildError(eq(e), any());
    }

    @Test
    void WHEN_exception_THEN_restErrorLogger_is_called() {
        // Arrange
        Exception e = mock(Exception.class);

        // Act
        restExceptionHandler.exception(e);

        // Assert
        verify(restErrorLogger).log(eq(e), any());
    }
}
