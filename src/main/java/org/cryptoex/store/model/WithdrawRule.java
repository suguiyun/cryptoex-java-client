package org.cryptoex.store.model;

import org.cryptoex.store.AbstractReadOnlyEntity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Store withdraw rules.
 * 
 * @author _admin
 */
@Entity
@Table(name = "withdraw_rules", uniqueConstraints = @UniqueConstraint(name = "UNI_CURRENCY", columnNames = "currency"))
public class WithdrawRule extends AbstractReadOnlyEntity {

	/**
	 * Rule for currency.
	 */
	@Column(nullable = false, updatable = false, length = VAR_ENUM)
	public String currency;

	/**
	 * Is withdraw disabled for this currency?
	 */
	@Column(nullable = false, updatable = false)
	public boolean withdrawDisabled;

	/**
	 * Minimum amount to withdraw.
	 */
	@Column(nullable = false, updatable = false, precision = PRECISION, scale = SCALE)
	public BigDecimal minimumAmount;

	/**
	 * Rate of withdraw. e.g. 0.01 = 1%, fee(withdraw 1.2 btc) = 1.2 * 0.01 = 0.012
	 */
	@Column(nullable = false, updatable = false, precision = PRECISION, scale = SCALE)
	public BigDecimal feeRate;

	/**
	 * Minimum fee of withdraw. e.g. 0.002 btc.
	 * 
	 * actual fee = max(amount * fee, minimumFee).
	 */
	@Column(nullable = false, updatable = false, precision = PRECISION, scale = SCALE)
	public BigDecimal minimumFee;

	/**
	 * Maximum fee of withdraw. e.g. 0.02 btc.
	 * 
	 * actual fee = min(amount * fee, maximumFee).
	 */
	@Column(nullable = false, updatable = false, precision = PRECISION, scale = SCALE)
	public BigDecimal maximumFee;

	@Transient
	public BigDecimal getActualFee(BigDecimal amount) {
		BigDecimal fee = amount.multiply(this.feeRate);
		fee = fee.max(this.minimumFee);
		fee = fee.min(this.maximumFee);
		return fee;
	}

	@Override
	public String toString() {
		return String.format("{WithdrawRule: currency=%s, minimumAmount=%s, feeRate=%s, minimumFee=%s, maximumFee=%s}",
				this.currency, this.minimumAmount, this.feeRate, this.minimumFee, this.maximumFee);
	}
}
