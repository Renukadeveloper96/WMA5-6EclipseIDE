package com.upendra.rai;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class RolePermissionBasedAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RolePermissionBasedAuthorizationApplication.class, args);
	}
	@Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }

}
