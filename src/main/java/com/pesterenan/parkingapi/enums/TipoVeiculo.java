package com.pesterenan.parkingapi.enums;

public enum TipoVeiculo {

	CARRO("Carro"), MOTO("Moto"), SERVICO("Serviço");

	private final String descricao;

	private TipoVeiculo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
