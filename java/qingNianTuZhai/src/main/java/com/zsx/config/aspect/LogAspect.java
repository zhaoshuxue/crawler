package com.zsx.config.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by highness on 2017/9/16 0016.
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(public * com.zsx.controller..*(..))")
    public void exec(){
    }

    @Before("exec()")
    public void doBefore(JoinPoint joinPoint){

    }

    @After("exec()")
    public void doAfter(){

    }

    @AfterReturning(pointcut = "exec()", returning = "object")
    public void doAfterReturning(Object object){

    }

}
