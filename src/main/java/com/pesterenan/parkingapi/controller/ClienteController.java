package com.pesterenan.parkingapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pesterenan.parkingapi.dto.MessageResponseDTO;
import com.pesterenan.parkingapi.dto.request.ClienteDTO;
import com.pesterenan.parkingapi.exception.ClienteAlreadyRegisteredException;
import com.pesterenan.parkingapi.exception.ClienteNotFoundException;
import com.pesterenan.parkingapi.service.ClienteService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/clientes")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteController {

	private ClienteService clienteService;

	// Mapeamento para criar clientes no banco de dados
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDTO createCliente(@RequestBody @Valid ClienteDTO clienteDTO)
			throws ClienteAlreadyRegisteredException {
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

	// Buscar Cliente por CPF, retorna "Cliente não encontrado" caso não exista.
	@GetMapping("/buscaCpf/{cpf}")
	public ClienteDTO getClienteByCpf(@PathVariable String cpf) throws ClienteNotFoundException {
		return clienteService.findByCpf(cpf);
	}

	@PutMapping("/{id}")
	public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid ClienteDTO clienteDTO)
			throws ClienteNotFoundException {
		return clienteService.updateById(id, clienteDTO);

	}

	// Mapeamento para apagar Clientes do banco.
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) throws ClienteNotFoundException {
		clienteService.delete(id);
	}
}
