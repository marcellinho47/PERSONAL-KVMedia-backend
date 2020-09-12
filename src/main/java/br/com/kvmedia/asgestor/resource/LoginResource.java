package br.com.kvmedia.asgestor.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kvmedia.asgestor.entity.OperatorEntity;
import br.com.kvmedia.asgestor.entity.dto.EmailDTO;
import br.com.kvmedia.asgestor.security.JWTUtil;
import br.com.kvmedia.asgestor.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginResource {

	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private LoginService loginService;

	@PostMapping
	@RequestMapping(value = "/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {

		OperatorEntity user = loginService.getAuthenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");

		return ResponseEntity.noContent().build();
	}

	@PostMapping
	@RequestMapping("/forgot")
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO emailDto) throws Exception {

		loginService.forgot(emailDto.getLogin());

		return ResponseEntity.noContent().build();
	}
}
