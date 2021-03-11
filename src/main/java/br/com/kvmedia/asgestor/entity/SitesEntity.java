package br.com.kvmedia.asgestor.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "SiteFormularios")
public class SitesEntity extends DefaultInclusion {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_SiteFormulario")
	private Integer id;

	@NotNull(message = "Site de Origem é obrigatório")
	@Size(min = 5, max = 60)
	@Column(nullable = false, name = "DESC_Site")
	private String site;

	@NotNull(message = "Nome é obrigatório")
	@Size(min = 2, max = 60)
	@Column(nullable = false, name = "DESC_Nome")
	private String name;

	@Email
	@Size(max = 100)
	@Column(name = "DESC_Email")
	private String email;

	@Size(max = 2)
	@Column(name = "DESC_DDDTelefone")
	private String dddTelefone;

	@Size(max = 10)
	@Column(name = "DESC_Telefone")
	private String telefone;

	@Size(max = 1000)
	@NotNull(message = "Mensagem é obrigatória")
	@Column(nullable = false, name = "DESC_Mensagem")
	private String message;

	@NotNull(message = "Data de Envio é obrigatória")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@Column(nullable = false, name = "DATA_Envio")
	private LocalDateTime sendDate;

	/* ====================================================================================== */
	/* CONSTRUCTOR */
	/* ====================================================================================== */
	public SitesEntity() {
		super();
		this.setInclusionDate(LocalDateTime.now());
	}

	public SitesEntity(String name, String email, String message, LocalDateTime sendDate) {
		super();
		this.name = name;
		this.email = email;
		this.message = message;
		this.sendDate = sendDate;
		this.setInclusionDate(LocalDateTime.now());
	}

	/* ====================================================================================== */
	/* GETTERS AND SETTERS */
	/* ====================================================================================== */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public String getDddTelefone() {
		return dddTelefone;
	}

	public void setDddTelefone(String dddTelefone) {
		this.dddTelefone = dddTelefone;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getSendDate() {
		return sendDate;
	}

	public void setSendDate(LocalDateTime sendDate) {
		this.sendDate = sendDate;
	}

	/* ====================================================================================== */
	/* HASH CODE AND EQUALS */
	/* ====================================================================================== */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sendDate == null) ? 0 : sendDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SitesEntity other = (SitesEntity) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sendDate == null) {
			if (other.sendDate != null)
				return false;
		} else if (!sendDate.equals(other.sendDate))
			return false;
		return true;
	}
}
