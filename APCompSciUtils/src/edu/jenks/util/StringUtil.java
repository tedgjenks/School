package edu.jenks.util;

import java.util.Random;

/**
 * Performs operations on String objects.
 *
 * @author JenksT
 */
public class StringUtil {
	
	private static final Random RANDOM = new Random(System.currentTimeMillis());
	private static final byte MIN_ASCII = 0, MAX_ASCII = 127;
	private static final int TOTAL_ASCII_CHARS = MAX_ASCII - MIN_ASCII + 1;
	public static final byte FIRST_ASCII_DIGIT = 48, FIRST_ASCII_UPPER_ALPHA = 65, FIRST_ASCII_LOWER_ALPHA = 97;
	
	/**
	 * Builds a String with the same characters of a specified length.
	 * 
	 * @param character	the character used to build the String
	 * @param length	the length of the String
	 * @return 			the constructed String
	 */
	public static String buildString(char character, int length) {
		StringBuilder sb = new StringBuilder(length);
		for(int index = length - 1; index >= 0; index--)
			sb.append(character);
		return sb.toString();
	}
	
	/**
	 * @param numChars
	 * @param lowUnicode inclusive
	 * @param highUnicode inclusive
	 * @return
	 */
	public static String buildRandomString(int numChars, int lowUnicode, int highUnicode) {
		StringBuilder sb = new StringBuilder(numChars);
		for(int count = numChars; count > 0; count--)
			sb.append(getRandomCharacterIncludeRange(lowUnicode, highUnicode));
		return sb.toString();
	}
	
	public static char getRandomCharacterIncludeRange(int lowUnicode, int highUnicode) {
		final int diff = highUnicode - lowUnicode + 1;
		return Character.toChars((RANDOM.nextInt(diff) + lowUnicode))[0];
	}
	
	/**
	 * @param lowLegalAscii
	 * @param highLegalAscii
	 * @param lowAscii
	 * @param highAscii
	 * @param singleExclusions should not be between <code>lowAscii</code> and <code>highAscii</code> and should be ascending
	 * @return
	 */
	public static char getRandomAsciiCharacterExcludeRange(int lowLegalAscii, int highLegalAscii, int lowAscii, int highAscii, int... singleExclusions) {
		final int numExcluded = highAscii - lowAscii + 1;
		final int totalCharPool = (highLegalAscii - lowLegalAscii + 1) - numExcluded - singleExclusions.length;
		int randomChar = RANDOM.nextInt(totalCharPool) + lowLegalAscii;
		if(randomChar >= lowAscii)
			randomChar += numExcluded;
		boolean underSingleExclusion = true;
		for(int index = 0; underSingleExclusion && index < singleExclusions.length; index++) {
			if(randomChar >= singleExclusions[index]) {
				randomChar++;
				if(randomChar >= lowAscii)
					randomChar += numExcluded;
			} else
				underSingleExclusion = false;
		}
		return (char)randomChar;
	}
	
	public static char convertToUpperCase(char lowerChar) {
		return String.valueOf(lowerChar).toUpperCase().charAt(0);
	}
	
	public static char convertToLowerCase(char upperChar) {
		return String.valueOf(upperChar).toLowerCase().charAt(0);
	}
	
	public static String prependCharacter(String s, char c, int desiredLength) {
		StringBuilder sb = new StringBuilder(desiredLength);
		sb.append(s);
		for(int numChars = desiredLength - sb.length(); numChars > 0; numChars--)
			sb.insert(0, c);
		return sb.toString();
	}
	
	public static String prependCharacter(int i, char c, int desiredLength) {
		StringBuilder sb = new StringBuilder(desiredLength);
		sb.append(i);
		for(int numChars = desiredLength - sb.length(); numChars > 0; numChars--)
			sb.insert(0, c);
		return sb.toString();
	}
	
	public static boolean equalAllowNull(String s1, String s2) {
		return ComparisonUtil.equalAllowNull(s1, s2);
	}
}
