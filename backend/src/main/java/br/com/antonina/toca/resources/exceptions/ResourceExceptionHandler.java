package br.com.antonina.toca.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.antonina.toca.services.exceptions.DataBaseException;
import br.com.antonina.toca.services.exceptions.ResourceNotFoundExecption;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundExecption.class)
	public ResponseEntity<StandarError> entityNotFound(ResourceNotFoundExecption e, HttpServletRequest request) {
		StandarError err = new StandarError();
		
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

	}

	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandarError> dataBase(DataBaseException e, HttpServletRequest request) {
		StandarError err = new StandarError();
		HttpStatus status = HttpStatus.BAD_REQUEST;

		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("DataBase exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());

		return ResponseEntity.status(status.value()).body(err);
	}

}
