package org.cryptoex.enums;

/**
 * Withdraw status enumeration.
 * 
 * Transitions:
 * 
 * SUBMITTED -> CANCELLED;
 * 
 * SUBMITTED -> DENIED;
 * 
 * SUBMITTED -> WAITING_FOR_WALLET -> PROCESSING -> DONE;
 * 
 * SUBMITTED -> WAITING_FOR_WALLET -> PROCESSING -> FAILED;
 * 
 * SUBMITTED -> WAITING_FOR_APPROVAL -> CANCELLED;
 * 
 * SUBMITTED -> WAITING_FOR_APPROVAL -> DENIED;
 * 
 * SUBMITTED -> WAITING_FOR_APPROVAL -> WAITING_FOR_WALLET -> CANCELLED;
 * 
 * SUBMITTED -> WAITING_FOR_APPROVAL -> WAITING_FOR_WALLET -> PROCESSING -> DONE;
 * 
 * SUBMITTED -> WAITING_FOR_APPROVAL -> WAITING_FOR_WALLET -> PROCESSING -> FAILED;
 * 
 * @author _admin
 */
public enum WithdrawStatus {

	/**
	 * Withdraw request was submitted ok.
	 */
	SUBMITTED(true),

	/**
	 * Withdraw request was submitted ok and waiting for wallet to process.
	 */
	WAITING_FOR_WALLET(true),

	/**
	 * Withdraw request was submitted ok and waiting for approval.
	 */
	WAITING_FOR_APPROVAL(true),

	/**
	 * Withdraw request was approved.
	 */
	APPROVED(true),

	/**
	 * Withdraw request was denied.
	 */
	DENIED(false),

	/**
	 * Withdraw request is processing by wallet.
	 */
	PROCESSING(false),

	/**
	 * Withdraw request was cancelled by user.
	 */
	CANCELLED(false),

	/**
	 * Withdraw was done successfully.
	 */
	DONE(false),

	/**
	 * Withdraw failed.
	 */
	FAILED(false);

	private final boolean cancellable;

	private WithdrawStatus(boolean cancellable) {
		this.cancellable = cancellable;
	}

	public boolean isCancellable() {
		return this.cancellable;
	}
}
