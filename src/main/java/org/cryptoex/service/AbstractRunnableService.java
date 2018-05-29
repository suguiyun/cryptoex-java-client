package org.cryptoex.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Base class for service interface that contains a running thread.
 * 
 * @author _admin
 */
public abstract class AbstractRunnableService extends AbstractService implements RunnableResource {

	private Thread processThread = null;
	private volatile boolean running;

	protected boolean isRunning() {
		return running;
	}

	/**
	 * Do init job.
	 */
	protected void init() throws Exception {
	}

	/**
	 * Process in a background thread.
	 * 
	 * @throws InterruptedException
	 */
	protected abstract void process() throws InterruptedException;

	/**
	 * Do clean job when background thread is about to end.
	 * 
	 * @throws InterruptedException
	 */
	protected abstract void clean() throws InterruptedException;

	/**
	 * Start new background thread to process job.
	 */
	@Override
	@PostConstruct
	public final synchronized void start() {
		if (processThread != null) {
			throw new IllegalStateException("Cannot re-invoke start()");
		}
		logger.info(getClass().getSimpleName() + " init...");
		try {
			init();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		logger.info("starting background thread in " + getClass().getName() + "...");
		this.running = true;
		processThread = new Thread() {
			public void run() {
				try {
					process();
				} catch (InterruptedException e) {
					logger.info("Interrupted background service.");
				}
				try {
					clean();
				} catch (InterruptedException e) {
					logger.warn("clean was interrupted.", e);
				}
			}
		};
		processThread.start();
		while (!processThread.isAlive()) {
		}
		logger.info("background thread was started ok in " + getClass().getName() + ".");
	}

	/**
	 * Shutdown background thread.
	 */
	@Override
	@PreDestroy
	public final synchronized void shutdown() {
		if (processThread == null) {
			throw new IllegalStateException("Cannot re-invoke shutdown()");
		}
		logger.info("shutting down background thread in " + getClass().getName() + "...");
		this.running = false;
		try {
			processThread.interrupt();
			processThread.join();
			logger.info("background thread was shutdown ok in " + getClass().getName() + ".");
		} catch (InterruptedException e) {
			logger.error("error when shut down background thread in " + getClass().getName(), e);
		}
		processThread = null;
	}
}
