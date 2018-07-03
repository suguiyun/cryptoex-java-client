package org.cryptoex.store.model;

import org.cryptoex.ApiError;
import org.cryptoex.ApiException;

public class WithdrawReviewBean {
	/**
	 * Review result.
	 */
	public boolean approved;

	/**
	 * Error code if not approved.
	 */
	public String errorCode;

	/**
	 * Detailed message if not approved.
	 */
	public String errorMessage;

	public void validate() {
		if (!approved) {
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
