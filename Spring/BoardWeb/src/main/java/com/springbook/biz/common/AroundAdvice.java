package com.springbook.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;

public class AroundAdvice {

	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		
		System.out.println("[BEFORE] : 비즈니스 메서드 수행 전에 처리할 내용...............");

		Object returnObj = pjp.proceed();
		
		System.out.println("[BEFORE] : 비즈니스 메서드 수행 후후후후에 처리할 내용...............");
		
				
		return returnObj;
		
	}
	
	
}
