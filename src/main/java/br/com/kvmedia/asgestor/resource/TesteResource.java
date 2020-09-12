package br.com.kvmedia.asgestor.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kvmedia.asgestor.repository.OperatorRepository;

@RestController
@RequestMapping("/testes")
public class TesteResource {

	@Autowired
	private OperatorRepository operatorRepository;

	@GetMapping
	@RequestMapping("/{id}")
	private void teste(@PathVariable Integer id) {

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	}
}
