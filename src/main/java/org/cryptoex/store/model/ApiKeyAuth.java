package org.cryptoex.store.model;

import org.cryptoex.store.AbstractEntity;
import org.cryptoex.util.IpUtil;

import javax.persistence.*;

@Entity
@Table(name = "api_key_auths", uniqueConstraints = @UniqueConstraint(name = "UNI_API_KEY", columnNames = {
		"apiKey" }), indexes = @Index(name = "IDX_USERID", columnList = "userId"))
public class ApiKeyAuth extends AbstractEntity {

	@Column(nullable = false, updatable = false)
	public long userId;

	/**
	 * API key is composed by:
	 * 
	 * encoded-user-id (8 chars), checksum (8 chars), random-part (16 chars).
	 * 
	 * So we can extract user id from api key.
	 */
	@Column(nullable = false, updatable = false, length = VAR_CHAR_50)
	public String apiKey;

	@Column(nullable = false, updatable = false, length = VAR_CHAR_50)
	public String apiSecret;

	@Column(nullable = false, updatable = false, length = VAR_CHAR_50)
	public String description;

	/**
	 * IP address allowed, such as 12.34.56.0 (using int form). Default to 0.
	 */
	@Column(nullable = false, updatable = false)
	public int netAddress;

	/**
	 * Net mask, such as 255.255.255.0 (using int form). Default to 0.
	 */
	@Column(nullable = false, updatable = false)
	public int netmask;

	@Column(nullable = false, updatable = false)
	public boolean canTrade;

	@Column(nullable = false, updatable = false)
	public boolean canWithdraw;

	@Column(nullable = false)
	public boolean enabled;

	/**
	 * IP address allowed, such as 12.34.56.0/24. Default to empty "".
	 */
	public void setIpRestriction(String ipRestriction) {
		int[] ns = IpUtil.parseIpRestriction(ipRestriction);
		this.netAddress = ns[0];
		this.netmask = ns[1];
	}

	@Transient
	public String getIpRestriction() {
		return IpUtil.getIPRestriction(this.netAddress, this.netmask);
	}
}
