package com.pesterenan.parkingapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.pesterenan.parkingapi.dto.request.ClienteDTO;
import com.pesterenan.parkingapi.entity.Cliente;

@Mapper
public interface ClienteMapper {
	
	ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);
	
	Cliente toModel(ClienteDTO clienteDTO);
	
	ClienteDTO toDTO(Cliente cliente);

}
