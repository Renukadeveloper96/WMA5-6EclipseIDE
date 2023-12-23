package com.minton.userservice.security.jwt;

import com.minton.userservice.constants.AppProperties;
import com.minton.userservice.entities.Role;
import com.minton.userservice.security.UserPrincipal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.minton.userservice.entities.User;

@Service
public class TokenProvider {

	private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

	@Autowired
	AppProperties appProperties;

	public String createToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());
		
		String authorities = authentication.getAuthorities().stream()
				.map(n -> new SimpleGrantedAuthority(n.getAuthority().toString()))
				.map(SimpleGrantedAuthority::getAuthority).collect(Collectors.joining(","));

		System.out.println(" createToken ============================== ==== " + authorities);
		return Jwts.builder().setSubject(String.format("%s", userPrincipal.getId())).claim("roles", authorities)
				.setIssuedAt(new Date()).setIssuer(appProperties.getAuth().getTokenIssuer()).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret()).compact();
	}

	public Long getUserIdFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(token)
				.getBody();

		return Long.parseLong(claims.getSubject());
	}

	public List<Role> getRolesFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(token)
				.getBody();
		String subject = (String) claims.get(Claims.SUBJECT);
		String roles = (String) claims.get("roles");

		System.out.println("SUBJECT: " + subject);
		System.out.println("roles: " + roles);

		roles = roles.replace("[", "").replace("]", "");
		String[] roleNames = roles.split(",");
		List<Role> roleList = new ArrayList<Role>();
		for (String aRoleName : roleNames) {
			roleList.add(new Role(aRoleName));
		}

		return roleList;
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException ex) {
			logger.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			logger.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			logger.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			logger.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			logger.error("JWT claims string is empty.");
		}
		return false;
	}

	public String getPasswordResetJWT(User user) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getSuperTokenExpirationMsec());

		return Jwts.builder().setSubject(String.format("%s", user.getId())).setIssuedAt(new Date())
				.setIssuer(appProperties.getAuth().getTokenIssuer()).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret()).compact();
	}
}
