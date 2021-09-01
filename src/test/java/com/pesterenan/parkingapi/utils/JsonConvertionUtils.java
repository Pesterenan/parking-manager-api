package com.pesterenan.parkingapi.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pesterenan.parkingapi.dto.request.ClienteDTO;

public class JsonConvertionUtils {

	public static String asJsonString(ClienteDTO clienteDTO) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return objectMapper.writeValueAsString(clienteDTO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
