package com.bsilva.starwars.swapi;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class RequestWrapper {
	//Ignorar os outros campos do json que não importam e não estão "mapeados" na classe como o next e previus.
		@JsonIgnoreProperties(ignoreUnknown = true)
		private ArrayList<PlanetSwapi> results;

		public ArrayList<PlanetSwapi> getResults() {
			return results;
		}

		public void setResults(ArrayList<PlanetSwapi> results) {
			this.results = results;
		}
}
