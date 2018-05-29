package org.cryptoex.service;

public interface RunnableResource {

	/**
	 * Start this resource.
	 */
	void start();

	/**
	 * Safely close resource.
	 */
	void shutdown();
}
