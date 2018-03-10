package com.bsilva.starwars.service;

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
		//Get Planet using swapi
		ResponseEntity<RequestWrapper> wrapper = api.getPlanetByName(nomePlaneta);
		
		if (wrapper.getBody().getResults().size() > 0) {
			int size = wrapper.getBody().getResults().get(0).getFilms().size();
			if(getPlanetByName(nomePlaneta) == null) {
				this.planeta = new Planet(nomePlaneta, clima, terreno,size);
				repositorio.save(this.planeta);
			}
			else {
				throw new Exception ("O PLANETA JÁ EXISTE");
			}
		}
		
		if(wrapper.getBody().getResults().size() == 0){
			throw new Exception ("O PLANETA NÃO EXISTE NO MUNDO STAR WARS");
		}		
	}
	
	public Iterable<Planet> getAllPlanets() {
		return repositorio.findAll();
	}
	
	public Planet getPlanetByName(String nome) {
		return repositorio.findByNome(nome);
	}
	
	public Planet getPlanetById(long id){
		return repositorio.findById(id);
	}
	
	public void removePlanet(long id) {
		repositorio.delete(id);
	}
	
	public Planet serviceGetPlanet(){
		return this.planeta;
	}
}
