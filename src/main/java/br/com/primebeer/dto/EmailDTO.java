package br.com.primebeer.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Email
	@NotEmpty
	private String email;
	
	public EmailDTO() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
