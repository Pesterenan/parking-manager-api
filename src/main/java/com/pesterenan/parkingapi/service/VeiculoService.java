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
		validarPlaca(veiculoToSave);
		Veiculo savedVeiculo = veiculoRepository.save(veiculoToSave);
		return createMessageResponse(savedVeiculo.getPlaca(), "Veiculo cadastrado com a placa: ");
	}

	@Override
	public List<VeiculoDTO> findAll() {
		List<Veiculo> allVeiculos = veiculoRepository.findAll();
		return allVeiculos.stream().map(veiculoMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public VeiculoDTO findById(Long id) throws VeiculoNotFoundException {
		Veiculo veiculo = verifyIfExists(id);
		return veiculoMapper.toDTO(veiculo);
	}

	@Override
	public VeiculoDTO findByPlaca(String placa) throws VeiculoNotFoundException {
		Veiculo veiculo = verifyIfExists(placa.toUpperCase());
		return veiculoMapper.toDTO(veiculo);
	}

	@Override
	public void delete(Long id) throws VeiculoNotFoundException {
		verifyIfExists(id);
		veiculoRepository.deleteById(id);
	}

	@Override
	public MessageResponseDTO updateById(Long id, VeiculoDTO veiculoDTO) throws VeiculoNotFoundException {
		Veiculo veiculoToUpdate = verifyIfExists(id);
		veiculoToUpdate = veiculoMapper.toModel(veiculoDTO);
		validarPlaca(veiculoToUpdate);
		veiculoToUpdate.setId(id);
		Veiculo updatedVeiculo = veiculoRepository.save(veiculoToUpdate);
		return createMessageResponse(updatedVeiculo.getPlaca(), "Veiculo atualizado com a placa: ");

	}

	private void validarPlaca(Veiculo veiculoParaValidarPlaca) {
		veiculoParaValidarPlaca.setPlaca(veiculoParaValidarPlaca.getPlaca().toUpperCase());
		// Escolher Tipo de Placa de acordo com a numeração
		TipoPlaca tipoPlaca = Pattern.matches(TipoPlaca.NACIONAL.getTipoPlaca(), veiculoParaValidarPlaca.getPlaca())
				? TipoPlaca.NACIONAL
				: TipoPlaca.MERCOSUL;
		veiculoParaValidarPlaca.setTipoPlaca(tipoPlaca);
	}

	// Verificar se a ID informada existe
	private Veiculo verifyIfExists(Long id) throws VeiculoNotFoundException {
		return veiculoRepository.findById(id).orElseThrow(() -> new VeiculoNotFoundException(id));
	}

	// Verificar se a placa informada existe
	private Veiculo verifyIfExists(String procuraPlaca) throws VeiculoNotFoundException {
		return veiculoRepository.findByPlaca(procuraPlaca)
				.orElseThrow(() -> new VeiculoNotFoundException(procuraPlaca));
	}

	private MessageResponseDTO createMessageResponse(String placa, String message) {
		return MessageResponseDTO.builder().message(message + placa).build();
	}

}
