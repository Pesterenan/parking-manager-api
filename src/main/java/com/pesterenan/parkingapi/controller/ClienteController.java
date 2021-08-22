package com.pesterenan.parkingapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	// Mapeamento para criar clientes no banco de dados
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO createCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
		return clienteService.createCliente(clienteDTO);
	}

	// Mapeamento para buscar todos os clientes
	@GetMapping
	public List<ClienteDTO> getAllClientes() {
		return clienteService.findAll();
	}

	// Buscar Cliente por ID, retorna "Cliente não encontrado" caso não exista.
	@GetMapping("/{id}")
	public ClienteDTO getClienteById(@PathVariable Long id) throws ClienteNotFoundException {
		return clienteService.findById(id);
	}
	
	// Mapeamento para apagar Clientes do banco.
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) throws ClienteNotFoundException {
		clienteService.delete(id);
	}
}
