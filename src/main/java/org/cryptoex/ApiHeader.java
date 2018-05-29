package org.cryptoex;

/**
 * API header definition.
 * 
 * @author _admin
 */
public class ApiHeader {

	public static final String HEADER_API_KEY = "API-KEY";
	public static final String HEADER_API_SIGNATURE_METHOD = "API-SIGNATURE-METHOD";
	public static final String HEADER_API_SIGNATURE_VERSION = "API-SIGNATURE-VERSION";
	public static final String HEADER_API_SIGNATURE = "API-SIGNATURE";
	public static final String HEADER_API_TIMESTAMP = "API-TIMESTAMP";
	public static final String HEADER_API_UNIQUE_ID = "API-UNIQUE-ID";

	public static final String API_SIGNATURE_VERSION_1 = "1";
	public static final String API_SIGNATURE_METHOD_HMAC_SHA256 = "HmacSHA256";

	public final String apiKey;
	public final String apiSignature;
	public final String apiUniqueId;
	public final String payload;

	public ApiHeader(String apiKey, String apiSignature, String apiUniqueId, String payload) {
		this.apiKey = apiKey;
		this.apiSignature = apiSignature;
		this.apiUniqueId = apiUniqueId;
		this.payload = payload;
	}
}
