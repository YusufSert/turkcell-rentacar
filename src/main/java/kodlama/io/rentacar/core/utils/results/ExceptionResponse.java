package kodlama.io.rentacar.core.utils.results;

import jakarta.persistence.criteria.CriteriaBuilder;
import kodlama.io.rentacar.core.exceptions.BusinessException;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ExceptionResponse<T extends Exception> {
    private LocalDateTime timestamp;
    private String type;
    private String message;

    public ExceptionResponse(T exception) {
        this.timestamp = LocalDateTime.now();
        this.type = convertToUpperCaseWithUnderscores(exception.getClass().getSimpleName());
        this.message = exception.getMessage();
    }

    private String convertToUpperCaseWithUnderscores(String camelCaseString) {
        return camelCaseString.replaceAll("(.)(\\p{Upper})", "$1_$2").toUpperCase();
    }
}
