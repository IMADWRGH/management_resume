package IMADWRGH.Gestion_CV.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProfileNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseBody
    public ResponseEntity<ErrorBody> handleInvalidRequestException(InvalidRequestException ex) {
        ErrorBody body = new ErrorBody();
        body.setMsg(ex.getMessage());
        body.setDate(new Date());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
