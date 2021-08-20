package com.pesterenan.parkingapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

	
	@GetMapping
	public String getClient() {
		return "Cliente retornado!";
	}
	
	
}
