package com.github.enid3.cardgames.webapp.service.advices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class LogServicesAspect {

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void anyService(){}

    @Pointcut("execution(public * *(..))")
    public void anyPublicMethod() {
    }

    @Pointcut("!execution(public * com.github.enid3.cardgames.webapp.service.impl.BrockerImpl.*(..))")
    public void notBrocker() {
    }

    // TODO fix: add turn(...)
    @Around(value = "anyService() && anyPublicMethod() && notBrocker()")
    public Object logMethodCall(ProceedingJoinPoint jp) throws Throwable {
        MethodSignature sig = (MethodSignature) jp.getSignature();
        log.info("{}: {}",
                sig.getMethod().getDeclaringClass().getSimpleName()
                        + "."
                        + sig.getMethod().getName(), jp.getArgs());
        return jp.proceed();
    }
}
