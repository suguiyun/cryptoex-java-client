package org.cryptoex.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Date time util.
 * 
 * @author _admin
 */
public class DateTimeUtil {

	/**
	 * Convert timestamp to local date time as string.
	 * 
	 * @param ts
	 *            Timestamp in milliseconds.
	 * @return String like "2017-09-22 18:12:46.912"
	 */
	public static String timestampToString(long ts) {
		ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(ts), DEFAULT_ZOND_ID);
		return zdt.toLocalDateTime().format(DT_FORMATTER);
	}

	static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SSS", Locale.US);

	static final ZoneId DEFAULT_ZOND_ID = ZoneId.systemDefault();
}
