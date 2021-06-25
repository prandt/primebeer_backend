package br.com.primebeer.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.primebeer.model.Cart;
import br.com.primebeer.model.User;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Cart obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendeOrderConfirmationEmailHTML(Cart obj);
	
	void sendEmailHTML(MimeMessage msg);
	
	void sendNewPasswordEmail(User user, String newPass);
	
}
