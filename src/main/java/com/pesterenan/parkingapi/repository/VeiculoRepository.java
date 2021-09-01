package com.pesterenan.parkingapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pesterenan.parkingapi.entity.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

	Optional<Veiculo> findByPlaca(String procuraPlaca);

}
