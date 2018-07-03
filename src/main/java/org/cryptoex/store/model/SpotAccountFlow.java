package org.cryptoex.store.model;

import org.cryptoex.enums.FlowType;
import org.cryptoex.store.AbstractReadOnlyEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Store account flows.
 * 
 * @author _admin
 */
@Entity
@Table(name = "spot_account_flows")
public class SpotAccountFlow extends AbstractReadOnlyEntity {

	@Column(nullable = false, updatable = false, length = VAR_ENUM)
	public FlowType flowType;

	@Column(nullable = false, updatable = false)
	public long fromUserId;

	@Column(nullable = false, updatable = false)
	public long fromAccountId;

	@Column(nullable = false, updatable = false)
	public long toUserId;

	@Column(nullable = false, updatable = false)
	public long toAccountId;

	@Column(nullable = false, updatable = false, length = VAR_ENUM)
	public String currency;

	@Column(nullable = false, updatable = false, precision = PRECISION, scale = SCALE)
	public BigDecimal amount;

	@Column(nullable = false, updatable = false, length = VAR_CHAR_200)
	public String description;

}
