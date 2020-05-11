package edu.jenks.scrape.data.gpa;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import edu.jenks.scrape.util.SystemInfo;

public class GPAUtils {
	
	public static final String PS_STANDARD_COURSE_NUMBER_REGEX = "^\\d{6}[A-Z]{2}$";
	private static final Pattern PS_STANDARD_COURSE_NUMBER_PATTERN = Pattern.compile(PS_STANDARD_COURSE_NUMBER_REGEX);
	private static final Properties CURRENT_TERM_PROPS = new Properties();
	private static final Map<String, Boolean> CURRENT_TERM_CACHE = new HashMap<>(20);
	
	static {
		final String currentTermPropertiesPath = SystemInfo.INSTANCE.RESOURCES_PATH + "CurrentTerm.properties";
		try {
			CURRENT_TERM_PROPS.load(new FileInputStream(currentTermPropertiesPath));
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}
	
	public static float calcExpectedEarnedCredit(String courseNumber) {
		char earnedCreditCode = courseNumber.charAt(courseNumber.length() - 1);
		switch(earnedCreditCode) {
		case 'W': return 1;
		case 'H': return .5F;
		case 'D': return 2;
		case 'Q': return .25F;
		default: return 0;
		}
	}
	
	public static boolean isTranscriptCourse(String courseNumber) {
		return PS_STANDARD_COURSE_NUMBER_PATTERN.matcher(courseNumber).matches();
	}
	
	public static boolean isCurrentTerm(String term) {
		if(!CURRENT_TERM_CACHE.containsKey(term)) {
			final Set<String> currentTerms = new HashSet<>(6);
			currentTerms.add("YR");
			currentTerms.add(CURRENT_TERM_PROPS.getProperty("SEMESTER"));
			currentTerms.add(CURRENT_TERM_PROPS.getProperty("QUARTER"));
			final String currentYear = CURRENT_TERM_PROPS.getProperty("YEAR");
			if(currentYear.equals(term.substring(0, 5)) && currentTerms.contains(term.substring(6)))
				CURRENT_TERM_CACHE.put(term, true);
			else
				CURRENT_TERM_CACHE.put(term, false);
		}
		return CURRENT_TERM_CACHE.get(term);
	}

	public static float weightGpaWithYear(float grade, String courseNumber, String yearTerm) {
		float weightedGpa = 0;
		int hyphenIndex = yearTerm.indexOf('-');
		byte lastYear = Byte.parseByte(yearTerm.substring(hyphenIndex + 1, hyphenIndex + 3));
		boolean newScale = lastYear >= 17;
		if(newScale)
			weightedGpa = .1F + .1F * (grade - 51);
		else
			weightedGpa = .125F + .125F * (grade - 62);
		if(weightedGpa > 0) {
			if(courseNumber.indexOf("HW") >= 0 || courseNumber.indexOf("HH") >= 0 || courseNumber.indexOf("HD") >= 0) // honors
				weightedGpa += .5F;
			else if(courseNumber.indexOf("AW") >= 0 || courseNumber.indexOf("EW") >= 0) // AP or dual-credit
				weightedGpa += 1;
		} else
			weightedGpa = 0;
		return weightedGpa;
	}
}
