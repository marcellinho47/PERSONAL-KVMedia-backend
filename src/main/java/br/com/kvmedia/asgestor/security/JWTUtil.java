package br.com.kvmedia.asgestor.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	private String secret = "20577e4c631beec6746d3167f2f522b313c172df9622a0f357b044dd0fa2b987"; // K + B + S
	private Long expiration = (long) 1000 * 60 * 60 * 24; // 1 Dia

	public String generateToken(String username) {

		return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + expiration)).signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}

	public boolean isTokenValid(String token) {

		Claims claims = getClaims(token);

		if (claims != null) {

			String username = claims.getSubject();
			Date expiration = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());

			if (username != null && expiration != null && now.before(expiration)) {

				return true;
			}
		}

		return false;
	}

	public String getUsername(String token) {

		Claims claims = getClaims(token);

		if (claims != null) {

			String username = claims.getSubject();

			if (username != null) {

				return username;
			}
		}

		return null;
	}

	private Claims getClaims(String token) {

		try {

			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();

		} catch (Exception e) {

			return null;
		}
	}
}