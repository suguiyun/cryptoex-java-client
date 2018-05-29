package org.cryptoex.enums;

/**
 * Withdraw error code.
 * 
 * @author _admin
 */
public enum WithdrawError {

	/**
	 * Do now approved.
	 */
	NOT_APPROVED,

	/**
	 * Network is temporally unavailable.
	 */
	NETWORK_ERROR,

	/**
	 * Hot wallet was exhausted.
	 */
	HOT_WALLET_EXHAUSTED,

	/**
	 * Server internal error.
	 */
	INTERNAL_ERROR;
}
