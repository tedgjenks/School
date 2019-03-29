package edu.jenks.scrape.util;

public class Students {
	public static final String LAST_FIRST_NAME_DELIMITER = ",";
	public static final int LAST_FIRST_NAME_DELIMITER_LENGTH = LAST_FIRST_NAME_DELIMITER.length();
	
	/**
	 * @param lastFirstName format lastname, firstname middlename
	 * @return format firstname middlename lastname
	 */
	public static String lastNameFirstToFirstNameFirst(String lastFirstName) {
		int commaIndex = lastFirstName.indexOf(LAST_FIRST_NAME_DELIMITER);
		if(commaIndex >= 0) {
			return new StringBuilder(lastFirstName.length()).append(lastFirstName.substring(commaIndex + LAST_FIRST_NAME_DELIMITER_LENGTH).trim())
					.append(" ").append(lastFirstName.substring(0, commaIndex).trim()).toString();
		} else
			return lastFirstName;
	}

}
