package br.com.primebeer.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.primebeer.model.enums.Profile;

@Entity
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	@JsonIgnore
	private String password;
	@JsonFormat(pattern = "dd/MM/yyy")
	private Date birthDate;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	@JsonManagedReference(value = "user_ad")
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Address> addresses = new ArrayList<Address>();
	
	@ElementCollection
	@CollectionTable(name="phone_number")
	private Set<String> phoneNumbers = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Cart> carts = new ArrayList<Cart>();
	
	public User() {
		addPerfil(Profile.USER);
	}

	public User(Integer id, String name, String email, String password, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
		addPerfil(Profile.USER);
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Profile> getPerfis(){
		return perfis.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
	}
	public void addPerfil(Profile perfil) {
		perfis.add(perfil.getCod());
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Set<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	
	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
