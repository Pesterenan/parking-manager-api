package com.pesterenan.parkingapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.pesterenan.parkingapi.dto.MessageResponseDTO;
import com.pesterenan.parkingapi.entity.Cliente;
import com.pesterenan.parkingapi.repository.ClienteRepository;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public MessageResponseDTO createCliente(Cliente client) {
		Cliente savedCliente = clienteRepository.save(client);
		return MessageResponseDTO.builder().message("Cliente criado com ID: " + savedCliente.getId()).build();

	}
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id);
	}

}
