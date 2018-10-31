package br.com.thiago.mongodb.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.thiago.mongodb.service.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> ObjectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;

		StandardError error = new StandardError(System.currentTimeMillis(), httpStatus.value(), "Não Encontrado!",
				e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(httpStatus).body(error);
	}
}
