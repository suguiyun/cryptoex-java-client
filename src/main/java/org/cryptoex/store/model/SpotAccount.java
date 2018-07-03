package org.cryptoex.store.model;

import org.cryptoex.enums.AccountType;
import org.cryptoex.store.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;

/**
 * Spot account.
 * 
 * @author _admin
 */
@Entity
@Table(name = "spot_accounts", uniqueConstraints = @UniqueConstraint(name = "UNI_USERID_CURRENCY_TYPE", columnNames = {
		"userId", "currency", "type" }))
public class SpotAccount extends AbstractEntity {

	@Column(nullable = false, updatable = false)
	public long userId;

	@Column(nullable = false, updatable = false, length = VAR_ENUM)
	public String currency;

	@Column(nullable = false, updatable = false, length = VAR_ENUM)
	public AccountType type;

	@Column(nullable = false, precision = PRECISION, scale = SCALE)
	public BigDecimal balance;

	@Override
	public String toString() {
		return String.format("{SpotAccount: id=%s, userId=%s, currency=%s, type=%s, balance=%s}", this.id, this.userId,
				this.currency, this.type.name(), String.valueOf(this.balance));
	}

}
