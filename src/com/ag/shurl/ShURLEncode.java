package com.ag.shurl;

/**
 * A class which is used to generate an unique index from an url, which can be reversed back to the original url.
 * @author A. Wallin
 * @date 8/2/15
 */

public class ShURLEncode {
	
	/**
	 * Constructs a new unique index using the original url and current cache size.
	 * @param urlToEncode, the url to create an unique index for.
	 * @param size, the current cache size.
	 * @return the newly constructed unique index for the parameter-specified url.
	 */
	public static String generateUniqueIndex(String urlToEncode, int size) {
		StringBuffer buffer = new StringBuffer("0x");
		buffer.append((int) (Math.abs((size * 100) - 50 * (size - 5))));
		buffer.append((int) (Math.floor(System.currentTimeMillis() / 1000)));
		buffer.append(size);
		return buffer.toString();
	}

}
