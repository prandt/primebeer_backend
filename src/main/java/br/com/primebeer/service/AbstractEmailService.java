package br.com.primebeer.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.primebeer.model.Cart;
import br.com.primebeer.model.User;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendOrderConfirmationEmail(Cart obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Cart obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getUser().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Código: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
	
	protected String htmlFromTemplateCart(Cart obj) {
		Context context = new Context();
		context.setVariable("cart", obj);
		return templateEngine.process("email/cartConfirmation", context);
	}
	
	@Override
	public void sendNewPasswordEmail(User user, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(user, newPass);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareNewPasswordEmail(User user, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(user.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha: ");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		return sm;
	}

	@Override
	public void sendeOrderConfirmationEmailHTML(Cart obj) {
		try {
			MimeMessage mm = prepareMimeFromPedido(obj);
			sendEmailHTML(mm);
		}
		catch(MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
	}

	protected MimeMessage prepareMimeFromPedido(Cart obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getUser().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido confirmado! Código "+obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateCart(obj), true);
		return mimeMessage;
	}
}