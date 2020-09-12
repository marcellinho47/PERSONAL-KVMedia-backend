package br.com.kvmedia.asgestor.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.kvmedia.asgestor.entity.OperatorEntity;
import br.com.kvmedia.asgestor.entity.dto.LoginDTO;

/**
 * Classe que é reponsável por validar os dados e retornar o token
 * 
 * @author alves
 */

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private JWTUtil jwtUtil;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@Override // Filtro para autenticação
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

		try {
			LoginDTO creds = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getLogin(), creds.getPassword(), new ArrayList<>());
			Authentication auth = authenticationManager.authenticate(authToken);

			return auth;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override // Resposta com o token
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

		String username = ((OperatorEntity) authResult.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
	}
}