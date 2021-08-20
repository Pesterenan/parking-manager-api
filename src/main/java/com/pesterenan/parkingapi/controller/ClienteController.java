package com.pesterenan.parkingapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pesterenan.parkingapi.dto.MessageResponseDTO;
import com.pesterenan.parkingapi.enums.TipoTelefone;
import com.pesterenan.parkingapi.model.Cliente;
import com.pesterenan.parkingapi.service.ClienteService;

@RestController
@RequestMapping("/api/v1/clients")
public class ClienteController {

	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@RequestMapping("/add")
	public void adicionarCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome("Renan2");
		cliente.setCpf("123456789-00");
		cliente.addTelefone(TipoTelefone.CELULAR, "12345678");
		createClient(cliente);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO createClient(@RequestBody Cliente client) {
		Cliente savedClient = clienteService.save(client);
		return new MessageResponseDTO("Cliente criado com ID: " + savedClient.getId());

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
