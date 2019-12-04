package com.dopaming.www.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service //bean등록 (Component라고 해도 무방)
@Aspect //aop설정
public class BeforeAdvice { //Advice 클래스
	
	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))") //pointcut 설정
	public void allpointcut() {} //pointcut 이름 지정
	
	@Before("allpointcut()") //pointcut 연결 및 시점지정
	public void beforeLog(JoinPoint jp) { //JoinPoint -> 실행되는 모든 서블릿을 받을 수 있음
		String methodName = jp.getSignature().getName(); 
		Object[] args = jp.getArgs();  //서블릿 안의 요소들을 Object타입의 변수로 받음 
		System.out.println("[사전처리] " + methodName + "비즈니스 로직 수행 전 동작" + ((args!=null && args.length>0) ? args[0]: ""));
	}
}
