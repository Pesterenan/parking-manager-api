package com.pesterenan.parkingapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pesterenan.parkingapi.dto.MessageResponseDTO;
import com.pesterenan.parkingapi.model.Client;
import com.pesterenan.parkingapi.repository.ClientRepository;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

	private ClientRepository clientRepository;

	@PostMapping
	public MessageResponseDTO createClient(@RequestBody Client client) {
		Client savedClient = clientRepository.save(client);
		return new MessageResponseDTO("Cliente criado com ID: " + savedClient.getId());

	}

	@GetMapping
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

}
