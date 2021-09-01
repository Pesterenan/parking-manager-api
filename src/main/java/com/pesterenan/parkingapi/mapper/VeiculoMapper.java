package com.pesterenan.parkingapi.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.pesterenan.parkingapi.dto.request.VeiculoDTO;
import com.pesterenan.parkingapi.entity.Veiculo;

@Mapper
public interface VeiculoMapper{
	
	VeiculoMapper INSTANCE = Mappers.getMapper(VeiculoMapper.class);
	
	Veiculo toModel(VeiculoDTO veiculoDTO);
	
	VeiculoDTO toDTO(Veiculo veiculo);

}
