package com.bsilva.starwars.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bsilva.starwars.entity.Planet;
import com.bsilva.starwars.service.PlanetService;
@Controller
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
	@GetMapping("/search/")
	ResponseEntity<?>getPlanetsByName(@RequestParam String name ) throws NotFoundException {
		Planet planet = service.getPlanetByName(name);

		if(planet == null){
			throw new NotFoundException("PLANET NOT FOUND");
		}

		return new ResponseEntity<>(planet,HttpStatus.OK);
	}
	
	//Get a planet by id
	@GetMapping("/{id}")
	ResponseEntity<?>getPlanetById(@PathVariable String id) throws NotFoundException {
		Planet planeta = service.getPlanetById(Long.parseLong(id));

		return new ResponseEntity<>(planeta,HttpStatus.OK);
	}
	
	//Add a planet
	@PostMapping
	ResponseEntity<?>savePlanet(@RequestBody Planet planeta) throws Exception {
		service.save(planeta.getNome(), planeta.getClima(), planeta.getTerreno());
		return new ResponseEntity<>(service.serviceGetPlanet(),HttpStatus.CREATED);
	}
	
	//Remove a planet
	@DeleteMapping("/{id}")
	ResponseEntity<?>removePlanet(@PathVariable String id) throws NotFoundException {
		Planet planeta = service.getPlanetById(Long.parseLong(id));
		service.removePlanet(Long.parseLong(id));
		return new ResponseEntity<>(planeta, HttpStatus.OK);
	}
}
