package com.pesterenan.parkingapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pesterenan.parkingapi.dto.MessageResponseDTO;
import com.pesterenan.parkingapi.dto.request.ClienteDTO;
import com.pesterenan.parkingapi.entity.Cliente;
import com.pesterenan.parkingapi.exceptionqq.ClienteNotFoundException;
import com.pesterenan.parkingapi.service.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

	private ClienteService clienteService;

	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO createCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
		return clienteService.createCliente(clienteDTO);
	}

	@GetMapping
	public List<ClienteDTO> getAllClientes() {
		return clienteService.findAll();
	}

	@GetMapping("/{id}")
	public ClienteDTO getClienteById(@PathVariable Long id) throws ClienteNotFoundException {
		return clienteService.findById(id);
	}
}
