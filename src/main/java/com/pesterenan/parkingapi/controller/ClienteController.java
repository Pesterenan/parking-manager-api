package com.pesterenan.parkingapi.controller;

import java.util.List;
import java.util.Optional;

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
import com.pesterenan.parkingapi.entity.Cliente;
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
	public MessageResponseDTO createCliente(@RequestBody Cliente cliente) {
		return clienteService.createCliente(cliente);
	}

	@GetMapping
	public List<Cliente> getAllClientes() {
		return clienteService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Cliente> getClienteById(@PathVariable Long id) {
		return clienteService.findById(id);
	}
}
