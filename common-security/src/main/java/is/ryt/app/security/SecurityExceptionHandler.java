package is.ryt.app.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import is.ryt.app.model.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SecurityExceptionHandler {
    @ExceptionHandler(JWTVerificationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse jwtVerificationException(JWTVerificationException e) {
        return new ErrorResponse("Bad Token");
    }
}
