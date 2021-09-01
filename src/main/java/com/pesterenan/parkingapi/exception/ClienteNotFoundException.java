package com.pesterenan.parkingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ClienteNotFoundException(Long id) {
		super("Cliente não encontrado com ID "+ id);
	}
	
	public ClienteNotFoundException(String cpf) {
		super("Cliente não encontrado com o CPF: " + cpf);
	}
	
}
