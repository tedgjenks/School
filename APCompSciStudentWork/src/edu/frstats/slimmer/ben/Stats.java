package edu.frstats.slimmer.ben;

import java.util.ArrayList;
import java.util.List;

import edu.frstats.jenks.dist.ScoreInfo;
import edu.frstats.jenks.dist.StatsRecorder;

public class Stats
extends java.lang.Object
implements StatsRecorder {
	
	private ArrayList<ScoreInfo> scoreList= new ArrayList<ScoreInfo>();
	
	@Override
	public List<ScoreInfo> getScoreList() {
		return scoreList;
	}

	@Override
	public boolean record(int arg0) {
		boolean scoreFound=false;
		int foundSpot=-1;
		if (scoreList.isEmpty()) {
			ScoreInfo newScore= new ScoreInfo(arg0);
			scoreList.add(newScore);
		}
		for (int index=0; index<scoreList.size(); index++) {
			if(scoreList.get(index).getScore()==arg0) {
				scoreFound= true;
				foundSpot= index;
			}
		}
		if (scoreFound==false) {
			ScoreInfo newScore= new ScoreInfo(arg0);
			scoreList.add(findSpot(arg0, scoreList), newScore);;
			return true;
		}
		else {
			scoreList.get(foundSpot).increment();
			return false;
		}
	}

	@Override
	public void recordScores(int[] arg0) {
		for (int index=0; index<arg0.length; index++) {
			record(arg0[index]);
		}
	}
	
	private int findSpot(int enterValue, ArrayList<ScoreInfo> list) {
		for (int index=0; index<list.size(); index++) {
			if(list.get(index).getScore()<enterValue && list.get(index+1).getScore()>enterValue)
				return index+1;
		}
		return -1;
	}

}
