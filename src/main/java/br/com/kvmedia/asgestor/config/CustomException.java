package br.com.kvmedia.asgestor.config;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/* ====================================================================================== */
	/* CONSTRUCTOR */
	/* ====================================================================================== */
	public CustomException(String msg) {
		super(msg);
	}

	public CustomException(String msg, Throwable cause) {
		super(cause);
	}
}
