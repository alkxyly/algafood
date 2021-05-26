package com.algaworks.algafood.infrastructure.service.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;

public class SmtpEnvioEmailService implements EnvioEmailService{

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private EmailProperties emailProperties;
	
	@Override
	public void enviar(Mensagem mensagem) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(emailProperties.getRemetente());
			helper.setSubject(mensagem.getAssunto());
			helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
			helper.setText(mensagem.getCorpo(), true);
			
			mailSender.send(mimeMessage);
			
		} catch (MessagingException e) {
			throw new  EmailException("Naão foi possivel enviar email", e);
		}
	}
}
