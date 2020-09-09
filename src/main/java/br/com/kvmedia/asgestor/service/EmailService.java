package br.com.kvmedia.asgestor.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private MailSender mailSender;

	@Value("${default.sender}")
	private String sender;

	public SimpleMailMessage prepareMail(String to, String from, String subject, String text) {

		if (from == null || from.equals("")) {
			from = this.sender;
		}

		SimpleMailMessage mail = new SimpleMailMessage();

		mail.setTo(to);
		mail.setFrom(from);
		mail.setSubject(subject);
		mail.setSentDate(new Date(System.currentTimeMillis()));
		mail.setText(text);

		return mail;
	}

	public void sendEmail(SimpleMailMessage mail) {

		mailSender.send(mail);
	}

}
