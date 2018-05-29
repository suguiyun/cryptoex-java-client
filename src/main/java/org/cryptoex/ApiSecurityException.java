package org.cryptoex;

/**
 * Indicate security alert.
 * 
 * @author cryptoex
 */
public class ApiSecurityException extends ApiException {

	public ApiSecurityException(ApiError error, String data, String message) {
		super(error, data, message);
	}

	public ApiSecurityException(ApiError error, String data) {
		super(error, data);
	}

	public ApiSecurityException(ApiError error) {
		super(error);
	}

	public ApiSecurityException(String error, String data, String message) {
		super(error, data, message);
	}

}
