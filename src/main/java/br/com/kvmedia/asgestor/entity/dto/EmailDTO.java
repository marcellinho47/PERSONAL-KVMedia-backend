package br.com.kvmedia.asgestor.entity.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

public class EmailDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Email(message = "E-mail inválido.")
	@Length(min = 5, max = 60, message = "Tamanho inválido.")
	private String login;

	/* ====================================================================================== */
	/* GETTERS AND SETTERS */
	/* ====================================================================================== */
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
