package br.com.kvmedia.asgestor.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kvmedia.asgestor.entity.SitesEntity;
import br.com.kvmedia.asgestor.service.SitesService;

/**
 * @author marcello.alves
 * @since 28/11/2018
 * 
 * Classe que receberá as informações dos formulário de contato  
 * 
 * */
@RestController
@RequestMapping("/sites")
public class SitesResource {

	@Autowired
	private SitesService sitesService;

	@PostMapping
	public ResponseEntity<Void> formReceve(@RequestBody SitesEntity sitesEntity) {

		sitesService.formReceve(sitesEntity);

		return ResponseEntity.created(null).build();
	}

}