package br.com.kvmedia.asgestor.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DefaultInclusionExclusion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "DATA_Inclusao", nullable = false)
	private LocalDateTime inclusionDate;

	@Column(name = "ID_OperadorInclusao", nullable = false)
	private Integer operatorInclusion;

	@Column(name = "DATA_Exclusao", nullable = false)
	private LocalDateTime exclusionDate;

	@Column(name = "ID_OperadorExclusao", nullable = false)
	private Integer operatorExclusion;

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

	public LocalDateTime getExclusionDate() {
		return exclusionDate;
	}

	public void setExclusionDate(LocalDateTime exclusionDate) {
		this.exclusionDate = exclusionDate;
	}

	public Integer getOperatorExclusion() {
		return operatorExclusion;
	}

	public void setOperatorExclusion(Integer operatorExclusion) {
		this.operatorExclusion = operatorExclusion;
	}
}
