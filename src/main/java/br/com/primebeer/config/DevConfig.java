package br.com.primebeer.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.primebeer.service.EmailService;
import br.com.primebeer.service.SmtpEmailService;


@Configuration
@Profile("dev")
public class DevConfig {

	/*@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}*/
	
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
	
}
