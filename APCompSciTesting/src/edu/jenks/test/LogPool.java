package edu.jenks.test;

import java.util.Random;
public class LogPool {
	
	private static final Random RANDOM = new Random(System.currentTimeMillis());

	public static String getSomePointsMessage(double finalPoints, double pointsAvailable, double pointsAvailableAfterPenalty) {
		StringBuilder sb = new StringBuilder(100);
		boolean funnyMessageAdded = false;
		if(finalPoints == 0) {
			sb.append("KABOOM!  Your object(s) exploded!  Check your constructors, normie!");
			funnyMessageAdded = true;
		} else if(finalPoints / pointsAvailable <= .5) {
			funnyMessageAdded = true;
			sb.append("Congratulations - here is your participation trophy!");
		}
		if(pointsAvailableAfterPenalty > 0) {
			final double funnyMessageChance = .25;
			if(sb.length() > 0)
				sb.append(System.lineSeparator());
			sb.append("You can earn another ").append(pointsAvailableAfterPenalty).append(" points.");
			if(!funnyMessageAdded && RANDOM.nextDouble() < funnyMessageChance)
				sb.append("  If you are satisfied with being average and totally unimpressive, you can quit now.");
		}
		return sb.toString();
	}
	
	public static String getAllPointsMessage() {
		final double funnyMessageChance = .5;
		if(RANDOM.nextDouble() < funnyMessageChance)
			return "This is the worst program I have ever processed.  As an AI, I cannot vomit; however, I have virtually vomited on my CPU.  I hope to never process a program from you again.  Take yourself outside and slap yourself until you bleed from your ears.";
		else
			return "Congratulations!  You earned all available points.  You are a coding rock star - humanity has hope.";
		
	}
}
