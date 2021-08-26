package com.pesterenan.parkingapi.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pesterenan.parkingapi.dto.MessageResponseDTO;
import com.pesterenan.parkingapi.dto.request.VeiculoDTO;
import com.pesterenan.parkingapi.entity.Veiculo;
import com.pesterenan.parkingapi.enums.TipoPlaca;
import com.pesterenan.parkingapi.exception.VeiculoNotFoundException;
import com.pesterenan.parkingapi.mapper.VeiculoMapper;
import com.pesterenan.parkingapi.repository.VeiculoRepository;

@Service
public class VeiculoService implements IVeiculoService {

	private VeiculoRepository veiculoRepository;

	private final VeiculoMapper veiculoMapper = VeiculoMapper.INSTANCE;

	@Autowired
	public VeiculoService(VeiculoRepository veiculoRepository) {
		this.veiculoRepository = veiculoRepository;
	}

	@Override
	public MessageResponseDTO createVeiculo(VeiculoDTO veiculoDTO) {
		Veiculo veiculoToSave = veiculoMapper.toModel(veiculoDTO);
		// Escolher Tipo de Placa de acordo com a numeração
		TipoPlaca tipoPlaca = Pattern.matches(TipoPlaca.NACIONAL.getTipoPlaca(), veiculoToSave.getPlaca())
				? TipoPlaca.NACIONAL
				: TipoPlaca.MERCOSUL;
		veiculoToSave.setTipoPlaca(tipoPlaca);
		System.out.println(veiculoToSave.getPlaca());
		Veiculo savedVeiculo = veiculoRepository.save(veiculoToSave);
		return createMessageResponse(savedVeiculo.getPlaca(), "Veiculo cadastrado com a placa: ");
	}

	@Override
	public List<VeiculoDTO> findAll() {
		List<Veiculo> allVeiculos = veiculoRepository.findAll();
		return allVeiculos.stream().map(veiculoMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public VeiculoDTO findByPlaca(String placa) throws VeiculoNotFoundException {
		Veiculo veiculo = verifyIfExists(placa.toUpperCase());
		return veiculoMapper.toDTO(veiculo);
	}

	@Override
	public void delete(Long id) throws VeiculoNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public MessageResponseDTO updateById(Long id, VeiculoDTO veiculoDTO) throws VeiculoNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	// Verificar se a ID informada existe
	private Veiculo verifyIfExists(String procuraPlaca) throws VeiculoNotFoundException {
		return veiculoRepository.findByPlaca(procuraPlaca)
				.orElseThrow(() -> new VeiculoNotFoundException(procuraPlaca));
	}

	private MessageResponseDTO createMessageResponse(String placa, String message) {
		return MessageResponseDTO.builder().message(message + placa).build();
	}

}
