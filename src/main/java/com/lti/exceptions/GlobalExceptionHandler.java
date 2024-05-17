package com.lti.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lti.payloads.ErrorObject;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorObject> handleUserNotFoundException(ResourceNotFoundException e){
		
		ErrorObject err= new ErrorObject();
		
		err.setMessage(e.getMessage());
		err.setStatusCode(HttpStatus.NOT_FOUND.value());
		err.setTimeStamp(new Date());
		
		return new ResponseEntity<ErrorObject>(err,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ItemAlreadyExistsException.class)
	public ResponseEntity<ErrorObject> handleItemAlreadyExistsException(ItemAlreadyExistsException e){

		ErrorObject err= new ErrorObject();

		err.setMessage(e.getMessage());
		err.setStatusCode(HttpStatus.CONFLICT.value());
		err.setTimeStamp(new Date());

		return new ResponseEntity<ErrorObject>(err,HttpStatus.CONFLICT);
	}
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
//		
//		Map<String,String> resp = new HashMap<>();
//		ex.getBindingResult().getAllErrors().forEach((error)->{
//			String fieldName = ((FieldError)error).getField();
//			String message = error.getDefaultMessage();
//			resp.put(fieldName, message);
//		});
//		
//		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
//		
//	}
	
}
