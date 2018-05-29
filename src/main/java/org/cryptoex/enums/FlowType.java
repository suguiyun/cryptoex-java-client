package org.cryptoex.enums;

public enum FlowType {

	/**
	 * A freeze operation in accounts of same user.
	 */
	TRADE_FREEZE,

	/**
	 * An unfreeze operation in accounts of same user.
	 */
	TRADE_UNFREEZE,

	/**
	 * clearing after buy or sell between user accounts.
	 */
	TRADE_CLEAR,

	/**
	 * A fee operation: user account -> system trade fee account.
	 */
	TRADE_FEE,

	/**
	 * A fee operation: user account -> system withdraw fee account.
	 */
	WITHDRAW_FEE,

	/**
	 * A fee operation: system asset account -> debt account. This fee is paid by
	 * system for user's fee is less than actual fee.
	 */
	WITHDRAW_FEE_COST,

	/**
	 * A fee operation: system withdraw fee account -> debt account. This fee is
	 * paid to miner and thus the asset and debt decreased.
	 */
	WITHDRAW_COST,

	/**
	 * Freeze operation for withdraw request.
	 */
	WITHDRAW_FREEZE,

	/**
	 * Unfreeze operation for withdraw was cancelled.
	 */
	WITHDRAW_UNFREEZE,

	/**
	 * A transfer operation: one user account -> another user account.
	 */
	TRANSFER_BETWEEN,

	/**
	 * A deposit operation: system asset account -> user account.
	 */
	TRANSFER_DEPOSIT,

	/**
	 * An undeposit operation: user account -> system account.
	 */
	TRANSFER_UNDEPOSIT,

	/**
	 * A withdraw operation: user account -> system asset account.
	 */
	TRANSFER_WITHDRAW,

	/**
	 * A transfer operation for refund: system account -> user account.
	 */
	TRANSFER_REFUND,

	/**
	 * Debt -> asset account.
	 */
	ASSET_INCREASE,

	/**
	 * Asset -> debt account.
	 */
	ASSET_DECREASE,

	/**
	 * Asset -> user account.
	 */
	ASSET_REWARD,

	/**
	 * Freeze operation for custom-type.
	 */
	CUSTOM_FREEZE,

	/**
	 * Unfreeze operation for custom-type.
	 */
	CUSTOM_UNFREEZE,

	/**
	 * Operation for fix.
	 */
	FIX;

}
