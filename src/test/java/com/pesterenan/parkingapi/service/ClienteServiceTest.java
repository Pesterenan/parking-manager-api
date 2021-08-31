package com.pesterenan.parkingapi.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pesterenan.parkingapi.builder.ClienteDTOBuilder;
import com.pesterenan.parkingapi.dto.request.ClienteDTO;
import com.pesterenan.parkingapi.entity.Cliente;
import com.pesterenan.parkingapi.exception.ClienteAlreadyRegisteredException;
import com.pesterenan.parkingapi.mapper.ClienteMapper;
import com.pesterenan.parkingapi.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

	@Mock
	private ClienteRepository clienteRepository;

	@InjectMocks
	private ClienteService clienteService;

	private ClienteMapper clienteMapper = ClienteMapper.INSTANCE;

	@Test
	void whenClienteIsInformedthenItShouldBeCreated() throws ClienteAlreadyRegisteredException {
		// given
		ClienteDTO expectedClienteDTO = ClienteDTOBuilder.builder().build().toClienteDTO();
		Cliente expectedSavedCliente = clienteMapper.toModel(expectedClienteDTO);

		// when
		when(clienteRepository.save(expectedSavedCliente)).thenReturn(expectedSavedCliente);

		// then
		ClienteDTO createdClienteDTO = clienteService.createCliente(expectedClienteDTO);

		assertThat(createdClienteDTO.getId(), is(equalTo(expectedClienteDTO.getId())));
		assertThat(createdClienteDTO.getNome(), is(equalTo(expectedClienteDTO.getNome())));
		assertThat(createdClienteDTO.getCpf(), is(equalTo(expectedClienteDTO.getCpf())));

	}

	@Test
	void whenAlreadyRegisteredClienteInformedThenAnExceptionShouldBeThrown() {
		// given
		ClienteDTO expectedClienteDTO = ClienteDTOBuilder.builder().build().toClienteDTO();
		Cliente duplicatedCliente = clienteMapper.toModel(expectedClienteDTO);

		// when
		when(clienteRepository.findByCpf(expectedClienteDTO.getCpf())).thenReturn(Optional.of(duplicatedCliente));
		
		// then
		assertThrows(ClienteAlreadyRegisteredException.class,() -> clienteService.createCliente(expectedClienteDTO));
		
	}





}