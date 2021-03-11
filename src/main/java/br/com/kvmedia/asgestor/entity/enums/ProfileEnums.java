package br.com.kvmedia.asgestor.entity.enums;

/* TABLE = Perfis */
public enum ProfileEnums {

	ADMIN(1, "ROLE_ADMIN"), CLIENTE(2, "ROLE_CLIENT");

	private int id;
	private String description;

	/* ====================================================================================== */
	/* CONSTRUCTOR */
	/* ====================================================================================== */
	private ProfileEnums(int id, String description) {
		this.id = id;
		this.description = description;
	}

	/* ====================================================================================== */
	/* GETTERS */
	/* ====================================================================================== */
	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	/* ====================================================================================== */
	/* EXTRA METHODS */
	/* ====================================================================================== */
	public static ProfileEnums toEnum(Integer id) {

		if (id == null) {
			return null;
		}

		for (ProfileEnums tipo : ProfileEnums.values()) {

			if (id.equals(tipo.getId())) {

				return tipo;
			}
		}

		throw new IllegalArgumentException("ID: " + id + " inválido.");
	}
}
