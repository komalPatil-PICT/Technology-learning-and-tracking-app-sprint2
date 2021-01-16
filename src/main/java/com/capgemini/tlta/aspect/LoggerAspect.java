package com.capgemini.tlta.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class LoggerAspect.
 */
@Aspect
@Configuration
@Slf4j
public class LoggerAspect {


	/**
	 * Application package pointcut.
	 */
	@Pointcut("execution(* com.capgemini.tlta.controller.AssessmentActivityController.*(..))")
	public void applicationPackagePointcut() {

	}
	
	/**
	 * Application package pointcut 1.
	 */
	@Pointcut("execution(* com.capgemini.tlta.controller.RegisterUserController.*(..))")
	public void applicationPackagePointcut1() {

	}
	
	/**
	 * Application package pointcut 2.
	 */
	@Pointcut("execution(* com.capgemini.tlta.controller.LearningActivityController.*(..))")
	public void applicationPackagePointcut2() {

	}
	
	/**
	 * Application package pointcut 3.
	 */
	@Pointcut("execution(* com.capgemini.tlta.controller.UserActivityController.*(..))")
	public void applicationPackagePointcut3() {

	}
	/**
	 * Log around.
	 *
	 * @param joinPoint the join point
	 * @return the object
	 * @throws Throwable the throwable
	 */
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
						log.info("Returning details of 1 Assessment");
					}else if(joinPoint.getSignature().getName().equals("getAllAssessments")) {
						log.info("Returning details of all Assessments");
					}else if(joinPoint.getSignature().getName().equals("addAssessment")) {
						log.info("Adding one assessment");
					}else if(joinPoint.getSignature().getName().equals("deleteAssessment")) {
						log.info("Deleting one assessment");
					}else if(joinPoint.getSignature().getName().equals("updateAssessment")) {
						log.info("Updating one assessment");
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

	/**
	 * Log around 1.
	 *
	 * @param joinPoint the join point
	 * @return the object
	 * @throws Throwable the throwable
	 */
	@Around("applicationPackagePointcut1()")
	public Object logAround1(ProceedingJoinPoint joinPoint) throws Throwable {

		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering method : {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
						joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
			}
			try {
				//explictly invoke joinpoint method
				Object result = joinPoint.proceed();
				if (log.isDebugEnabled()) {
					if(joinPoint.getSignature().getName().equals("getRegisterUserById")) {
						log.info("Returning details of 1 Register User");
					}else if(joinPoint.getSignature().getName().equals("getAllRegisterUsers")) {
						log.info("Returning details of all Users");
					}else if(joinPoint.getSignature().getName().equals("addRegisterUser")) {
						log.info("Adding one User");
					}else if(joinPoint.getSignature().getName().equals("deleteRegisterUser")) {
						log.info("Deleting one user");
					}else if(joinPoint.getSignature().getName().equals("updatePassword")) {
						log.info("Updating password");
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

	/**
	 * Log around 2.
	 *
	 * @param joinPoint the join point
	 * @return the object
	 * @throws Throwable the throwable
	 */
	@Around("applicationPackagePointcut2()")
	public Object logAround2(ProceedingJoinPoint joinPoint) throws Throwable {

		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering method : {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
						joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
			}
			try {
				//explictly invoke joinpoint method
				Object result = joinPoint.proceed();
				if (log.isDebugEnabled()) {
					if(joinPoint.getSignature().getName().equals("getLearningActivityById")) {
						log.info("Returning details of 1 Learning Activity");
					}else if(joinPoint.getSignature().getName().equals("getAllLearningActivity")) {
						log.info("Returning details of all Learning Activity");
					}else if(joinPoint.getSignature().getName().equals("addLearningActivity")) {
						log.info("Adding one Learning Activity");
					}else if(joinPoint.getSignature().getName().equals("deleteAssessment")) {
						log.info("Deleting one Learning Activity");
					}else if(joinPoint.getSignature().getName().equals("updateAssessment")) {
						log.info("Updating Learning Activity");
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

	/**
	 * Log around 2.
	 *
	 * @param joinPoint the join point
	 * @return the object
	 * @throws Throwable the throwable
	 */
	@Around("applicationPackagePointcut3()")
	public Object logAround3(ProceedingJoinPoint joinPoint) throws Throwable {

		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering method : {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
						joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
			}
			try {
				//explictly invoke joinpoint method
				Object result = joinPoint.proceed();
				if (log.isDebugEnabled()) {
					if(joinPoint.getSignature().getName().equals("getUserActivityById")) {
						log.info("Returning details of 1 User Learning Activity");
					}else if(joinPoint.getSignature().getName().equals("addUserLearningActivity")) {
						log.info("Adding one User Learning Activity");
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
