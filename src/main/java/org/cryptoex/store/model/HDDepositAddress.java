package org.cryptoex.store.model;

import org.cryptoex.store.AbstractReadOnlyEntity;

import javax.persistence.*;

/**
 * HD Deposit address for user.
 * 
 * @author _admin
 */
@Entity
@Table(name = "hd_deposit_addresses", uniqueConstraints = {
		@UniqueConstraint(name = "UNI_IDX_CUR", columnNames = { "bipIndex", "currency" }),
		@UniqueConstraint(name = "UNI_USERID_CUR", columnNames = { "userId", "currency" }) })
public class HDDepositAddress extends AbstractReadOnlyEntity {

	@Column(nullable = false, updatable = false)
	public long userId;

	@Column(nullable = false, updatable = false)
	public int bipIndex;

	@Column(nullable = false, updatable = false, length = VAR_ENUM)
	public String currency;

	/**
	 * This field is NOT store in database. Just set and return to client.
	 */
	@Transient
	public String address;

	@Override
	public String toString() {
		return String.format("{HDDepositAddress: id=%s, userId=%s, currency=%s, bipIndex=%s}", this.id, this.userId,
				this.currency, this.bipIndex);
	}

}
