package br.com.kvmedia.asgestor.service;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import br.com.kvmedia.asgestor.entity.SitesEntity;
import br.com.kvmedia.asgestor.repository.SitesRepository;

@Service
public class SitesService {

	@Autowired
	private SitesRepository sitesRepository;

	@Autowired
	private EmailService emailService;

	@Value("${default.form.receiver}")
	private String receiver;

	public SitesEntity formReceve(SitesEntity sitesEntity) {

		// Save the form contact
		sitesEntity.setOperatorInclusion(1);
		SitesEntity entity = sitesRepository.save(sitesEntity);

		// Building formatted message - to agency
		StringBuilder sb = new StringBuilder();
		sb.append("Nome: ").append(entity.getName());
		sb.append("\n\nTelefone: ").append(entity.getDddTelefone() == null ? "-" : entity.getDddTelefone()).append(entity.getTelefone() == null ? "" : entity.getTelefone());
		sb.append("\n\nMensagem: ").append(entity.getMessage());
		sb.append("\n\nData de Envio: ").append(entity.getSendDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss", new Locale("pt", "BR"))));

		SimpleMailMessage mail = emailService.prepareMail(this.receiver, null, "Contato Site: " + entity.getSite(), sb.toString());
		emailService.sendEmail(mail);

		// buildind formatted message - to client
//		sb = new StringBuilder();
//		sb.append("");
//		
//		mail = emailService.prepareMail(entity.getEmail(), null, "assunto", "corpo");
//		
//		emailService.sendEmail(mail);
//
		return entity;
	}
}
