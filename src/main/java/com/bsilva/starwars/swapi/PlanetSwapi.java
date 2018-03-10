package com.bsilva.starwars.swapi;

import java.util.ArrayList;

public class PlanetSwapi {
    private String name;
    private String diameter;
    private String gravity;
    private String population;
    private String climate;
    private String terrain;
    private String created;
    private String edited;
    private String url;
    private String rotationPeriod;
    private String orbitalPeriod;
    private String surfaceWater;
    private ArrayList<String> residents;
    private ArrayList<String> films;
    
	public ArrayList<String> getFilms() {
		return films;
	}
	public void setFilms(ArrayList<String> films) {
		this.films = films;
	}
	public ArrayList<String> getResidents() {
		return residents;
	}
	public void setResidents(ArrayList<String> residents) {
		this.residents = residents;
	}
	public String getSurfaceWater() {
		return surfaceWater;
	}
	public void setSurfaceWater(String surfaceWater) {
		this.surfaceWater = surfaceWater;
	}
	public String getOrbitalPeriod() {
		return orbitalPeriod;
	}
	public void setOrbitalPeriod(String orbitalPeriod) {
		this.orbitalPeriod = orbitalPeriod;
	}
	public String getRotationPeriod() {
		return rotationPeriod;
	}
	public void setRotationPeriod(String rotationPeriod) {
		this.rotationPeriod = rotationPeriod;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEdited() {
		return edited;
	}
	public void setEdited(String edited) {
		this.edited = edited;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getTerrain() {
		return terrain;
	}
	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	public String getClimate() {
		return climate;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	public String getPopulation() {
		return population;
	}
	public void setPopulation(String population) {
		this.population = population;
	}
	public String getGravity() {
		return gravity;
	}
	public void setGravity(String gravity) {
		this.gravity = gravity;
	}
	public String getDiameter() {
		return diameter;
	}
	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
