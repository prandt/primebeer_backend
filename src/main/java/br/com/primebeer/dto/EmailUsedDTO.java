package br.com.primebeer.dto;

public class EmailUsedDTO {
	
	private String email;
	private boolean isUsed;
	
	public EmailUsedDTO() {
		
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isUsed() {
		return isUsed;
	}
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	
	

}
