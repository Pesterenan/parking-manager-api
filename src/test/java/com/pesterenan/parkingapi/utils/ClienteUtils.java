package com.pesterenan.parkingapi.utils;


import java.util.Collections;

import com.pesterenan.parkingapi.dto.request.ClienteDTO;
import com.pesterenan.parkingapi.entity.Cliente;

public class ClienteUtils {

	private static final long ID = 1L;
    private static final String NOME = "Renan Torres";
    private static final String CPF = "369.333.878-79";
   
    public static ClienteDTO createFakeDTO() {
        return ClienteDTO.builder()
        		.nome(NOME)
                .cpf(CPF)
                .telefones(Collections.singletonList(TelefoneUtils.createFakeDTO()))
                .build();
    }

    public static Cliente createFakeEntity() {
        return Cliente.builder()
                .id(ID)
                .nome(NOME)
                .cpf(CPF)
                .telefones(Collections.singletonList(TelefoneUtils.createFakeEntity()))
                .build();
    }
}
