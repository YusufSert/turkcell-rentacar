package kodlama.io.rentacar.core.configuration.exceptions;

import kodlama.io.rentacar.core.exceptions.BusinessException;
import kodlama.io.rentacar.core.utils.results.ExceptionResponse;
import kodlama.io.rentacar.entities.Car;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) // 422
    public ExceptionResponse<BusinessException> handleBusinessException(BusinessException exception) {
        return new ExceptionResponse<>(exception);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ExceptionResponse<RuntimeException> handleRuntimeException(RuntimeException exception) {
        return new ExceptionResponse<RuntimeException>(exception);
    }
}
