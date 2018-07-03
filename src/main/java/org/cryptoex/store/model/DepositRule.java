package org.cryptoex.store.model;

import org.cryptoex.store.AbstractReadOnlyEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Store deposit rules.
 * 
 * @author _admin
 */
@Entity
@Table(name = "deposit_rules", indexes = @Index(name = "IDX_CURRENCY", columnList = "currency"))
public class DepositRule extends AbstractReadOnlyEntity {

	@Column(nullable = false, updatable = false, length = VAR_ENUM)
	public String currency;

	@Column(nullable = false, updatable = false, precision = PRECISION, scale = SCALE)
	public BigDecimal amount;

	@Column(nullable = false, updatable = false)
	public int confirms;

	@Override
	public String toString() {
		return String.format("{DepositRule: currency=%s, amount=%s, confirms=%d}", this.currency, this.amount,
				this.confirms);
	}
}
