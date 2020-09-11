package br.com.kvmedia.asgestor.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kvmedia.asgestor.entity.OperatorEntity;
import br.com.kvmedia.asgestor.entity.enums.ProfileEnums;
import br.com.kvmedia.asgestor.repository.OperatorRepository;

@RestController
@RequestMapping("/testes")
public class TesteResource {

	@Autowired
	private OperatorRepository operatorRepository;

	@GetMapping
	@RequestMapping("/{id}")
	private void teste(@PathVariable Integer id) {

		Optional<OperatorEntity> findById = operatorRepository.findById(id);
		
		ProfileEnums profile = findById.get().getOpProfiles().get(0).getProfile();
		
		System.out.println(profile);
	}
}
