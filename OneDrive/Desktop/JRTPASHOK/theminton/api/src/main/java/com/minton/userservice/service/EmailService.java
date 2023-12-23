package com.minton.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Map;

@Service
public class EmailService {

    private final SpringTemplateEngine templateEngine;

    @Autowired
    public EmailService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String processEmailTemplate(String templateName, Map<String, Object> variables) {
        Context context = new Context();
        context.setVariables(variables);
        return templateEngine.process(templateName, context);
    }
}
