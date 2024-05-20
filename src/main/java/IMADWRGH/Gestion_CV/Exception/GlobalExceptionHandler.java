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
    public ResponseEntity<String> profileNotFoundException(ProfileNotFoundException msg) {
        return new ResponseEntity<>(msg.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseBody
    public ResponseEntity<String> invalidRequestException(InvalidRequestException msg) {
        return new ResponseEntity<>(msg.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResumeNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> resumeNotFoundException(ResumeNotFoundException msg){
        return new ResponseEntity<>(msg.getMessage(),HttpStatus.NOT_FOUND);
    }

}
