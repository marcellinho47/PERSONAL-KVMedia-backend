package br.com.kvmedia.asgestor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.kvmedia.asgestor.entity.enums.ProfileEnums;

@Entity
@Table(name = "OperadoresPerfis")
public class OperatorProfilesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_OperadorPerfil")
	private Integer id;

	@Column(nullable = false, name = "ID_Operador")
	private Integer idOperador;

	@NotNull
	@Column(nullable = false, name = "ID_Perfil")
	private Integer profile;

	/* ====================================================================================== */
	/* CONSTRUCTOR */
	/* ====================================================================================== */
	public OperatorProfilesEntity() {
		super();
	}

	public OperatorProfilesEntity(Integer idOperador, Integer idProfile) {
		super();
		this.idOperador = idOperador;
		this.profile = idProfile;
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

	public Integer getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(Integer idOperador) {
		this.idOperador = idOperador;
	}

	public ProfileEnums getProfile() {
		return ProfileEnums.toEnum(this.profile);
	}

	public void setProfile(Integer idProfile) {
		this.profile = idProfile;
	}
}
