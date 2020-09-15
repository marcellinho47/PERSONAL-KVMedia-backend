package br.com.kvmedia.asgestor.config;

import java.io.Serializable;

public class FieldMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fieldError;
	private String error;

	/* ====================================================================================== */
	/* CONSTRUCTOR */
	/* ====================================================================================== */
	public FieldMessage() {
		super();
	}

	public FieldMessage(String fieldError, String error) {
		super();
		this.fieldError = fieldError;
		this.error = error;
	}

	/* ====================================================================================== */
	/* GETTERS AND SETTERS */
	/* ====================================================================================== */
	public String getFieldError() {
		return fieldError;
	}

	public void setFieldError(String fieldError) {
		this.fieldError = fieldError;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
