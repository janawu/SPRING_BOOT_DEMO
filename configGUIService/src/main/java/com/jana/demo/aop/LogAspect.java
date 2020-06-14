package com.jana.demo.aop;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LogAspect {
	
	Logger logger = Logger.getLogger(getClass().getName());
	
	ThreadLocal<Long> startTime = new ThreadLocal<>();
	
	@Pointcut("@annotation(com.jana.demo.aop.Log)")
    public void log(){}

    @Pointcut("execution(public * com.jana.demo.service..*.*(..))")
    public void service(){}
    
    @Pointcut("execution(public * com.jana.demo.controller..*.query*(..))")
    public void query(){}

    
    @Before("log()")
    public void doBeforeLog(JoinPoint joinPoint){
    	MethodSignature signature =  (MethodSignature) joinPoint.getSignature();
    	Method method = signature.getMethod();
    	String logMsg = method.getAnnotation(Log.class).value();
    	logger.info("# " + logMsg + " START");
    	logger.info("- METHOD: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    	logger.info("- ARGS: " + Arrays.toString(joinPoint.getArgs()));
    }
    
    @AfterReturning("log()")
    public void doAfterReturningLog(JoinPoint joinPoint){
    	MethodSignature signature =  (MethodSignature) joinPoint.getSignature();
    	Method method = signature.getMethod();
    	String logMsg = method.getAnnotation(Log.class).value();
    	logger.info("# " + logMsg + " END");
        
    }
    
    
    @Before("service()")
    public void doBeforeService(JoinPoint joinPoint){
    	
    	startTime.set(System.currentTimeMillis());
    	logger.info("==== ["+ joinPoint.getSignature().getName() +"] invoked ====");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("- URL: " + request.getRequestURL().toString());
        // logger.info("- HTTP_METHOD: " + request.getMethod());
        // logger.info("- IP: " + request.getRemoteAddr());
        logger.info("- METHOD: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("- ARGS: " + Arrays.toString(joinPoint.getArgs()));

    }

    @AfterReturning(pointcut = "service()", returning = "r")
    public void doAfterReturningService(JoinPoint joinPoint, Object r){
        // Can print the method return value if needed
    	// logger.info("RESPONSE : " + r);
        logger.info("- EXECUTION TIME: " + (System.currentTimeMillis() - startTime.get()) + "ms");
        
        logger.info("==== ["+ joinPoint.getSignature().getName() +"] finished ====");
    }
    
    @Before("query()")
    public void doBefore(JoinPoint joinPoint){
       
    }

}
