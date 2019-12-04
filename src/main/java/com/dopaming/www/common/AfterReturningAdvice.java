package com.dopaming.www.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterReturningAdvice {
	
	@AfterReturning(pointcut="BeforeAdvice.allpointcut()", returning="returnObj") //return값이 있으므로 함께 지정해주어야 함
	public void afterLog(JoinPoint jp, Object returnObj) {
		String methodName = jp.getSignature().getName();
		System.out.println("[사후 처리]" + methodName + " : " + (returnObj != null? returnObj : ""));
	}
}
