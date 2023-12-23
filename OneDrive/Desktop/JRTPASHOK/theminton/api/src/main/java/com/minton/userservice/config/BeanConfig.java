package com.minton.userservice.config;

import com.minton.userservice.constants.ApplicationConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class BeanConfig {


    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(ApplicationConstant.BASE_NAME);
        messageSource.setDefaultEncoding(ApplicationConstant.DEFAULT_ENCODING_UTF8);
        return messageSource;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
