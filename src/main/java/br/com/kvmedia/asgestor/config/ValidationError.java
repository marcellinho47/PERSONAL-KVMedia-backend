package br.com.kvmedia.asgestor.config;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	List<FieldMessage> errors;

	/* ====================================================================================== */
	/* CONSTRUCTOR */
	/* ====================================================================================== */
	public ValidationError() {
		super();
		this.errors = new ArrayList<>();
	}

	public ValidationError(Integer status, String error, String message, String path) {
		super(status, error, message, path);
		this.errors = new ArrayList<>();
	}

	/* ====================================================================================== */
	/* GETTERS AND SETTERS */
	/* ====================================================================================== */
	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void setErrors(FieldMessage error) {
		this.errors.add(error);
	}
}
