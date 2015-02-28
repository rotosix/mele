package com.mele.test;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
//@Component
public class BenchMark {
	private static final Logger log = LoggerFactory.getLogger(BenchMark.class);

	@Pointcut("execution(* com.mele.*.*.*(..))")
	public void detectAction() {
	}

	@Pointcut("execution(* com.mele.*.*.*.*(..))")
	public void detectAction2() {
	}

	@Pointcut("execution(* com.mele.*.*.*.*.*(..))")
	public void detectAction3() {
	}

	@Pointcut("execution(* (com.mele.rmi.IIndexService+||"+"com.mele.rmi.ISearchService).*(..))")
	public void detectRMI() {
	}

	/**
	 * 
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	@Around("detectAction()||detectAction2()||detectAction3()||detectRMI()")
	public Object detect(ProceedingJoinPoint point) throws Throwable {

		MethodSignature methodSignature = (MethodSignature) point.getSignature();
		Method method = methodSignature.getMethod();
		String methodName = method.getName();
		String className = method.getDeclaringClass().getCanonicalName();

		long startTime = System.currentTimeMillis();
		Object value = point.proceed();

		if (!"getQueueBadge".equals(methodName) && !"getQueueRegistry".equals(methodName) && !"getQueueFeed".equals(methodName)) {
			try {
				long currentTime = System.currentTimeMillis();
				log.info(className + ":" + methodName + " time:" + (currentTime - startTime) + "ms");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return value;
	}
}
