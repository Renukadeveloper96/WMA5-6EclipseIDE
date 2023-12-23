package com.minton.userservice.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@Slf4j
public class LoggingAdvice {


    @Pointcut("execution(* com.minton.userservice.controller.UserController.*.*(..))")
    public void controllerPointcut() {

    }

    @Around("controllerPointcut()")
    public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        Object[] array = pjp.getArgs();
        log.info("method invoked : " + className + ": " + methodName + "() " +
                "Request arguments :" + mapper.writeValueAsString(array));
        Object object = pjp.proceed();
        log.info(className + ": " + methodName + "() " +
                "Response : " + mapper.writeValueAsString(object));
        return object;
    }


}
