package com.pesterenan.parkingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pesterenan.parkingapi.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
