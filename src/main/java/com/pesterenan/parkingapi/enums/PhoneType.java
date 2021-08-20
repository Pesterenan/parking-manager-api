package com.pesterenan.parkingapi.enums;

public enum PhoneType {

	RESIDENTIAL("Residencial"), MOBILE("Celular"), COMMERCIAL("Comercial");

	private final String description;

	private PhoneType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
