package com.minton.userservice.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityUtils {
	
	public static String encodePassword(String password) {
		byte[] encodedBytes = Base64.getEncoder().encode(password.getBytes(StandardCharsets.UTF_8));
		return new String(encodedBytes, StandardCharsets.UTF_8);
	}

	public static String decodePassword(String encodedPassword) {
		byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword.getBytes(StandardCharsets.UTF_8));
		return new String(decodedBytes, StandardCharsets.UTF_8);
	}

}
