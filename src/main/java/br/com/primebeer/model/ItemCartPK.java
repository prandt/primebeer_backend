package br.com.primebeer.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Embeddable
public class ItemCartPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonManagedReference(value="cart-it")
	@ManyToOne
	@JoinColumn(name="cart_id")
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name="beer_id")
	private Beer beer;
	
	public ItemCartPK() {}

	public ItemCartPK(Cart cart, Beer beer) {
		super();
		this.cart = cart;
		this.beer = beer;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Beer getBeer() {
		return beer;
	}

	public void setBeer(Beer beer) {
		this.beer = beer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beer == null) ? 0 : beer.hashCode());
		result = prime * result + ((cart == null) ? 0 : cart.hashCode());
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
		ItemCartPK other = (ItemCartPK) obj;
		if (beer == null) {
			if (other.beer != null)
				return false;
		} else if (!beer.equals(other.beer))
			return false;
		if (cart == null) {
			if (other.cart != null)
				return false;
		} else if (!cart.equals(other.cart))
			return false;
		return true;
	}
	
	
	
}
