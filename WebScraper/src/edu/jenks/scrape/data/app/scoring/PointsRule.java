package edu.jenks.scrape.data.app.scoring;

import org.jdom2.Element;

public class PointsRule extends KaScoring {

	private final String BEST_SCORE_LABEL;
	private final String POINTS_POSSIBLE_LABEL;
	private final double MIN_SCORE_PERCENT_AS_DECIMAL;
	private final double POINT_VALUE;
	public PointsRule(Element pointsRule) {
		BEST_SCORE_LABEL = pointsRule.getAttributeValue("header-best-score");
		POINTS_POSSIBLE_LABEL = pointsRule.getAttributeValue("header-points-possible");
		Element curve = pointsRule.getChild("curve");
		if(curve != null) {
			String minScorePercent = curve.getAttributeValue("min-score-percent");
			MIN_SCORE_PERCENT_AS_DECIMAL = Double.parseDouble(minScorePercent) / 100;
			POINT_VALUE = Double.parseDouble(curve.getAttributeValue("point-value"));
		} else {
			MIN_SCORE_PERCENT_AS_DECIMAL = 0;
			POINT_VALUE = 1;
		}
	}

	@Override
	public double getScore(String[] record) {
		String scoreText = record[parser.HEADER_INDECES.get(BEST_SCORE_LABEL)];
		double score = 0;
		if(!scoreText.isBlank()) {
			score = Double.parseDouble(scoreText);
			double pointsPossible = Double.parseDouble(record[parser.HEADER_INDECES.get(POINTS_POSSIBLE_LABEL)]);
			score = applyCurve(score, pointsPossible);
			score = verifyScore(score, pointsPossible);
		}
		return score;
	}
	
	private double verifyScore(double score, double pointsPossible) {
		if(score > pointsPossible) {
			System.err.println("Bad score");
			score = pointsPossible;
		}
		return score;
	}
	
	private double applyCurve(double score, double pointsPossible) {
		double finalScore = MIN_SCORE_PERCENT_AS_DECIMAL * pointsPossible;
		finalScore += score * POINT_VALUE;
		return finalScore;
	}
}
