package edu.jenks.scrape.data.app.scoring;

import java.util.*;

import org.jdom2.Element;

import edu.jenks.scrape.data.app.KaCsvParser;

public abstract class KaScoring {

	protected static Map<String, KaScoring> TYPE_SCORING_MAP = new HashMap<>(10);
	protected static KaCsvParser parser; 
	
	public static void init(Element root, KaCsvParser parserArg) {
		parser = parserArg;
		List<Element> rules = root.getChild("rules").getChildren("rule");
		for(Element rule : rules) {
			String assignmentType = rule.getAttributeValue("assignment-type");
			Element ruleTypeE = rule.getChildren().get(0);
			String ruleType = ruleTypeE.getName();
			switch(ruleType) {
				case "completion-rule":
					TYPE_SCORING_MAP.put(assignmentType, new CompletionRule(ruleTypeE));
					break;
				case "points-rule":
					TYPE_SCORING_MAP.put(assignmentType, new PointsRule(ruleTypeE));
					break;
				default: System.err.println("Unhandled rule type: " + ruleType);
			}
		}
	}
	
	public static KaScoring getInstance(String assignmentType) {
		return TYPE_SCORING_MAP.get(assignmentType);
	}
	
	protected KaScoring() {}
	
	public abstract double getScore(String[] record);
}
