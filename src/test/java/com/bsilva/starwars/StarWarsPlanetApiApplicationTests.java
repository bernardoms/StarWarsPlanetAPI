package com.bsilva.starwars;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bsilva.starwars.entity.Planet;
import com.bsilva.starwars.service.PlanetService;
import com.bsilva.starwars.util.TestUtil;

import static org.hamcrest.Matchers.hasSize;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StarWarsPlanetApiApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private PlanetService service;
	//SUCCESS CASES
	//Test if the planet is created and have the same number of views get from swapi api
	@Test
	public void shouldCreatePlanet() throws Exception {
		
		  Planet planet = new Planet();
		  planet.setNome("Naboo");
		  planet.setClima("temperate");
		  planet.setTerreno("swamp, jungles");
		  
		this.mockMvc.perform(post("/planets").contentType(MediaType.APPLICATION_JSON).content(TestUtil.asJsonString(planet)))
		.andExpect(jsonPath("$.nome").value(service.serviceGetPlanet().getNome()))
		.andExpect((jsonPath("$.clima").value(service.serviceGetPlanet().getClima())))
		.andExpect(jsonPath("$.terreno").value(service.serviceGetPlanet().getTerreno()))
		.andExpect(jsonPath("$.qtdAparicao").value(service.serviceGetPlanet().getQtdAparicao()))
		.andExpect(status().isCreated());
	}
	//Test to get planets
	@Test
	public void shouldGetAllPlanets() throws Exception {
		//POST A NEW PLANET
		Planet planet = new Planet();
		planet.setNome("Alderaan");
		planet.setClima("temperate");
		planet.setTerreno("grasslands, mountains");
		
		this.mockMvc.perform(post("/planets").contentType(MediaType.APPLICATION_JSON).content(TestUtil.asJsonString(planet)));
		//GET all planets
		this.mockMvc.perform(get("/planets")).andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].nome").value("Naboo"))
		.andExpect(jsonPath("$[0].clima").value("temperate"))
		.andExpect(jsonPath("$[0].terreno").value("swamp, jungles"))
		.andExpect(jsonPath("$[0].qtdAparicao").value(4))
		.andExpect(jsonPath("$[1].nome").value("Alderaan"))
		.andExpect(jsonPath("$[1].clima").value("temperate"))
		.andExpect(jsonPath("$[1].terreno").value("grasslands, mountains"))
		.andExpect(jsonPath("$[1].qtdAparicao").value(2));
	}
	//Test to get planet by name
	@Test
	public void shouldGetPlanetByName() throws Exception {
		this.mockMvc.perform(get("/planets/search/").param("name","Naboo")).andExpect(status().isOk())
		.andExpect(jsonPath("$.nome").value("Naboo"))
		.andExpect(jsonPath("$.clima").value("temperate"))
		.andExpect(jsonPath("$.terreno").value("swamp, jungles"))
		.andExpect(jsonPath("$.qtdAparicao").value(4));
	}
	//Test to get planet by id
	@Test
	public void shouldGetPlanetById() throws Exception {
		this.mockMvc.perform(get("/planets/3")).andExpect(status().isOk())
		.andExpect(jsonPath("$.nome").value("Alderaan"))
		.andExpect(jsonPath("$.clima").value("temperate"))
		.andExpect(jsonPath("$.terreno").value("grasslands, mountains"))
		.andExpect(jsonPath("$.qtdAparicao").value(2));
	}
	//Test to remove a planet
	@Test
	public void shouldRemovePlanetById() throws Exception {
		//POST to a new planet
		Planet planet = new Planet();
		planet.setNome("Geonosis");
		planet.setClima("temperate, arid");
		planet.setTerreno("rock, desert, mountain, barren");
		this.mockMvc.perform(post("/planets").contentType(MediaType.APPLICATION_JSON).content(TestUtil.asJsonString(planet)));
		long id = service.serviceGetPlanet().getId();
		//Removing Planet
		this.mockMvc.perform(delete("/planets/" + id)).andExpect(status().isOk())
		.andExpect(jsonPath("$.nome").value("Geonosis"))
		.andExpect(jsonPath("$.clima").value("temperate, arid"))
		.andExpect(jsonPath("$.terreno").value("rock, desert, mountain, barren"))
		.andExpect(jsonPath("$.qtdAparicao").value(1));
	}
	//FAILED CASES
	//Test add a already registered planet
	@Test
	public void shouldgiveAddSameError() throws Exception {
		//POST to a new planet
		Planet planet = new Planet();
		planet.setNome("Geonosis");
		planet.setClima("temperate, arid");
		planet.setTerreno("rock, desert, mountain, barren");
		this.mockMvc.perform(post("/planets").contentType(MediaType.APPLICATION_JSON).content(TestUtil.asJsonString(planet)));
		//Add the same planet
		
		this.mockMvc.perform(post("/planets").contentType(MediaType.APPLICATION_JSON).content(TestUtil.asJsonString(planet))).andExpect(status().isUnprocessableEntity())
		.andExpect(jsonPath("$.error_code").value("001"))
		.andExpect(jsonPath("$.description").value("PLANET ALREADY EXISTS"));
	}
	@Test
	//Test add a non existent planet
	public void shouldgiveAddError() throws Exception {
		//POST to a new planet
		Planet planet = new Planet();
		planet.setNome("Geonosisss");
		planet.setClima("temperate, arid");
		planet.setTerreno("rock, desert, mountain, barren");

		this.mockMvc.perform(post("/planets").contentType(MediaType.APPLICATION_JSON).content(TestUtil.asJsonString(planet))).andExpect(status().isNotFound())
		.andExpect(jsonPath("$.error_code").value("002"))
		.andExpect(jsonPath("$.description").value("PLANET DOESN'T EXISTS in STAR WARS"));
	}
	@Test
	//Test get a non existent planet name
	public void shouldgiveGetNameError() throws Exception {
		this.mockMvc.perform(get("/planets/search/").param("name", "Naboooooo")).andExpect(status().isNotFound())
		.andExpect(jsonPath("$.error_code").value("002"))
		.andExpect(jsonPath("$.description").value("PLANET NOT FOUND"));
	}
	@Test
	//Test get a non existent planet id
	public void shouldgiveGetIdError() throws Exception {
		this.mockMvc.perform(get("/planets/9999")).andExpect(status().isNotFound())
		.andExpect(jsonPath("$.error_code").value("002"))
		.andExpect(jsonPath("$.description").value("PLANET NOT FOUND"));
	}
	@Test
	
	//Test get a non existent planet id
	public void shouldgiveRemoveIdError() throws Exception {
		this.mockMvc.perform(delete("/planets/9999")).andExpect(status().isNotFound())
		.andExpect(jsonPath("$.error_code").value("002"))
		.andExpect(jsonPath("$.description").value("PLANET NOT FOUND"));
	}
	
}
