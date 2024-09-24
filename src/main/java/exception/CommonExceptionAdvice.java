package exception;

import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {


    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(NoHandlerFoundException ex){
        return "/resources/index.html";
    }
}
