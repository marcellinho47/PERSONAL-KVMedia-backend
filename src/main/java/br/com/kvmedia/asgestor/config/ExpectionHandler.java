package br.com.kvmedia.asgestor.config;

import javax.management.InvalidAttributeValueException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExpectionHandler extends ResponseEntityExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<Object> RuntimeException(RuntimeException e, HttpServletRequest request, WebRequest res) {

		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), "Erro de Processamento", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> Exception(Exception e, HttpServletRequest request, WebRequest res) {

		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), "Erro de Processamento", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	// Exceptions
	@org.springframework.web.bind.annotation.ExceptionHandler({ CustomException.class })
	public ResponseEntity<Object> CustomException(CustomException e, HttpServletRequest request, WebRequest res) {

		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), "Erro de Processamento", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	// Related to Authorization
	@org.springframework.web.bind.annotation.ExceptionHandler({ AuthorizationServiceException.class })
	public ResponseEntity<Object> AuthorizationServiceException(AuthorizationServiceException e, HttpServletRequest request, WebRequest res) {

		StandardError error = new StandardError(HttpStatus.FORBIDDEN.value(), "Acesso Negado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}

	// Not found objects
	@org.springframework.web.bind.annotation.ExceptionHandler({ InvalidAttributeValueException.class })
	public ResponseEntity<Object> InvalidAttributeValueException(InvalidAttributeValueException e, HttpServletRequest request, WebRequest res) {

		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), "Dados Inválidos", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	// Data Validation
	@org.springframework.web.bind.annotation.ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {

		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), "Integridade de Dados", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	// Data Validation
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ValidationError error = new ValidationError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", e.getMessage(), " ");

		for (FieldError erro : e.getBindingResult().getFieldErrors()) {

			error.setErrors(new FieldMessage(erro.getField(), erro.getDefaultMessage()));
		}

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
	}
}
