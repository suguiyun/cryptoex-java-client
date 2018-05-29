package org.cryptoex.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.codahale.metrics.Slf4jReporter;
import com.codahale.metrics.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Collect metrics.
 * 
 * @author _admin
 */
public final class Metrics {

	static final Logger logger = LoggerFactory.getLogger(Metrics.class);

	static final MetricRegistry registry;
	static final ScheduledReporter reporter;

	static {
		logger.info("init metric registry...");
		registry = new MetricRegistry();
		logger.info("init metric reporter...");
		reporter = Slf4jReporter.forRegistry(registry).outputTo(logger).convertRatesTo(TimeUnit.SECONDS)
				.convertDurationsTo(TimeUnit.MILLISECONDS).build();
		reporter.start(1, TimeUnit.MINUTES);
	}

	static Map<String, Timer> timers = new ConcurrentHashMap<>();

	public static TimerMetric measureTime(String name) {
		Timer timer = timers.get(name);
		if (timer == null) {
			timer = registry.timer(name);
			timers.put(name, timer);
		}
		return new TimerMetricsImpl(timer.time());
	}
}

class TimerMetricsImpl implements TimerMetric {

	final Timer.Context context;

	TimerMetricsImpl(Timer.Context context) {
		this.context = context;
	}

	@Override
	public void close() {
		this.context.stop();
	}

}
