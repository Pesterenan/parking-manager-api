package com.pesterenan.parkingapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pesterenan.parkingapi.model.Cliente;
import com.pesterenan.parkingapi.repository.ClienteRepository;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
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
