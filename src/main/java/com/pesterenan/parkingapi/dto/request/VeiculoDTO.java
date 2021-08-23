package com.pesterenan.parkingapi.dto.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.pesterenan.parkingapi.enums.TipoVeiculo;

import lombok.Builder;

@Builder
public class VeiculoDTO {

	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TipoVeiculo tipo;
	
	@NotEmpty
	@Size(min =7, max=7)
	private String placa;

	
	
	// Getters and Setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoVeiculo getTipo() {
		return tipo;
	}

	public void setTipo(TipoVeiculo tipo) {
		this.tipo = tipo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public VeiculoDTO() {
		
	}
	
	public VeiculoDTO(Long id, TipoVeiculo tipo, @NotEmpty @Size(min = 7, max = 7) String placa) {
		this.id = id;
		this.tipo = tipo;
		this.placa = placa;
	}
	
	// HashCode and Equals
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VeiculoDTO other = (VeiculoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

	
}
