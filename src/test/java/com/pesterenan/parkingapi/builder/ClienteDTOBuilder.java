package com.pesterenan.parkingapi.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.pesterenan.parkingapi.dto.request.ClienteDTO;
import com.pesterenan.parkingapi.dto.request.TelefoneDTO;
import com.pesterenan.parkingapi.dto.request.VeiculoDTO;
import com.pesterenan.parkingapi.enums.TipoPlaca;
import com.pesterenan.parkingapi.enums.TipoTelefone;
import com.pesterenan.parkingapi.enums.TipoVeiculo;

import lombok.Builder;

@Builder
public class ClienteDTOBuilder {

	private static final Long ID = 1L;
	private static final String PHONE_NUMBER = "55199123456789";
	private static final TipoTelefone PHONE_TYPE = TipoTelefone.CELULAR;
	private static final TipoVeiculo VEHICLE_TYPE = TipoVeiculo.CARRO;
	private static final TipoPlaca LICENSE_PLATE_TYPE = TipoPlaca.NACIONAL;
	private static final String LICENSE_PLATE = "ABC1234";
	
	private static final Long CLIENTE_ID = 1L;
	private static final String CLIENTE_NAME = "NomeClienteTest";
	private static final String CLIENTE_CPF = "363.347.798-50";
	private static final List<TelefoneDTO> telefones = new ArrayList<>();
	private static final List<VeiculoDTO> veiculos = new ArrayList<>();

	
	
	public ClienteDTO toClienteDTO() {
		return ClienteDTO.builder()
				.id(CLIENTE_ID)
				.nome(CLIENTE_NAME)
				.cpf(CLIENTE_CPF)
				.telefones(Collections.singletonList(createFakeTelefoneDTO()))
				.veiculos(Collections.singletonList(createFakeVeiculoDTO()))
				.build();
	}
	
	public static TelefoneDTO createFakeTelefoneDTO() {
        return TelefoneDTO.builder()
                .id(ID)
                .numero(PHONE_NUMBER)
                .tipo(PHONE_TYPE)
                .build();
    }
	public static VeiculoDTO createFakeVeiculoDTO() {
        return VeiculoDTO.builder()
                .id(ID)
                .tipoVeiculo(VEHICLE_TYPE)
                .tipoPlaca(LICENSE_PLATE_TYPE)
                .placa(LICENSE_PLATE)
                .build();
    }
}
