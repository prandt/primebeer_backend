package br.com.primebeer.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.primebeer.model.Cart;

public class CartDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Date instant;
	private String userName;

	public CartDTO(Cart obj) {
		id = obj.getId();
		instant = obj.getInstant();
		userName = obj.getUser().getName();
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
