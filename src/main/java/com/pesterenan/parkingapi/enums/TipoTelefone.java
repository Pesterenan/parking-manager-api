package com.pesterenan.parkingapi.enums;

public enum TipoTelefone {

	RESIDENCIAL("Residencial"), CELULAR("Celular"), COMERCIAL("Comercial");

	private final String descricao;

	private TipoTelefone(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
