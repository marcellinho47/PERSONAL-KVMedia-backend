package br.com.kvmedia.asgestor.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "Operadores")
public class OperatorEntity extends DefaultInclusionExclusion implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_Operador")
	private Integer id;

	@Column(nullable = false, name = "FLAG_MasterAdmin")
	private Boolean flagMasterAdmin;

	@Email(message = "E-mail inválido")
	@NotNull(message = "Login é obrigatório")
	@Size(min = 5, max = 60, message = "Tamanho inválido")
	@Column(nullable = false, name = "DESC_Login")
	private String login;

	@NotNull(message = "Senha Obrigatória")
	@Size(min = 60, max = 65, message = "Tamanho inválido")
	@Column(nullable = false, name = "DESC_Senha")
	private String password;

	@OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "idOperador")
	private List<OperatorProfilesEntity> opProfiles;

	/* ====================================================================================== */
	/* CONSTRUCTOR */
	/* ====================================================================================== */
	public OperatorEntity() {
		super();
	}

	public OperatorEntity(String login, String password) {
		super();
		this.flagMasterAdmin = false;
		this.login = login;
		this.password = password;
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

	public Boolean getFlagMasterAdmin() {
		return flagMasterAdmin;
	}

	public void setFlagMasterAdmin(Boolean flagMasterAdmin) {
		this.flagMasterAdmin = flagMasterAdmin;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<OperatorProfilesEntity> getOpProfiles() {
		return opProfiles;
	}

	public void setOpProfiles(List<OperatorProfilesEntity> opProfiles) {
		this.opProfiles = opProfiles;
	}

	/* ====================================================================================== */
	/* HASHCODE AND EQUALS */
	/* ====================================================================================== */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flagMasterAdmin == null) ? 0 : flagMasterAdmin.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		OperatorEntity other = (OperatorEntity) obj;
		if (flagMasterAdmin == null) {
			if (other.flagMasterAdmin != null)
				return false;
		} else if (!flagMasterAdmin.equals(other.flagMasterAdmin))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	/* ====================================================================================== */
	/* MÉTODOS AUXILIARES */
	/* ====================================================================================== */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getAuthoritiesFromProfiles();
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return Boolean.TRUE;
	}

	@Override
	public boolean isAccountNonLocked() {
		return Boolean.TRUE;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return Boolean.TRUE;
	}

	@Override
	public boolean isEnabled() {
		return Boolean.TRUE;
	}

	private List<? extends GrantedAuthority> getAuthoritiesFromProfiles() {
		return this.opProfiles.stream().map(perfil -> new SimpleGrantedAuthority(perfil.getProfile().getDescription())).collect(Collectors.toList());
	}
}
