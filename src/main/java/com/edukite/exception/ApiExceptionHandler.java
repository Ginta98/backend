package com.edukite.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.edukite.helper.ResponseBuilder;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);
	
    /**
     * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
     *
     * @param ex      MissingServletRequestParameterException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
    	logger.error(ex.getMessage(), ex);
    	return buildResponseEntity(new ApiException(ErrorMessage.INVALID_PARAM));
    }


    /**
     * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is invalid as well.
     *
     * @param ex      HttpMediaTypeNotSupportedException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
    	logger.error(ex.getMessage(), ex);
    	return buildResponseEntity(new ApiException(ErrorMessage.UNSUPPORTED_MEDIA_TYPE));
    }

    /**
     * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
     *
     * @param ex      the MethodArgumentNotValidException that is thrown when @Valid validation fails
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        System.out.println("handleMethodArgumentNotValid");
    	logger.error(ex.getMessage(), ex);
        return buildResponseEntity(new ApiException(ErrorMessage.BAD_REQUEST));
    }

    /**
     * Handles javax.validation.ConstraintViolationException. Thrown when @Validated fails.
     *
     * @param ex the ConstraintViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
            javax.validation.ConstraintViolationException ex) {
        System.out.println("handleConstraintViolation");

        logger.error(ex.getMessage(), ex);
    	return buildResponseEntity(new ApiException(ErrorMessage.CONTRAINS_EXCEPTION));
    }


    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
     *
     * @param ex      HttpMessageNotReadableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("handleHttpMessageNotReadable");

        logger.error(ex.getMessage(), ex);
    	return buildResponseEntity(new ApiException(ErrorMessage.BAD_REQUEST));
    }

    /**
     * Handle HttpMessageNotWritableException.
     *
     * @param ex      HttpMessageNotWritableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	logger.error(ex.getMessage(), ex);
        System.out.println("handleHttpMessageNotWritable");

        return buildResponseEntity(new ApiException(ErrorMessage.BAD_REQUEST));
    }

    /**
     * Handle NoHandlerFoundException.
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	logger.error(ex.getMessage(), ex);
        System.out.println("handleNoHandlerFoundException");

        return buildResponseEntity(new ApiException(ErrorMessage.NOT_FOUND));
    }

    /**
     * Handle DataIntegrityViolationException, inspects the cause for different DB causes.
     *
     * @param ex the DataIntegrityViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
                                                                  WebRequest request) {
    	logger.error(ex.getMessage(), ex);
        System.out.println("handleConstraintViolation");

        return buildResponseEntity(new ApiException(ErrorMessage.CONTRAINS_EXCEPTION));
    }

    /**
     * Handle DataIntegrityViolationException, inspects the cause for different DB causes.
     *
     * @param ex the DataIntegrityViolationException
     * @return the ApiError object
     */
	@ExceptionHandler(ApiException.class)
	protected ResponseEntity<Object> handleInvalidInputRequest(ApiException ex, WebRequest request) {
		logger.error(ex.getMessage(), ex);

		return buildResponseEntity(ex);
	}

    @ExceptionHandler(IOException.class)
    protected ResponseEntity<Object> handleInvalidInputRequest(IOException ex,
                                                               WebRequest request) {
        logger.error(ex.getMessage(), ex);
        System.out.println("handleInvalidInputRequest IOException");
        return buildResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
//        return buildResponseEntity(new TMSException(ErrorMessage.BAD_REQUEST));
    }

    /**
     * Handle Exception, handle generic Exception.class
     *
     * @param ex the Exception
     * @return the ApiError object
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                      WebRequest request) {
        System.out.println("handleMethodArgumentTypeMismatch");

        logger.error(ex.getMessage(), ex);
    	return buildResponseEntity(new ApiException(ErrorMessage.BAD_REQUEST));
    }

    /**
     * Handles EntityNotFoundException. Created to encapsulate errors with more detail than javax.persistence.EntityNotFoundException.
     *
     * @param ex the EntityNotFoundException
     * @return the ApiError object
     */
//    @ExceptionHandler(ApiException.class)
//    protected ResponseEntity<Object> handleEntityNotFound(ApiException ex) {
//       // System.out.println("handleEntityNotFound");
//
//        logger.error(ex.getMessage(), ex);
//    	return buildResponseEntity(ex);
//    }
    
   private ResponseEntity<Object> buildResponseEntity(String message, HttpStatus status) {
        return new ResponseEntity<>(ResponseBuilder.buildApplicationException(message, status.value()), status);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiException tmsEx) {
        return new ResponseEntity<>(ResponseBuilder.buildApplicationException(tmsEx), HttpStatus.OK);
    }

}