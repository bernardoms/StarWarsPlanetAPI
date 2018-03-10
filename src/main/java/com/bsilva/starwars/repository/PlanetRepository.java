package com.bsilva.starwars.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bsilva.starwars.entity.Planet;
@Repository
public interface PlanetRepository extends CrudRepository<Planet,Long> {
	Planet findById(long id);
	Planet findByNome(String nome);
}
