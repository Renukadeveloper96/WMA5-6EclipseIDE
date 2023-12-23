package com.minton.userservice.controller;

import com.minton.userservice.constants.AuthProvider;
import com.minton.userservice.entities.Role;
import com.minton.userservice.exception.BadRequestException;
import com.minton.userservice.exception.ResourceNotFoundException;
import com.minton.userservice.entities.User;
import com.minton.userservice.payload.*;
import com.minton.userservice.payload.ApiResponse;
import com.minton.userservice.payload.AuthResponse;
import com.minton.userservice.payload.LoginRequest;
import com.minton.userservice.payload.SignUpRequest;
import com.minton.userservice.repository.RoleRepository;
import com.minton.userservice.repository.UserRepository;
import com.minton.userservice.security.jwt.TokenProvider;
import com.minton.userservice.service.UserService;
import com.minton.userservice.util.SecurityUtils;

import ch.qos.logback.core.status.Status;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
@OpenAPIDefinition(info = @Info(title = "Auth Controller service", version = "v3.3", description = "AuthController"))
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SecurityUtils securityUtils;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private TokenProvider tokenProvider;

	@Autowired
	private UserService userService;

	public static final String PASSWORD = "Password@123";

	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		log.info("/login called");

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = tokenProvider.createToken(authentication);
		return ResponseEntity.ok(new AuthResponse(token));

	}

	@PostMapping("/google/login")
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<?> loginAndCreate(@Valid @RequestBody CreateUpdateRequest createUpdateRequest) {
		log.info("/Create and login ");
		Optional<User> user = userRepository.findByEmailAndProvider(createUpdateRequest.getEmail(),
				AuthProvider.google);
		Authentication authentication = null;
		if (user.isPresent()) {

			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(createUpdateRequest.getEmail(), PASSWORD));
		} else {
			user = Optional.ofNullable(new User());
			if (userRepository.existsByEmail(createUpdateRequest.getEmail())) {
				throw new BadRequestException("User Already Exist. Please Use Your Credentials to LogIn.");
			}
			Role role = roleRepository.findByName("USER");
			Set<Role> roles = new HashSet<>();
			roles.add(role);
			user.get().setName(createUpdateRequest.getFirstName() + " " + createUpdateRequest.getLastName());
			user.get().setFirstName(createUpdateRequest.getFirstName());
			user.get().setLastName(createUpdateRequest.getLastName());
			user.get().setEmail(createUpdateRequest.getEmail());
			user.get().setRoles(roles);
			String password = passwordEncoder.encode("Password@123");
			user.get().setPassword(password);
			user.get().setEmailVerified(true);
			user.get().setProvider(AuthProvider.google);
			userRepository.save(user.get());

			authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(createUpdateRequest.getEmail(), PASSWORD));
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = tokenProvider.createToken(authentication);
		return ResponseEntity.ok(new AuthResponse(token));
	}

	@PostMapping("/signin")
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<?> authenticateUserForm(@RequestBody LoginRequest loginDto) {
		log.info("/signin called");
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "User signed-in successfully!"));
	}

	@PostMapping("/signup")
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		log.info("/signup called");
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new BadRequestException("Email address already in use.");
		}

		Role role = roleRepository.findByName("USER");
		Set<Role> set = new HashSet<>();
		set.add(role);
		// Creating user's account
		User user = new User();
		user.setFirstName(signUpRequest.getFirstName());
		user.setName(signUpRequest.getName());
		user.setLastName(signUpRequest.getLastName());
		user.setPhoneNumber(signUpRequest.getPhoneNumber());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(signUpRequest.getPassword());
		user.setProvider(AuthProvider.local);
		user.setRoles(set);

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User result = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/me")
				.buildAndExpand(result.getId()).toUri();
		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	}

}
