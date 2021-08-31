package com.pesterenan.parkingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClienteAlreadyRegisteredException extends Exception{
	
	public ClienteAlreadyRegisteredException(String cpfCliente) {
		super(String.format("Cliente com o CPF %s já está cadastrado no sistema." , cpfCliente));
	}
}
