package com.my_company.telegram_send_feedback.config.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class LoggingMethodsAspect {

    @Pointcut(value = "execution(* com.my_company.telegram_send_feedback.service.*.*(..))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void logBefore(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();
        LocalDateTime timeExecute = LocalDateTime.now();
        log.info("{} Метод {} с аргументами {}", timeExecute, className + "." + methodName, methodArgs);
    }
}
