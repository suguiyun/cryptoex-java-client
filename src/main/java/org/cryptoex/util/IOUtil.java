package org.cryptoex.util;

import java.io.*;

public class IOUtil {

	/**
	 * Copy all bytes from one input stream to another output stream.
	 * 
	 * @param from
	 *            Source input.
	 * @param to
	 *            Target output.
	 * @throws IOException
	 *             If IO error.
	 */
	public static void copy(InputStream from, OutputStream to) throws IOException {
		byte[] buffer = new byte[1024];
		for (;;) {
			int n = from.read(buffer);
			if (n <= 0) {
				break;
			}
			to.write(buffer, 0, n);
		}
		to.flush();
	}

	/**
	 * Copy all characters from one reader to another writer.
	 * 
	 * @param from
	 *            Source reader.
	 * @param to
	 *            Target writer.
	 * @throws IOException
	 *             If IO error.
	 */
	public static void copy(Reader from, Writer to) throws IOException {
		char[] buffer = new char[1024];
		for (;;) {
			int n = from.read(buffer);
			if (n <= 0) {
				break;
			}
			to.write(buffer, 0, n);
		}
		to.flush();
	}

}
