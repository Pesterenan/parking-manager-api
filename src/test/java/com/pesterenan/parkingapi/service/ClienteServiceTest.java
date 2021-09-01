package com.pesterenan.parkingapi.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import java.util.Collections;
import java.util.List;
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
import com.pesterenan.parkingapi.exception.ClienteNotFoundException;
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
		assertThrows(ClienteAlreadyRegisteredException.class, () -> clienteService.createCliente(expectedClienteDTO));

	}

	@Test
	void whenValidClienteCPFIsGivenThenReturnACliente() throws ClienteNotFoundException {
		// given
		ClienteDTO expectedFoundClienteDTO = ClienteDTOBuilder.builder().build().toClienteDTO();
		Cliente expectedFoundCliente = clienteMapper.toModel(expectedFoundClienteDTO);

		// when
		when(clienteRepository.findByCpf(expectedFoundCliente.getCpf())).thenReturn(Optional.of(expectedFoundCliente));

		// then
		ClienteDTO foundClienteDTO = clienteService.findByCpf(expectedFoundCliente.getCpf());

		assertThat(foundClienteDTO, is(equalTo(expectedFoundClienteDTO)));
	}

	@Test
	void whenNotValidClienteCPFIsGivenThenThrowAnException() {
		// given
		ClienteDTO expectedFoundClienteDTO = ClienteDTOBuilder.builder().build().toClienteDTO();

		// when
		when(clienteRepository.findByCpf(expectedFoundClienteDTO.getCpf())).thenReturn(Optional.empty());

		// then
		assertThrows(ClienteNotFoundException.class, () -> clienteService.findByCpf(expectedFoundClienteDTO.getCpf()));
	}

	@Test
	void whenListClientesIsCalledThenReturnAListOfClientes() {
		// given
		ClienteDTO expectedFoundClienteDTO = ClienteDTOBuilder.builder().build().toClienteDTO();
		Cliente expectedFoundCliente = clienteMapper.toModel(expectedFoundClienteDTO);
		// when
		when(clienteRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundCliente));

		// then
		List<ClienteDTO> foundListClienteDTO = clienteService.findAll();

		assertThat(foundListClienteDTO, is(not(empty())));
		assertThat(foundListClienteDTO.get(0), is(equalTo(expectedFoundClienteDTO)));
	}

	@Test
	void whenListClientesIsCalledThenReturnAnEmptyList() {
		// when
		when(clienteRepository.findAll()).thenReturn(Collections.emptyList());

		// then
		List<ClienteDTO> foundListClienteDTO = clienteService.findAll();

		assertThat(foundListClienteDTO, is(empty()));
	}

	@Test
	void whenExclusionIsCalledWithValidIdThenAClientShouldBeDeleted() throws ClienteNotFoundException {
		// given
		ClienteDTO expectedFoundClienteDTO = ClienteDTOBuilder.builder().build().toClienteDTO();
		Cliente expectedFoundCliente = clienteMapper.toModel(expectedFoundClienteDTO);
		
		
		// when
		when(clienteRepository.findById(expectedFoundCliente.getId())).thenReturn(Optional.of(expectedFoundCliente));
		doNothing().when(clienteRepository).deleteById(expectedFoundClienteDTO.getId());
	
		//then
		clienteService.delete(expectedFoundClienteDTO.getId());
		
		verify(clienteRepository, times(1)).findById(expectedFoundClienteDTO.getId());
		verify(clienteRepository, times(1)).deleteById(expectedFoundClienteDTO.getId());
	}
}