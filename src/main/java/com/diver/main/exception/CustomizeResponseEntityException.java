package com.diver.main.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomizeResponseEntityException extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(UserAlredyExist.class)
	
	public final ResponseEntity<Object> handleAllException(UserAlredyExist ex, WebRequest request) {
		// TODO Auto-generated method stub
		ExceptionGeneric exG=new ExceptionGeneric(new Date(), ex.getMessage(), request.getDescription(false));
				return new ResponseEntity<>(exG, HttpStatus.CONFLICT);
	}
@ExceptionHandler(UserNotFoundException.class)
	
	public final ResponseEntity<Object> handleAllException(UserNotFoundException ex, WebRequest request) {
		// TODO Auto-generated method stub
		ExceptionGeneric exG=new ExceptionGeneric(new Date(), ex.getMessage(), request.getDescription(false));
				return new ResponseEntity<>(exG, HttpStatus.NOT_FOUND);
	}

@ExceptionHandler(PasswordAlredyResetException.class)

public final ResponseEntity<Object> handleAllException(PasswordAlredyResetException ex, WebRequest request) {
	// TODO Auto-generated method stub
	ExceptionGeneric exG=new ExceptionGeneric(new Date(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(exG, HttpStatus.CONFLICT);
}

@ExceptionHandler(EntityNoFoundException.class)

public final ResponseEntity<Object> handleAllException(EntityNoFoundException ex, WebRequest request) {
	// TODO Auto-generated method stub
	ExceptionGeneric exG=new ExceptionGeneric(new Date(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(exG, HttpStatus.NOT_FOUND);
}

}
