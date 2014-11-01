package edu.frstats.lodge.kaleb;

import java.util.ArrayList;
import java.util.List;

import edu.frstats.jenks.dist.ScoreInfo;
import edu.frstats.jenks.dist.StatsRecorder;

public class Stats implements StatsRecorder {
	private ArrayList<ScoreInfo> scorelist = new ArrayList<>(0);

	public Stats() {
	}

	@Override
	public List<ScoreInfo> getScoreList() {
		return scorelist;
	}

	@Override
	public boolean record(int score) {
		boolean doesexist = false;
		int location = 0;
		for (int count = 0; count < scorelist.size() && !doesexist && scorelist.get(count).getScore()<= score; count++, location++){
			if(scorelist.get(count).getScore() == score){
				doesexist = true;
				scorelist.get(count).increment();
				return false;
			}
		}
		if (!doesexist){
			ScoreInfo newScore = new ScoreInfo(score);
			scorelist.add(location, newScore);
			return true;
		}
		return false;
	}

	@Override
	public void recordScores(int[] scores) {
		for (int score : scores) {
			record(score);
		}
	}

}
