package br.com.primebeer.dto;

import java.io.Serializable;

public class BeerNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private Double price;
	private Double teorAlcool;
	private String coloring;
	private String addtionalInformation;
	private Integer rating;
	private Integer amount;
	
	private Integer volumeId;
	private Integer typeBeerId;
	
	private String photoURL1;
	private String photoURL2;
	private String photoURL3;
	
	private Integer manufactureId;
	
	public BeerNewDTO() {}

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

	public Double getTeorAlcool() {
		return teorAlcool;
	}

	public void setTeorAlcool(Double teorAlcool) {
		this.teorAlcool = teorAlcool;
	}

	public String getColoring() {
		return coloring;
	}

	public void setColoring(String coloring) {
		this.coloring = coloring;
	}

	public String getAddtionalInformation() {
		return addtionalInformation;
	}

	public void setAddtionalInformation(String addtionalInformation) {
		this.addtionalInformation = addtionalInformation;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getVolumeId() {
		return volumeId;
	}

	public void setVolumeId(Integer volumeId) {
		this.volumeId = volumeId;
	}

	public Integer getTypeBeerId() {
		return typeBeerId;
	}

	public void setTypeBeerId(Integer typeBeerId) {
		this.typeBeerId = typeBeerId;
	}

	public String getPhotoURL1() {
		return photoURL1;
	}

	public void setPhotoURL1(String photoURL1) {
		this.photoURL1 = photoURL1;
	}

	public String getPhotoURL2() {
		return photoURL2;
	}

	public void setPhotoURL2(String photoURL2) {
		this.photoURL2 = photoURL2;
	}

	public String getPhotoURL3() {
		return photoURL3;
	}

	public void setPhotoURL3(String photoURL3) {
		this.photoURL3 = photoURL3;
	}

	public Integer getManufactureId() {
		return manufactureId;
	}

	public void setManufactureId(Integer manufactureId) {
		this.manufactureId = manufactureId;
	}
	
}
