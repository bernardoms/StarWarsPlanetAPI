package com.bsilva.starwars.swapi;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class RequestWrapper {

		@JsonIgnoreProperties(ignoreUnknown = true)
		private ArrayList<PlanetSwapi> results;

		public ArrayList<PlanetSwapi> getResults() {
			return results;
		}

		public void setResults(ArrayList<PlanetSwapi> results) {
			this.results = results;
		}
}
