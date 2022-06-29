package io.eho.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	// setup logger
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// setup pointcut declarations
	@Pointcut("execution(* io.eho.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* io.eho.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* io.eho.springdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void AppFlow() {}
	
	// add @Before
	@Before("AppFlow()")
	public void before(JoinPoint joinPoint) {
		
		// display method being called
		String method = joinPoint.getSignature().toShortString();
		logger.info("====>>> in @Before: calling method: " + method);
		
		// display the arguments to the method
		
		// get the arguments
		Object[] args = joinPoint.getArgs();
		
		// loop thru and display the args
		for (Object o : args) {
			logger.info("====>>> argument: " + o);
		}
		
		
	}

	
	// add @AfterReturning
	@AfterReturning(
			pointcut="AppFlow()",
			returning="result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		
		// display the method returned from
		String method = joinPoint.getSignature().toShortString();
		logger.info("====>>> in @AfterReturning: calling method: " + method);
		
		// display data returned
		logger.info("====>>> result: " + result);
		
	}

}
