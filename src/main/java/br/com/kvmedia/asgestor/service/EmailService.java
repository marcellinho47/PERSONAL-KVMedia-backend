package br.com.kvmedia.asgestor.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.kvmedia.asgestor.entity.OperatorEntity;

@Service
public class EmailService {

	@Autowired
	private MailSender mailSender;
	@Autowired
	private JavaMailSender javaMailSender;

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

	public MimeMessage prepareJavaMail(String to, String from, String subject, String text, boolean isHtml, List<String> pathAttachments) throws MessagingException, IOException {

		if (from == null || from.equals("")) {
			from = this.sender;
		}

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(to);
		helper.setTo(from);
		helper.setSubject(subject);
		helper.setText(text, isHtml);

		if (pathAttachments != null && pathAttachments.size() > 0) {

			for (String path : pathAttachments) {

				File file = new File(path);

				if (file.exists() && file.isFile()) {

					DataSource source = new FileDataSource(file);
					helper.addAttachment(file.getName(), source);
				}
			}
		}

		return message;
	}

	public void sendEmail(SimpleMailMessage mail) {

		mailSender.send(mail);
	}

	public void sendEmail(MimeMessage mail) {

		javaMailSender.send(mail);
	}

	public void sendNewPassword(OperatorEntity operator, String newPassword) throws MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(this.sender);
		helper.setTo(operator.getLogin());
		helper.setSubject("KV Media - Recuperação de Senha");
		helper.setText("<h2>Recupeação de Senha</h2></br></br></br><p>Sua nova senha é: " + newPassword + "</p>", true);

		javaMailSender.send(message);
	}
}
