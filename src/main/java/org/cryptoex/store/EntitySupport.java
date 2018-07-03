package org.cryptoex.store;

import org.cryptoex.ApiError;
import org.cryptoex.ApiException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * Define entity support.
 * 
 * @author _admin
 */
public interface EntitySupport extends Serializable {

	// default big decimal storage type: DECIMAL(PRECISION, SCALE)
	static final int PRECISION = 32;
	static final int SCALE = 16;

	static final int VAR_ENUM = 32;
	static final int VAR_CHAR_50 = 50;
	static final int VAR_CHAR_100 = 100;
	static final int VAR_CHAR_200 = 200;
	static final int VAR_CHAR_1000 = 1000;

	static BigDecimal checkBigDecimalPositive(String name, BigDecimal value) {
		value = checkBigDecimal(value);
		if (value.signum() <= 0) {
			throw new ApiException(ApiError.PARAMETER_INVALID, name, "Value must be positive.");
		}
		return value;
	}

	static BigDecimal checkBigDecimal(BigDecimal value) {
		if (value.scale() > SCALE) {
			return value.setScale(SCALE, RoundingMode.DOWN);
		}
		return value;
	}
}
