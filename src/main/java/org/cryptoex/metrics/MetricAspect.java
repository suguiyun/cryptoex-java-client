package org.cryptoex.metrics;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MetricAspect {

	@Around("@annotation(metricWith)")
	public Object routingWithDataSource(ProceedingJoinPoint joinPoint, MetricWith metricWith) throws Throwable {
		String name = metricWith.value();
		try (TimerMetric m = Metrics.measureTime(name)) {
			return joinPoint.proceed();
		}
	}
}
