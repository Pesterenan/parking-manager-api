package com.pesterenan.parkingapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pesterenan.parkingapi.dto.MessageResponseDTO;
import com.pesterenan.parkingapi.dto.request.VeiculoDTO;
import com.pesterenan.parkingapi.exception.ClienteNotFoundException;

@Service
public class VeiculoService implements IVeiculoService {

	@Override
	public MessageResponseDTO createVeiculo(VeiculoDTO veiculoDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VeiculoDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VeiculoDTO findByLicensePlate(Long id) throws ClienteNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) throws ClienteNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public MessageResponseDTO updateById(Long id, VeiculoDTO veiculoDTO) throws ClienteNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
