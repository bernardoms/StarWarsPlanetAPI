package com.bsilva.starwars.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Planet {
	@JsonIgnore
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String nome;
	private String clima;
	private String terreno;
	private int qtdAparicao;
	
	public Planet(String nome,String clima,String terreno,int qtdAparicao) {
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
		this.qtdAparicao = qtdAparicao;
	}
	
	public Planet() {
		
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getClima() {
		return clima;
	}
	
	public void setClima(String clima) {
		this.clima = clima;
	}
	
	public String getTerreno() {
		return terreno;
	}
	
	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}
	
	public int getQtdAparicao() {
		return qtdAparicao;
	}
	
	public void setQtdAparicao(int qtdAparicao) {
		this.qtdAparicao = qtdAparicao;
	} 
	
	public long getId() {
		return this.id;
	}
}
