package br.com.primebeer.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date instant;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToMany(mappedBy = "id.cart")
	private Set<ItemCart> items = new HashSet<>();

	public Cart() {
	}

	public Cart(Integer id, Date instant, User user, Address address) {
		super();
		this.id = id;
		this.instant = instant;
		this.user = user;
		this.address = address;
	}
	
	public double getTotal() {
		double soma = 0;
		for(ItemCart ic : items) {
			soma = soma + ic.getSubTotal();
		}
		return soma;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstant() {
		return instant;
	}

	public void setInstant(Date instant) {
		this.instant = instant;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

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
		Cart other = (Cart) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido numero: ");
		builder.append(getId());
		builder.append(", Instante: ");
		builder.append(sdf.format(getInstant()));
		builder.append(", Cliente: ");
		builder.append(getUser().getName());
		builder.append("\nDetalhes: \n");
		for(ItemCart ic : getItems()) {
			builder.append(ic.toString());
		}
		builder.append(("Valor total: "));
		builder.append(nf.format(getTotal()));
		return builder.toString();
	}
	
	

}
