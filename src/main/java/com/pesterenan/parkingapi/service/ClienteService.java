package com.pesterenan.parkingapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.pesterenan.parkingapi.dto.MessageResponseDTO;
import com.pesterenan.parkingapi.dto.request.ClienteDTO;
import com.pesterenan.parkingapi.entity.Cliente;
import com.pesterenan.parkingapi.exceptionqq.ClienteNotFoundException;
import com.pesterenan.parkingapi.mapper.ClienteMapper;
import com.pesterenan.parkingapi.repository.ClienteRepository;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	private final ClienteMapper clienteMapper = ClienteMapper.INSTANCE;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public MessageResponseDTO createCliente(ClienteDTO clienteDTO) {
		Cliente clienteToSave = clienteMapper.toModel(clienteDTO);
		Cliente savedCliente = clienteRepository.save(clienteToSave);
		return MessageResponseDTO.builder().message("Cliente criado com ID: " + savedCliente.getId()).build();

	}

	public List<ClienteDTO> findAll() {
		List<Cliente> allClientes = clienteRepository.findAll();
		return allClientes.stream().map(clienteMapper::toDTO).collect(Collectors.toList());
	}

	public ClienteDTO findById(Long id) throws ClienteNotFoundException {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
		return clienteMapper.toDTO(cliente);
		
	}

}
