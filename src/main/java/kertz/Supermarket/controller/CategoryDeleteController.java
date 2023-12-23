package kertz.Supermarket.controller;

import kertz.Supermarket.exception.CategoryDeleteException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CategoryDeleteController {
    @ResponseBody
    @ExceptionHandler(CategoryDeleteException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String deleteErrorHandler(CategoryDeleteException exception){
        return exception.getMessage();
    }
}
