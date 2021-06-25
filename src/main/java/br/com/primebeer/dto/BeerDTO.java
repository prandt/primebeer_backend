package br.com.primebeer.dto;

import java.io.Serializable;

import br.com.primebeer.model.Beer;
import br.com.primebeer.model.TypeBeer;
import br.com.primebeer.model.Volume;

public class BeerDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Double price;
	private TypeBeer typeBeer;
	private Volume volume;
	private Double teorAlcool;
	
	public BeerDTO(Beer obj) {
		id = obj.getId();
		name = obj.getName();
		price = obj.getPrice();
		typeBeer = obj.getTypeBeer();
		volume = obj.getVolume();
		teorAlcool = obj.getTeorAlcool();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public TypeBeer getTypeBeer() {
		return typeBeer;
	}

	public void setTypeBeer(TypeBeer typeBeerName) {
		this.typeBeer = typeBeerName;
	}

	public Volume getVolume() {
		return volume;
	}

	public void setVolume(Volume volume) {
		this.volume = volume;
	}

	public Double getTeorAlcool() {
		return teorAlcool;
	}
	
	public void setTeorAlcool(Double teorAlcool) {
		this.teorAlcool = teorAlcool;
	}
	
	
	
	
}
