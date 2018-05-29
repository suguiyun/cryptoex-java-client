package org.cryptoex.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CryptoLogger {

	static Logger txLogger = LoggerFactory.getLogger("crypto.log.transaction");

	static Logger businessLogger = LoggerFactory.getLogger("crypto.log.business");

	public static Logger getTxLogger() {
		return txLogger;
	}

	public static Logger getBusinessLogger() {
		return businessLogger;
	}
}
