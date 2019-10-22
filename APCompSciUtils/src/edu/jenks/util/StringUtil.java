package edu.jenks.util;

import java.util.Random;

/**
 * Performs operations on String objects.
 *
 * @author JenksT
 */
public class StringUtil {
	
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
		Random random = new Random(System.currentTimeMillis());
		final int diff = highUnicode - lowUnicode + 1;
		for(int count = numChars; count > 0; count--) {
			char c = Character.toChars((random.nextInt(diff) + lowUnicode))[0];
			sb.append(c);
		}
		return sb.toString();
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
