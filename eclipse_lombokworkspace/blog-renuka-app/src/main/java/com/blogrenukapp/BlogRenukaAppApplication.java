package com.blogrenukapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogRenukaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogRenukaAppApplication.class, args);
	}

	@Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }

}
