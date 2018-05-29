package org.cryptoex;

import org.cryptoex.util.ApiKeyUtil;
import org.cryptoex.util.ByteUtil;
import org.cryptoex.util.DateTimeUtil;
import org.cryptoex.util.HashUtil;

import java.nio.charset.StandardCharsets;

/**
 * A token represent an authenticated user.
 * 
 * @author _admin
 */
public class Token {

	private final static byte NONE = 0x00;
	private final static byte CAN_TRADE = 0x01;
	private final static byte CAN_WITHDRAW = 0x02;

	public final long userId;
	public final boolean canTrade;
	public final boolean canWithdraw;
	public final long expiresAt;
	public final Source source;

	public Token(long userId, boolean canTrade, boolean canWithdraw, long expiresAt, Source source) {
		this.userId = userId;
		this.canTrade = canTrade;
		this.canWithdraw = canWithdraw;
		this.expiresAt = expiresAt;
		this.source = source;
	}

	public static Token decrypt(String str, String secret, String signKey) {
		if (str.length() != 93) {
			throw new ApiException(ApiError.AUTH_AUTHORIZATION_INVALID);
		}
		String prefix = str.substring(0, 29);
		String actualHash = str.substring(29);
		// verify:
		String expectedHash = HashUtil.hmacSha256((prefix + secret).getBytes(StandardCharsets.UTF_8), signKey);
		if (!expectedHash.equals(actualHash)) {
			throw new ApiException(ApiError.AUTH_AUTHORIZATION_INVALID);
		}
		long expires = Long.parseLong(str.substring(10, 26), 16);
		if (System.currentTimeMillis() > expires) {
			throw new ApiException(ApiError.AUTH_AUTHORIZATION_EXPIRED);
		}
		final byte permission = ByteUtil.fromHex(str.substring(8, 10));
		return new Token(ApiKeyUtil.decodeUserId(str.substring(0, 8)), (permission & CAN_TRADE) == CAN_TRADE,
				(permission & CAN_WITHDRAW) == CAN_WITHDRAW, expires, Source.valueOf(str.substring(26, 29)));
	}

	public String encrypt(String secret, String signKey) {
		String str = prepareStringToSign();
		String hash = HashUtil.hmacSha256((str + secret).getBytes(StandardCharsets.UTF_8), signKey);
		return str + hash;
	}

	String prepareStringToSign() {
		// 16 chars hex string:
		String expires = String.format("%016x", this.expiresAt);
		StringBuilder sb = new StringBuilder(29);
		byte permission = (byte) ((this.canTrade ? CAN_TRADE : NONE) | (this.canWithdraw ? CAN_WITHDRAW : NONE));
		sb.append(ApiKeyUtil.encodeUserId(userId)).append(ByteUtil.toHex(permission)).append(expires).append(source);
		return sb.toString();
	}

	@Override
	public String toString() {
		return String.format("{Token: userId=%s, canTrade=%s, canWithdraw=%s, expiresAt=%s, source=%s}", this.userId,
				this.canTrade, this.canWithdraw, DateTimeUtil.timestampToString(expiresAt), source);
	}
}
