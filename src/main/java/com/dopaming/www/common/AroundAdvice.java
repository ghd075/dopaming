package com.dopaming.www.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class AroundAdvice {
		
	@Around("BeforeAdvice.allpointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable { // around advice의 경우 return값이 존재 해야 함으로 Object 타입 선언
		String methodName = pjp.getSignature().getName();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object obj = pjp.proceed();
		
		stopWatch.stop();
		
		System.out.println("[around]" + methodName + " : " + stopWatch.getTotalTimeMillis());
		
		
		//System.out.println("[around:before] 비즈니스 메서드 수행 전 처리할 내용");		
		//Object obj = pjp.proceed(); //포인트 컷(서비스 메서드)호출 -> JoinPoint: 호출된 서비스 매서드 정보를 담고있음		
		//System.out.println("[around:after] 비즈니스 메서드 수행 후 처리할 내용");
		return obj;
	}
}
