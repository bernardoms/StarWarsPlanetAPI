package com.bsilva.starwars.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bsilva.starwars.entity.Planet;
import com.bsilva.starwars.service.PlanetService;
@RestController
@RequestMapping("/planets")
public class StarWarsPlanetController {
	@Autowired 
	private PlanetService service;
	//Get all planets
	@GetMapping
	ResponseEntity<?> getPlanets(){
		ArrayList<Planet> planetas = (ArrayList<Planet>) service.getAllPlanets();
		return new ResponseEntity<>(planetas,HttpStatus.OK);
	}
	//Get a planet by Name
	@GetMapping("/search/{nome}")
	ResponseEntity<?>getPlanetsByName(@PathVariable String nome ){
		Planet planeta = service.getPlanetByName(nome);
		if(planeta == null)
		{
			HashMap<String, String> error = new HashMap<>();
			error.put("nome", nome);
			error.put("error_code", "001");
			error.put("description", "PLANET NOT FOUND");
			return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(planeta,HttpStatus.OK);
	}
	
	//Get a planet by id
	@GetMapping("/{id}")
	ResponseEntity<?>getPlanetById(@PathVariable String id){
		Planet planeta = service.getPlanetById(Long.parseLong(id));
		if(planeta == null)
		{
			HashMap<String, String> error = new HashMap<>();
			error.put("id", id);
			error.put("error_code", "001");
			error.put("description", "PLANET NOT FOUND");
			return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(planeta,HttpStatus.OK);
	}
	
	//Add a planet
	@PostMapping
	ResponseEntity<?>savePlanet(@RequestBody Planet planeta){
		try {
			service.save(planeta.getNome(), planeta.getClima(), planeta.getTerreno());
		} catch (Exception e) {
			HashMap<String, String> error = new HashMap<>();
			error.put("nome", planeta.getNome());
			error.put("error_code", "002");
			error.put("description", e.getMessage());
			return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(service.serviceGetPlanet(),HttpStatus.CREATED);
	}
	
	//Remove a planet
	@DeleteMapping("/{id}")
	ResponseEntity<?>removePlanet(@PathVariable String id){
		Planet planeta = service.getPlanetById(Long.parseLong(id));
		if(planeta == null)
		{
			HashMap<String, String> error = new HashMap<>();
			error.put("id", (id));
			error.put("error_code", "001");
			error.put("description", "PLANET NOT FOUND");
			return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		}
		service.removePlanet(Long.parseLong(id));
		return new ResponseEntity<>(planeta, HttpStatus.OK);
	}
}
