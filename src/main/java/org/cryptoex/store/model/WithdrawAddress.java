package org.cryptoex.store.model;

import org.cryptoex.ApiError;
import org.cryptoex.ApiException;
import org.cryptoex.store.model.base.AbstractAddress;
import org.cryptoex.util.HashUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

/**
 * Withdraw address.
 * 
 * @author _admin
 */
@Entity
@Table(name = "withdraw_addresses", uniqueConstraints = @UniqueConstraint(name = "UNI_ADDR_CUR_USERID", columnNames = {
		"address", "currency", "userId" }))
public class WithdrawAddress extends AbstractAddress {

	@Column(nullable = false, updatable = false)
	public long userId;

	@Column(nullable = false, updatable = false, length = VAR_CHAR_100)
	public String description;

	@Override
	protected String toPostfixString() {
		return "userId=" + this.userId;
	}

	@Override
	public void checkAddressHash(String secret) {
		if (!getAddressHash(secret).equals(this.hash)) {
			throw new ApiException(ApiError.ADDRESS_INVALID, "address", "hash check failed.");
		}
	}

	@Override
	public String getAddressHash(String secret) {
		String data = userId + currency + address;
		return HashUtil.hmacSha256(data.getBytes(StandardCharsets.UTF_8), secret.getBytes(StandardCharsets.UTF_8));
	}

}
