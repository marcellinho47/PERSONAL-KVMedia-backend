package br.com.kvmedia.asgestor.config;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;

	private LocalDateTime timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

	/* ====================================================================================== */
	/* CONSTRUCTOR */
	/* ====================================================================================== */
	public StandardError() {
		super();
	}

	public StandardError(Integer status, String error, String message, String path) {
		super();
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	/* ====================================================================================== */
	/* GETTERS AND SETTERS */
	/* ====================================================================================== */
	public String getTimestamp() {
		return timestamp.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
