package org.cryptoex.metrics;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface MetricWith {

	/**
	 * The metric name.
	 * 
	 * @return Metric name.
	 */
	String value();
}
