	package br.com.primebeer.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemCart implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private ItemCartPK id = new ItemCartPK();

	private Double discount;
	private Integer amount;
	private Double price;
	
	public ItemCart() {
	}
	
	public ItemCart(Cart cart, Beer beer,Double discount, Integer amount, Double price) {
		super();
		id.setCart(cart);
		id.setBeer(beer);
		this.discount = discount;
		this.amount = amount;
		this.price = price;
	}
	
	public double getSubTotal() {
		return (price - discount) * amount;
	}
	
	@JsonBackReference(value="cart-it")
	public Cart getCart() {
		return id.getCart();
	}
	
	public Beer getBeer() {
		return id.getBeer();
	}
	
	public void setCart(Cart cart) {
		id.setCart(cart);
	}
	
	public void setBeer(Beer beer) {
		id.setBeer(beer);
	}

	public ItemCartPK getId() {
		return id;
	}

	public void setId(ItemCartPK id) {
		this.id = id;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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
		ItemCart other = (ItemCart) obj;
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
		StringBuilder builder = new StringBuilder();
		builder.append(getBeer().getName());
		builder.append(", Qtd: ");
		builder.append(getAmount());
		builder.append(", Preço Unitário: ");
		builder.append(nf.format(getPrice()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}
	
	

}
