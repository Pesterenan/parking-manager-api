package com.pesterenan.parkingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VeiculoNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public VeiculoNotFoundException(String placa) {
		super("Veiculo não encontrado com a placa "+ placa);
	}
	
	
}
