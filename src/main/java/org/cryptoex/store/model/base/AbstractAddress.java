package org.cryptoex.store.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.cryptoex.store.AbstractReadOnlyEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Deposit address.
 * 
 * @author _admin
 */
@MappedSuperclass
public abstract class AbstractAddress extends AbstractReadOnlyEntity {

	@Column(nullable = false, updatable = false, length = VAR_ENUM)
	public String currency;

	@Column(nullable = false, updatable = false, length = VAR_CHAR_100)
	public String address;

	/**
	 * Store hex hash by HmacSHA256(currency+address, "secret-key"), or
	 * HmacSHA256(userId+currency+address, "secret-key").
	 */
	@JsonIgnore
	@Column(nullable = false, updatable = false, length = VAR_CHAR_100)
	public String hash;

	@Override
	public String toString() {
		String part = toPostfixString();
		if (part != null && !part.isEmpty()) {
			part = ", " + part;
		}
		return String.format("{%s: id=%s, currency=%s, address=%s, hash=%s%s}", getClass().getSimpleName(), this.id,
				this.currency, this.address, this.hash, part);
	}

	protected String toPostfixString() {
		return "";
	}

	/**
	 * Get address hash.
	 * 
	 * @param secret
	 * @return
	 */
	public abstract String getAddressHash(String secret);

	public abstract void checkAddressHash(String secret);
}
