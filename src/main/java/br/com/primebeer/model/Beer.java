package br.com.primebeer.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Beer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double price;
	private Double teorAlcool;
	private String coloring;
	private String addtionalInformation;
	private Integer rating;
	private Integer amount;

	/*@OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<Volume> volumes = new ArrayList<Volume>();
	
	@OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<TypeBeer> typeOfBeers = new ArrayList<TypeBeer>();*/
	@OneToOne
	private TypeBeer typeBeer;
	@OneToOne
	private Volume volume;

	@ManyToOne
	@JoinColumn(name = "manufacturer_id")
	private Manufacturer manufacturer;
	
	@OneToMany(mappedBy = "beer")
	private List<Photo> photos = new ArrayList<Photo>();
	
	@OneToMany(mappedBy = "id.beer")
	private Set<ItemCart> items = new HashSet<>();

	public Beer() {
	}

	public Beer(Integer id, String name, Double price, Double teorAlcool, String coloring, String addtionalInformation,
			Integer rating, Integer amount, Manufacturer manufacturer, TypeBeer typeBeer, Volume volume) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.teorAlcool = teorAlcool;
		this.coloring = coloring;
		this.addtionalInformation = addtionalInformation;
		this.rating = rating;
		this.amount = amount;
		this.manufacturer = manufacturer;
		this.typeBeer = typeBeer;
		this.volume = volume;
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

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	
	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	public TypeBeer getTypeBeer() {
		return typeBeer;
	}

	public void setTypeBeer(TypeBeer typeBeer) {
		this.typeBeer = typeBeer;
	}

	public Volume getVolume() {
		return volume;
	}

	public void setVolume(Volume volume) {
		this.volume = volume;
	}

	@JsonIgnore
	public Set<ItemCart> getItems() {
		return items;
	}

	public void setItems(Set<ItemCart> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Beer other = (Beer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
