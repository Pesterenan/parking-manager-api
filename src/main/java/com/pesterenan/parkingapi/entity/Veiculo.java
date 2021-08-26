package com.pesterenan.parkingapi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.pesterenan.parkingapi.enums.TipoPlaca;
import com.pesterenan.parkingapi.enums.TipoVeiculo;

import lombok.Builder;

@Entity
@Builder
public class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoVeiculo tipoVeiculo;

	@Column(unique = true,nullable = false)
	private String placa;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoPlaca tipoPlaca;

	public Veiculo() {
		
	}
	
	public Veiculo(Long id, TipoVeiculo tipoVeiculo, String placa, TipoPlaca tipoPlaca) {
		this.id = id;
		this.tipoVeiculo = tipoVeiculo;
		this.placa = placa;
		this.tipoPlaca = tipoPlaca;
	}

	// Getters and Setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public TipoPlaca getTipoPlaca() {
		return tipoPlaca;
	}

	public void setTipoPlaca(TipoPlaca tipoPlaca) {
		this.tipoPlaca = tipoPlaca;
	}
	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	// Hashcode and Equals
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		result = prime * result + ((tipoPlaca == null) ? 0 : tipoPlaca.hashCode());
		result = prime * result + ((tipoVeiculo == null) ? 0 : tipoVeiculo.hashCode());
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
		Veiculo other = (Veiculo) obj;
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
		if (tipoPlaca != other.tipoPlaca)
			return false;
		if (tipoVeiculo != other.tipoVeiculo)
			return false;
		return true;
	}

}
