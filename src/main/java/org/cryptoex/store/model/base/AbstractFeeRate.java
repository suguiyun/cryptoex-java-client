package org.cryptoex.store.model.base;

import org.cryptoex.store.AbstractReadOnlyEntity;
import org.cryptoex.util.DateTimeUtil;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

/**
 * Abstract transaction fee.
 * 
 * @author _admin
 */
@MappedSuperclass
public abstract class AbstractFeeRate extends AbstractReadOnlyEntity {

	/**
	 * Start timestamp (inclusive).
	 */
	@Column(nullable = false, updatable = false)
	public long startTime;

	/**
	 * The taker fee rate as 0.001 = 0.1%
	 */
	@Column(nullable = false, updatable = false, precision = PRECISION, scale = SCALE)
	public BigDecimal takerFeeRate;

	/**
	 * The maker fee rate as 0.001 = 0.1%
	 */
	@Column(nullable = false, updatable = false, precision = PRECISION, scale = SCALE)
	public BigDecimal makerFeeRate;

	@Override
	public String toString() {
		return String.format("{%s %s, taker=%s%%, maker=%s%%, startTime=%s}", getClass().getSimpleName(), toSubString(),
				takerFeeRate.multiply(BD_100), makerFeeRate.multiply(BD_100),
				DateTimeUtil.timestampToString(startTime));
	}

	protected abstract String toSubString();

	private static final BigDecimal BD_100 = BigDecimal.valueOf(100);

}
