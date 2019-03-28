package testsystem.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import testsystem.exception.*;
import testsystem.util.GenericResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public RestResponseEntityExceptionHandler() {
        super();
    }

    // 400
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {
        final BindingResult result = ex.getBindingResult();
        final GenericResponse bodyOfResponse = new GenericResponse(result.getAllErrors(), "Invalid" + result.getObjectName());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({NoSuchCategoryException.class})
    public ResponseEntity<Object> handleNoSuchCategory(final RuntimeException ex, final WebRequest request) {
        final GenericResponse bodyOfResponse = new GenericResponse(ex.getMessage(), "NoSuchCategory");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({NoSuchEmailTokenException.class})
    public ResponseEntity<Object> handleNoSuchEmailToken(final RuntimeException ex, final WebRequest request) {
        final GenericResponse bodyOfResponse = new GenericResponse(ex.getMessage(), "NoSuchEmailToken");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({EmailTokenIsExpiredException.class})
    public ResponseEntity<Object> handleEmailTokenIsExpired(final RuntimeException ex, final WebRequest request) {
        final GenericResponse bodyOfResponse = new GenericResponse(ex.getMessage(), "EmailTokenIsExpired");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    // 409
    @ExceptionHandler({ UserAlreadyExistsException.class })
    public ResponseEntity<Object> handleUserAlreadyExist(final RuntimeException ex, final WebRequest request) {
        final GenericResponse bodyOfResponse = new GenericResponse(ex.getMessage(), "UserAlreadyExists");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({ EmailAlreadyExistsException.class })
    public ResponseEntity<Object> handleEmailAlreadyExist(final RuntimeException ex, final WebRequest request) {
        final GenericResponse bodyOfResponse = new GenericResponse(ex.getMessage(), "EmailAlreadyExists");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({ CategoryAlreadyExistsException.class })
    public ResponseEntity<Object> handleCategoryAlreadyExist(final RuntimeException ex, final WebRequest request) {
        final GenericResponse bodyOfResponse = new GenericResponse(ex.getMessage(), "CategoryAlreadyExists");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    // 500
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        final GenericResponse bodyOfResponse = new GenericResponse(ex.getMessage(), "InternalError");
        return new ResponseEntity<Object>(bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}