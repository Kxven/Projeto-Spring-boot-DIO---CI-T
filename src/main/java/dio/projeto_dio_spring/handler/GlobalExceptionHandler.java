package dio.projeto_dio_spring.handler;

import jakarta.annotation.Resource;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.springframework.cglib.proxy.UndeclaredThrowableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    private HttpHeaders headers(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
    private ResponseError responseError( String message,HttpStatus statusCode){
        ResponseError responseError = new ResponseError();
        responseError.setStatus("error");
        responseError.setError(message);
        responseError.setStatusCode(statusCode.value());
        return responseError;
    }

    @ExceptionHandler({BusinessException.class})
    private ResponseEntity<Object> handleBusinessException(BusinessException e, WebRequest request) {
        ResponseError error = responseError(e.getMessage(),HttpStatus.CONFLICT);
        return handleExceptionInternal(e, error, headers(), HttpStatus.CONFLICT, request);
    }
}

