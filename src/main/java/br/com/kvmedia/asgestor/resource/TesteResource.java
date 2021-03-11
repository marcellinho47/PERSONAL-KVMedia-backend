package br.com.kvmedia.asgestor.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kvmedia.asgestor.config.CustomException;

@RestController
@RequestMapping("/testes")
public class TesteResource {

	@GetMapping
	@RequestMapping("/{id}")
	private void teste(@PathVariable Integer id) {

		throw new CustomException("teste bolado");
	}
}
