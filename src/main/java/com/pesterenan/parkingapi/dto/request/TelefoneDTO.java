package com.pesterenan.parkingapi.dto.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.pesterenan.parkingapi.enums.TipoTelefone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneDTO {

	
	
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TipoTelefone tipo;
	
	@NotEmpty
	@Size(min= 13, max =14)
	private String numero;
	
}
