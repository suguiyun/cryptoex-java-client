package org.cryptoex.util;

import org.cryptoex.ApiError;
import org.cryptoex.ApiException;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * Utils for generate, verify API key.
 * 
 * @author _admin
 */
public class ApiKeyUtil {

	public static String generateApiKey(long userId) {
		String uid = encodeUserId(userId);
		String rnd = RandomUtil.createRandomString(RandomUtil.WORDS, 16);
		byte[] sha1 = HashUtil.sha1AsBytes((uid + rnd + RANDOM_FOR_HASH).getBytes(StandardCharsets.UTF_8));
		// take first 6 bytes as checksum:
		String checksum = Base64.getUrlEncoder().encodeToString(Arrays.copyOfRange(sha1, 0, 6));
		return uid + checksum + rnd;
	}

	public static long extractUserIdFromApiKey(String apiKey) {
		if (apiKey.length() != 32) {
			throw new ApiException(ApiError.AUTH_APIKEY_INVALID, "Invalid API key.");
		}
		String uid = apiKey.substring(0, 8);
		String checksum = apiKey.substring(8, 16);
		String rnd = apiKey.substring(16);
		// verify checksum:
		byte[] sha1 = HashUtil.sha1AsBytes((uid + rnd + RANDOM_FOR_HASH).getBytes(StandardCharsets.UTF_8));
		// take first 6 bytes as checksum:
		String expectedChecksum = Base64.getUrlEncoder().encodeToString(Arrays.copyOfRange(sha1, 0, 6));
		if (!checksum.equals(expectedChecksum)) {
			throw new ApiException(ApiError.AUTH_APIKEY_INVALID, "Invalid API key.");
		}
		return decodeUserId(uid);
	}

	public static String generateApiSecret() {
		return RandomUtil.createRandomString(RandomUtil.WORDS, 20);
	}

	/**
	 * Encode userId to fixed 8-chars string.
	 * 
	 * @param userId
	 *            User id.
	 * @return
	 */
	public static String encodeUserId(long userId) {
		return Base64.getUrlEncoder().encodeToString(longTo6Bytes(userId));
	}

	/**
	 * Decode fixed 8-chars string to userId.
	 * 
	 * @param str
	 *            Encoded string.
	 * @return User id.
	 */
	public static long decodeUserId(String str) {
		byte[] userIdBytes = Base64.getUrlDecoder().decode(str);
		return longFrom6Bytes(userIdBytes);
	}

	static byte[] longTo6Bytes(long value) {
		byte[] result = new byte[6];
		for (int i = 5; i >= 0; i--) {
			result[i] = (byte) (value & 0xffL);
			value >>= 8;
		}
		return result;
	}

	static long longFrom6Bytes(byte[] bytes) {
		return (bytes[0] & 0xFFL) << 40 | (bytes[1] & 0xFFL) << 32 | (bytes[2] & 0xFFL) << 24 | (bytes[3] & 0xFFL) << 16
				| (bytes[4] & 0xFFL) << 8 | (bytes[5] & 0xFFL);
	}

	// IMPORTANT: DO NOT CHANGE:
	private static final String RANDOM_FOR_HASH = HashUtil.sha1("ForCryptoCurrency".getBytes(StandardCharsets.UTF_8));

}
