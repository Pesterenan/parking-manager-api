package com.pesterenan.parkingapi.controller;

import static com.pesterenan.parkingapi.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.pesterenan.parkingapi.builder.ClienteDTOBuilder;
import com.pesterenan.parkingapi.dto.request.ClienteDTO;
import com.pesterenan.parkingapi.exception.ClienteNotFoundException;
import com.pesterenan.parkingapi.service.ClienteService;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

	private static final String SEARCH_BY_CPF = "/buscaCpf/";
	private static final String CLIENTE_API_URL_PATH = "/api/v1/clientes";
	private static final Long INVALID_CLIENTE_ID = 5L;
	private MockMvc mockMvc;

	@Mock
	private ClienteService clienteService;

	@InjectMocks
	private ClienteController clienteController;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(clienteController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.setViewResolvers((s, locale) -> new MappingJackson2JsonView()).build();
	}

	@Test
	void whenPOSTIsCalledThenAClienteIsCreated() throws Exception {
		// given
		ClienteDTO clienteDTO = ClienteDTOBuilder.builder().build().toClienteDTO();

		// when
		when(clienteService.createCliente(clienteDTO)).thenReturn(clienteDTO);

		// then
		mockMvc.perform(
				post(CLIENTE_API_URL_PATH).contentType(MediaType.APPLICATION_JSON).content(asJsonString(clienteDTO)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.nome", is(clienteDTO.getNome())))
				.andExpect(jsonPath("$.cpf", is(clienteDTO.getCpf())));
	}

	@Test
	void whenPOSTIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception {
		// given
		ClienteDTO clienteDTO = ClienteDTOBuilder.builder().build().toClienteDTO();
		clienteDTO.setCpf(null);

		// then
		mockMvc.perform(
				post(CLIENTE_API_URL_PATH).contentType(MediaType.APPLICATION_JSON).content(asJsonString(clienteDTO)))
				.andExpect(status().isBadRequest());
	}

	@Test
	void whenGETIsCalledWithValidCPFThenOkStatusIsReturned() throws Exception {
		// given
		ClienteDTO clienteDTO = ClienteDTOBuilder.builder().build().toClienteDTO();

		// when
		when(clienteService.findByCpf(clienteDTO.getCpf())).thenReturn(clienteDTO);

		// then
		mockMvc.perform(
				get(CLIENTE_API_URL_PATH + SEARCH_BY_CPF + clienteDTO.getCpf()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.nome", is(clienteDTO.getNome())))
				.andExpect(jsonPath("$.cpf", is(clienteDTO.getCpf())));
	}

	@Test
	void whenGETIsCalledWithoutRegistratedCPFThenNotFoundStatusIsReturned() throws Exception {
		// given
		ClienteDTO clienteDTO = ClienteDTOBuilder.builder().build().toClienteDTO();

		// when
		when(clienteService.findByCpf(clienteDTO.getCpf())).thenThrow(ClienteNotFoundException.class);

		// then
		mockMvc.perform(
				get(CLIENTE_API_URL_PATH + SEARCH_BY_CPF + clienteDTO.getCpf()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void whenGETListWithClientesIsCalledThenOkStatusIsReturned() throws Exception {
		// given
		ClienteDTO clienteDTO = ClienteDTOBuilder.builder().build().toClienteDTO();

		// when
		when(clienteService.findAll()).thenReturn(Collections.singletonList(clienteDTO));

		// then
		mockMvc.perform(get(CLIENTE_API_URL_PATH).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0]nome", is(clienteDTO.getNome())))
				.andExpect(jsonPath("$.[0]cpf", is(clienteDTO.getCpf())));
	}

	@Test
	void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
		// given
		ClienteDTO clienteDTO = ClienteDTOBuilder.builder().build().toClienteDTO();

		// when
		doNothing().when(clienteService).delete(clienteDTO.getId());

		// then
		mockMvc.perform(delete(CLIENTE_API_URL_PATH + "/" + clienteDTO.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	void whenDELETEIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
		// given
		ClienteDTO clienteDTO = ClienteDTOBuilder.builder().build().toClienteDTO();

		// when
		doThrow(ClienteNotFoundException.class).when(clienteService).delete(INVALID_CLIENTE_ID);

		// then
		mockMvc.perform(delete(CLIENTE_API_URL_PATH + "/" + INVALID_CLIENTE_ID).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

}
