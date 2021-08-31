package com.pesterenan.parkingapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pesterenan.parkingapi.dto.MessageResponseDTO;
import com.pesterenan.parkingapi.dto.request.ClienteDTO;
import com.pesterenan.parkingapi.entity.Cliente;
import com.pesterenan.parkingapi.exception.ClienteAlreadyRegisteredException;
import com.pesterenan.parkingapi.exception.ClienteNotFoundException;
import com.pesterenan.parkingapi.mapper.ClienteMapper;
import com.pesterenan.parkingapi.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteService implements IClienteService {

	private ClienteRepository clienteRepository;

	private final ClienteMapper clienteMapper = ClienteMapper.INSTANCE;

	// Criar Cliente
	public ClienteDTO createCliente(ClienteDTO clienteDTO) throws ClienteAlreadyRegisteredException {
		verifyIfIsAlreadyRegistered(clienteDTO.getCpf());
		Cliente clienteToSave = clienteMapper.toModel(clienteDTO);
		Cliente savedCliente = clienteRepository.save(clienteToSave);
		createMessageResponse(savedCliente.getId(), "Cliente criado com ID: ");
		return clienteMapper.toDTO(savedCliente);
	}

	private void verifyIfIsAlreadyRegistered(String cpf) throws ClienteAlreadyRegisteredException {
		Optional<Cliente> optSavedCliente = clienteRepository.findByCpf(cpf);
		if (optSavedCliente.isPresent()) {
			throw new ClienteAlreadyRegisteredException(cpf);
		}

	}

	// Listar todos os Clientes
	public List<ClienteDTO> findAll() {
		List<Cliente> allClientes = clienteRepository.findAll();
		return allClientes.stream().map(clienteMapper::toDTO).collect(Collectors.toList());
	}

	// Encontrar ID do cliente
	public ClienteDTO findById(Long id) throws ClienteNotFoundException {
		Cliente cliente = verifyIfExists(id);
		return clienteMapper.toDTO(cliente);
	}

	public ClienteDTO findByCpf(String cpf) throws ClienteAlreadyRegisteredException {
		Cliente cliente = verifyIfExists(cpf);
		return clienteMapper.toDTO(cliente);
	}

	// Apagar cliente do banco
	public void delete(Long id) throws ClienteNotFoundException {
		verifyIfExists(id);
		clienteRepository.deleteById(id);
	}

	public MessageResponseDTO updateById(Long id, ClienteDTO clienteDTO) throws ClienteNotFoundException {
		verifyIfExists(id);
		Cliente clienteToUpdate = clienteMapper.toModel(clienteDTO);
		Cliente updatedCliente = clienteRepository.save(clienteToUpdate);
		return createMessageResponse(updatedCliente.getId(), "Cliente atualizado com ID: ");

	}

	// Verificar se a ID informada existe
	private Cliente verifyIfExists(Long id) throws ClienteNotFoundException {
		return clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
	}

	// Verificar se o CPF informado existe
	private Cliente verifyIfExists(String cpf) throws ClienteAlreadyRegisteredException {
		return clienteRepository.findByCpf(cpf).orElseThrow(() -> new ClienteAlreadyRegisteredException(cpf));
	}

	private MessageResponseDTO createMessageResponse(Long id, String message) {
		return MessageResponseDTO.builder().message(message + id).build();
	}

}
