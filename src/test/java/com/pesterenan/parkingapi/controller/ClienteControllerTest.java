package com.pesterenan.parkingapi.controller;

import static com.pesterenan.parkingapi.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.pesterenan.parkingapi.service.ClienteService;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

	private static final String CLIENTE_API_URL_PATH = "/api/v1/clientes";
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
		mockMvc.perform(get(CLIENTE_API_URL_PATH + "/buscaCpf/" + clienteDTO.getCpf()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.nome", is(clienteDTO.getNome())))
				.andExpect(jsonPath("$.cpf", is(clienteDTO.getCpf())));
	}
}
