package com.pesterenan.parkingapi.utils;

import com.pesterenan.parkingapi.dto.request.TelefoneDTO;
import com.pesterenan.parkingapi.entity.Telefone;
import com.pesterenan.parkingapi.enums.TipoTelefone;

public class TelefoneUtils {

    private static final String NUMERO = "1199999-9999";
    private static final TipoTelefone TIPO = TipoTelefone.RESIDENCIAL;
    private static final long ID = 1L;

    public static TelefoneDTO createFakeDTO() {
        return TelefoneDTO.builder()
        		.numero(NUMERO)
                .tipo(TIPO)
                .build();
    }

    public static Telefone createFakeEntity() {
        return Telefone.builder()
                .id(ID)
                .numero(NUMERO)
                .tipo(TIPO)
                .build();
    }
}
