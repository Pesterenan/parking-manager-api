package com.pesterenan.parkingapi.service;

import static com.pesterenan.parkingapi.utils.ClienteUtils.createFakeDTO;
import static com.pesterenan.parkingapi.utils.ClienteUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pesterenan.parkingapi.dto.MessageResponseDTO;
import com.pesterenan.parkingapi.dto.request.ClienteDTO;
import com.pesterenan.parkingapi.entity.Cliente;
import com.pesterenan.parkingapi.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

	@Mock
	private ClienteRepository clienteRepository;

	@InjectMocks
	private ClienteService clienteService;

	@Test
	void testGivenClienteDTOThenReturnSavedMessage() {
		ClienteDTO clienteDTO = createFakeDTO();
		Cliente expectedSavedCliente = createFakeEntity();

		when(clienteRepository.save(any(Cliente.class))).thenReturn(expectedSavedCliente);

		MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavedCliente.getId(), "Cliente criado com ID: ");
		MessageResponseDTO successMessage = clienteService.createCliente(clienteDTO);

		assertEquals(expectedSuccessMessage, successMessage);
	}

	private MessageResponseDTO createExpectedMessageResponse(Long id, String message) {
		return MessageResponseDTO.builder().message(message + id).build();
	}
}
