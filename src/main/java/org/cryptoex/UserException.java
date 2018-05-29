package org.cryptoex;

/**
 * Base exception for web user.
 * 
 * @author _admin
 */
public class UserException extends RuntimeException {

	public UserException() {
		super();
	}

	public UserException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserException(String message) {
		super(message);
	}

	public UserException(Throwable cause) {
		super(cause);
	}

}
