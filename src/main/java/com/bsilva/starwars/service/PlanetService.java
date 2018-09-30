package com.bsilva.starwars.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bsilva.starwars.entity.Planet;
import com.bsilva.starwars.repository.PlanetRepository;
import com.bsilva.starwars.swapi.RequestWrapper;
import com.bsilva.starwars.swapi.Swapi;
@Service
public class PlanetService {
	private Planet planeta;
	@Autowired
	private PlanetRepository repositorio;

	public void save(String nomePlaneta,String clima, String terreno) throws Exception {
		Swapi api = new Swapi();

		ResponseEntity<RequestWrapper> wrapper = api.getPlanetByName(nomePlaneta);
		
		if (wrapper.getBody().getResults().size() > 0) {
			int qtdAparicao = wrapper.getBody().getResults().get(0).getFilms().size();

			if(getPlanetByName(nomePlaneta) == null) {
				this.planeta = new Planet(nomePlaneta, clima, terreno,qtdAparicao);
				repositorio.save(this.planeta);
			}
			else {
				throw new IllegalArgumentException ("PLANET ALREADY EXISTS");
			}
		}
		
		else{
			throw new NotFoundException("PLANET DOESN'T EXISTS in STAR WARS");
		}		
	}
	
	public Iterable<Planet> getAllPlanets() {
		return repositorio.findAll();
	}
	
	public Planet getPlanetByName(String nome) throws NotFoundException {
		return repositorio.findByNome(nome);
	}
	
	public Planet getPlanetById(long id) throws NotFoundException {
		Planet planet = repositorio.findById(id);

		if(planet == null){
			throw new NotFoundException("PLANET NOT FOUND");
		}

		return planet;
	}
	
	public void removePlanet(long id) {
		repositorio.delete(id);
	}
	
	public Planet serviceGetPlanet(){
		return this.planeta;
	}
}
