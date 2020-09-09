package br.com.kvmedia.asgestor.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DefaultInclusion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "DATA_Inclusao", nullable = false)
	private LocalDateTime inclusionDate;

	@Column(name = "ID_OperadorInclusao", nullable = false)
	private Integer operatorInclusion;

	/* ====================================================================================== */
	/* GETTERS AND SETTERS */
	/* ====================================================================================== */
	public LocalDateTime getInclusionDate() {
		return inclusionDate;
	}

	public void setInclusionDate(LocalDateTime inclusionDate) {
		this.inclusionDate = inclusionDate;
	}

	public Integer getOperatorInclusion() {
		return operatorInclusion;
	}

	public void setOperatorInclusion(Integer operatorInclusion) {
		this.operatorInclusion = operatorInclusion;
	}
}
