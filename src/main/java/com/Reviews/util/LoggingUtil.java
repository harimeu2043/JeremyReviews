package com.Reviews.util;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.Reviews.models.MovieDTO;

@Aspect
@Component
public class LoggingUtil {
	
	Log logger = LogFactory.getLog(LoggingUtil.class);
	
	@Before(value = "execution(* com.Reviews.service.MovieServiceImpl2.*(..))")
	public void beforeService() {
		
		logger.trace("called aservice method");
		
	}
	
	@AfterThrowing(pointcut = "execution(* com.Reviews.service.*Impl2.*(..))", throwing = "exception")
	public void afterServiceThrows(Exception exception) {
		logger.warn(exception);
	}
	
	@AfterReturning(value = "execution(java.util.List<com.Reviews.models.MovieDTO> com.Reviews.repo.MovieRepo*.*(..))", returning="myList")
	public void afterRepoReturns(List<MovieDTO> myList) {
		logger.info(myList.toString());
	}
	
	@After(value = "execution(* com.Reviews.service.MovieServiceImpl2.*(..))")
	public void afterService() {
		logger.trace("exited a service method");
	}
}
