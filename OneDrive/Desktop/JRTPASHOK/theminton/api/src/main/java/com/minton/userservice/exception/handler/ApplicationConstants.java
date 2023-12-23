package com.minton.userservice.exception.handler;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@ConfigurationProperties(prefix = "application")
@Data
public class ApplicationConstants {

  private String corsUrl;

}
