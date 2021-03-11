package br.com.kvmedia.asgestor.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.kvmedia.asgestor.entity.OperatorEntity;
import br.com.kvmedia.asgestor.repository.OperatorRepository;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	private OperatorRepository operatorRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	private Random rand = new Random();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<OperatorEntity> opt = operatorRepository.findByLogin(username);

		if (opt == null || opt.isEmpty()) {
			new UsernameNotFoundException("Login e/ou senha incorretos.");
		}

		return opt.get();
	}

	public OperatorEntity getAuthenticated() {

		return (OperatorEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public void forgot(String login) throws Exception {

		Optional<OperatorEntity> opt = operatorRepository.findByLogin(login);

		if (opt == null || opt.isEmpty() || opt.get() == null) {

			throw new Exception("Email não encontrado.");
		}

		OperatorEntity operator = opt.get();

		String newPassword = newPassword();
		operator.setPassword(passwordEncoder.encode(newPassword));

		operator = operatorRepository.save(operator);
		emailService.sendNewPassword(operator, newPassword);
	}

	private String newPassword() {

		char[] vet = new char[10];

		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}

		return new String(vet);
	}

	private char randomChar() {

		int opt = rand.nextInt(3);

		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);

		} else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);

		} else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
