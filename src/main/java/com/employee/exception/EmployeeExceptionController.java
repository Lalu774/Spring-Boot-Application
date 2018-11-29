package com.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionController {

	@ExceptionHandler(value = EmployeeNotFoundException.class)
	public ResponseEntity<String> exception(EmployeeNotFoundException exception){
		return new ResponseEntity<String>("Employee not found", HttpStatus.NOT_FOUND);
	}
}
