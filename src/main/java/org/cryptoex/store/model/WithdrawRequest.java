package org.cryptoex.store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.cryptoex.ApiError;
import org.cryptoex.ApiException;
import org.cryptoex.enums.WithdrawStatus;
import org.cryptoex.store.AbstractEntity;
import org.cryptoex.util.HashUtil;

import javax.persistence.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

/**
 * User's withdraw request.
 * 
 * @author _admin
 */
@Entity
@Table(name = "withdraw_requests", indexes = @Index(name = "IDX_USER_ID", columnList = "userId"))
public class WithdrawRequest extends AbstractEntity {

	/**
	 * Withdraw status.
	 */
	@Column(nullable = false, length = VAR_ENUM)
	public WithdrawStatus status;

	/**
	 * Withdraw error code if status = DENIED or FAILED. Default to "" (no error).
	 * 
	 * @see org.cryptoex.enums.WithdrawError
	 */
	@Column(nullable = false, length = VAR_ENUM)
	public String errorCode;

	/**
	 * Detailed error message for human if errorCode != "". Default to "" (no error
	 * message).
	 */
	@Column(nullable = false, length = VAR_CHAR_100)
	public String errorMessage;

	@Column(nullable = false, updatable = false, length = VAR_ENUM)
	public String currency;

	/**
	 * User id.
	 */
	@Column(nullable = false, updatable = false)
	public long userId;

	/**
	 * The to address. e.g. "1KXqoP2jUwcTe8uXdMhqYnYZb9P6w5mZJK".
	 */
	@Column(nullable = false, updatable = false, length = VAR_CHAR_200)
	public String toAddress;

	/**
	 * The amount to withdraw.
	 */
	@Column(nullable = false, updatable = false, precision = PRECISION, scale = SCALE)
	public BigDecimal amount;

	/**
	 * The fee given to miner of this withdraw request. Set by rule.
	 */
	@Column(nullable = false, updatable = false, precision = PRECISION, scale = SCALE)
	public BigDecimal fee;

	/**
	 * The hash = HmacSHA256(userId + currency + toAddress, secret)
	 */
	@JsonIgnore
	@Column(nullable = false, updatable = false, length = VAR_CHAR_100)
	public String hash;

	/**
	 * The tx hash. e.g.
	 * "1d678f9dc967ec84913e4e0b8779dc404ad19309e09fc57df0aa37f3f63ec239".
	 */
	@Column(nullable = false, length = VAR_CHAR_200)
	public String tx;

	/**
	 * Is this withdraw request can be cancelled?
	 * 
	 * @return
	 */
	@Transient
	public boolean isCancellable() {
		return this.status.isCancellable();
	}

	public void checkAddressHash(String secret) {
		if (!getAddressHash(secret).equals(this.hash)) {
			throw new ApiException(ApiError.ADDRESS_INVALID, "toAddress", "hash check failed.");
		}
	}

	public String getAddressHash(String secret) {
		String data = userId + currency + toAddress;
		return HashUtil.hmacSha256(data.getBytes(StandardCharsets.UTF_8), secret.getBytes(StandardCharsets.UTF_8));
	}

}

