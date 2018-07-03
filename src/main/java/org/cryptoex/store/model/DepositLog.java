package org.cryptoex.store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.cryptoex.store.AbstractEntity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Store deposit logs.
 * 
 * @author _admin
 */
@Entity
@Table(name = "deposit_logs", uniqueConstraints = @UniqueConstraint(name = "UNI_UNIQUE_ID", columnNames = "uniqueId"), indexes = @Index(name = "IDX_CURRENCY", columnList = "currency"))
public class DepositLog extends AbstractEntity {

	/**
	 * Status for deposit:
	 * 
	 * PENDING -> DEPOSITED
	 * 
	 * PENDING -> DEPOSITED -> CANCELLED
	 * 
	 * PENDING -> WAITING_FOR_APPROVAL -> DEPOSITED
	 * 
	 * PENDING -> WAITING_FOR_APPROVAL -> DENIED
	 */
	public static enum DepositStatus {
		/**
		 * Waiting for more block confirm.
		 */
		PENDING,

		/**
		 * Already deposited.
		 */
		DEPOSITED,

		/**
		 * Already cancelled.
		 */
		CANCELLED,

		/**
		 * Waiting for approval.
		 */
		WAITING_FOR_APPROVAL,

		/**
		 * Denied by manager.
		 */
		DENIED;
	}

	@Column(nullable = false, length = VAR_ENUM)
	public DepositStatus status;

	@Column(nullable = false, updatable = false)
	public long userId;

	@Column(nullable = false, updatable = false, length = VAR_ENUM)
	public String currency;

	@Column(nullable = false, updatable = false, length = VAR_CHAR_100)
	public String toAddress;

	@Column(nullable = false, updatable = false, length = VAR_CHAR_200)
	public String uniqueId;

	@Column(nullable = false, updatable = false, precision = PRECISION, scale = SCALE)
	public BigDecimal amount;

	@Column(nullable = false)
	public int confirms;

	@Column(nullable = false)
	public int minimumConfirms;

	@JsonIgnore
	@Column(nullable = false)
	public int deposited;

	@JsonIgnore
	@Column(nullable = false)
	public int cancelled;

	@Override
	public String toString() {
		return String.format(
				"{DepositLog: status=%s, currency=%s, toAddress=%s, uniqueId=%s, amount=%s, confirms=%d/%d, deposited=%s, cancelled=%s}",
				this.status, this.currency, this.toAddress, this.uniqueId, this.amount, this.confirms,
				this.minimumConfirms, this.deposited, this.cancelled);
	}
}
