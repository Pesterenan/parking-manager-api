package com.pesterenan.parkingapi.enums;

public enum TipoPlaca {
	
	NACIONAL("[A-Z]{3}[0-9]{4}"), 
	MERCOSUL("[A-Z]{3}[0-9]{1}[A-Z]{1}[0-9]{2}");

	private final String tipoPlaca;

	private TipoPlaca(String tipo) {
		this.tipoPlaca = tipo;
	}

	public String getTipoPlaca() {
		return tipoPlaca;
	}
}
