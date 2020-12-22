package com.capgemini.tlta.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Configuration
@Slf4j
public class LoggerAspect {


	@Pointcut("execution(* com.capgemini.tlta.controller.AssessmentActivityController.*(..))")
	public void applicationPackagePointcut() {

	}
	
	@Around("applicationPackagePointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering method : {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
						joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
			}
			try {
				//explictly invoke joinpoint method
				Object result = joinPoint.proceed();
				if (log.isDebugEnabled()) {
					if(joinPoint.getSignature().getName().equals("getAssessmentById")) {
						log.info("Returning details of 1 Product");
					}else if(joinPoint.getSignature().getName().equals("getAllAssessments")) {
						log.info("Returning details of all Products");
					}else if(joinPoint.getSignature().getName().equals("addAssessment")) {
						log.info("Adding one product");
					}else if(joinPoint.getSignature().getName().equals("deleteAssessment")) {
						log.info("Deleting one product");
					}else if(joinPoint.getSignature().getName().equals("updateAssessment")) {
						log.info("Updating one product");
					}
					log.debug("Exiting method: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
							joinPoint.getSignature().getName(), result);
				}
				return result;
			} catch (Exception e) {
				log.error("Error in {}.{}()", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
				log.error(e.getMessage());
				throw e;
			}
		}catch(Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
}
