package com.capgemini.tlta.exception;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * The Class GlobalExceptionHandler.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

//	/**
//	 * Resource not found handling.
//	 *
//	 * @param exception the exception
//	 * @param request the request
//	 * @return the response entity
//	 */
//	// handling specific exception
//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request){
//		ErrorDetails errorDetails = 
//				new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
//		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//	}
//
//	/**
//	 * Global exception handling.
//	 *
//	 * @param exception the exception
//	 * @param request the request
//	 * @return the response entity
//	 */
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
//		ErrorDetails errorDetails = 
//				new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
//		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	
//	/**
//	 * Custom validation error handling.
//	 *
//	 * @param exception the exception
//	 * @return the response entity
//	 */
//	//handling custome exception
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<?> customValidationErrorHandling(MethodArgumentNotValidException exception ){
//		ErrorDetails errorDetails = 
//				new ErrorDetails(new Date(), "validation error",exception.getBindingResult().getFieldError().getDefaultMessage());
//		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//	}
//	
	 // handling specific exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request){
        ErrorDetails errorDetails = 
                new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    
    // handling global exception
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
        ErrorDetails errorDetails = 
                new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    //handling custom exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> customValidationErrorHandling(MethodArgumentNotValidException exception ){
        ErrorDetails errorDetails = 
                new ErrorDetails(new Date(), "Validation error",exception.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AssesmentException.class)
    public ResponseEntity<Object> handleAssessmentExceptions( AssesmentException exception, WebRequest webRequest) {
         ErrorDetails errorDetails =
                    new ErrorDetails(new Date(), "Not found",exception.getMessage());
            ResponseEntity<Object> entity = new ResponseEntity<>(errorDetails ,HttpStatus.NOT_FOUND);
            return entity;
    }
    @ExceptionHandler(ActivityException.class)
    public ResponseEntity<Object> handleActivityExceptions( ActivityException exception, WebRequest webRequest) {
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), "Not found",exception.getMessage());
        ResponseEntity<Object> entity = new ResponseEntity<>(errorDetails ,HttpStatus.NOT_FOUND);
        return entity;
    }
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<Object> handleLoginExceptions( LoginException exception, WebRequest webRequest) {
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), "Not found",exception.getMessage());
        ResponseEntity<Object> entity = new ResponseEntity<>(errorDetails ,HttpStatus.NOT_FOUND);
        return entity;
    }
    @ExceptionHandler(RegisterUserException.class)
    public ResponseEntity<Object> handleRegisterUserExceptions( RegisterUserException exception, WebRequest webRequest) {
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), "Not found",exception.getMessage());
        ResponseEntity<Object> entity = new ResponseEntity<>(errorDetails ,HttpStatus.NOT_FOUND);
        return entity;
    }
	
}