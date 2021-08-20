package com.pesterenan.parkingapi.dto;

import lombok.Builder;

@Builder
public class MessageResponseDTO {

	private String message;

	public String getMessage() {
		return message;
	}

	public MessageResponseDTO(String message) {
		this.message = message;
	}
}
