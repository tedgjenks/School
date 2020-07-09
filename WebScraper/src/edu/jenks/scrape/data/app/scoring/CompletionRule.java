package edu.jenks.scrape.data.app.scoring;

import org.jdom2.Element;

public class CompletionRule extends KaScoring {

	private final int COMPLETION_POINTS;
	private final String COMPLETION_DATE_LABEL;
	
	public CompletionRule(Element completionRule) {
		COMPLETION_POINTS = Integer.parseInt(completionRule.getAttributeValue("completion-points"));
		COMPLETION_DATE_LABEL = completionRule.getAttributeValue("header-completion");
	}

	@Override
	public double getScore(String[] record) {
		String completionDate = record[parser.HEADER_INDECES.get(COMPLETION_DATE_LABEL)];
		return completionDate.isBlank() ? 0 : COMPLETION_POINTS;
	}

}
