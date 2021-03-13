package bol.com.interview.kahala.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvisor  {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ErrorResponse handleMissingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException e) {
        String message = "Required parameter " + e.getParameterName() + " is missing";
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getClass().getSimpleName(),
                message);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = NullPointerException.class)
    public ErrorResponse handleNullPointerException(HttpServletRequest request, NullPointerException e) {
        return new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getClass().getSimpleName(),
                e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ErrorResponse handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException ex) {
        Map<String, Collection<String>> errors = new LinkedHashMap<>();
        final String[] queryParam = {""};
        ex.getConstraintViolations().forEach(constraintViolation -> {
            String queryParamPath = constraintViolation.getPropertyPath().toString();
            queryParam[0] = queryParamPath.contains(".") ?
                    queryParamPath.substring(queryParamPath.indexOf(".") + 1) :
                    queryParamPath;
        });
        String message = "The " + queryParam[0] + " is wrong";
        return new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getClass().getSimpleName(),
                message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ErrorResponse handleMethodArgumentTypeMismatchException(HttpServletRequest request, MethodArgumentTypeMismatchException e) {
        String message = "Wrong parameter " + e.getName();
        return new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getClass().getSimpleName(),
                message);
    }
}
