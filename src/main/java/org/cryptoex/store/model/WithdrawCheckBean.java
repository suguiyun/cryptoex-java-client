package org.cryptoex.store.model;

import org.cryptoex.ApiError;
import org.cryptoex.ApiException;

public class WithdrawCheckBean {

	/**
	 * Check result: OK or failed.
	 */
	public boolean success;

	/**
	 * If success, need review or not:
	 */
	public boolean needReview;

	/**
	 * Error code if denied.
	 */
	public String errorCode;

	/**
	 * Detailed message if denied.
	 */
	public String errorMessage;

	public void validate() {
		if (!success) {
			if (errorCode == null || errorCode.isEmpty()) {
				throw new ApiException(ApiError.PARAMETER_INVALID, "errorCode",
						"errorCode must be set when result is DENIED.");
			}
			if (errorMessage == null || errorMessage.isEmpty()) {
				throw new ApiException(ApiError.PARAMETER_INVALID, "errorMessage",
						"errorMessage must be set when result is DENIED.");
			}
		}
	}
}
