package com.pesterenan.parkingapi.builder;

import java.util.List;

import com.pesterenan.parkingapi.dto.request.ClienteDTO;
import com.pesterenan.parkingapi.dto.request.TelefoneDTO;
import com.pesterenan.parkingapi.dto.request.VeiculoDTO;

import lombok.Builder;

@Builder
public class ClienteDTOBuilder {

	@Builder.Default
	private Long id = 1L;
	@Builder.Default
	private String nome = "NomeClienteTest";
	@Builder.Default
	private String cpf = "363.347.785-50";
	@Builder.Default
	private List<TelefoneDTO> telefones;

	@Builder.Default
	private List<VeiculoDTO> veiculos;

	
	
	public ClienteDTO toClienteDTO() {
		return new ClienteDTO(id, nome, cpf, telefones, veiculos);
	}
}
