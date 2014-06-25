package edu.frstats.rogers.payton;

import java.util.ArrayList;
import java.util.List;

import edu.frstats.jenks.dist.ScoreInfo;
import edu.frstats.jenks.dist.StatsRecorder;

public class Stats implements StatsRecorder{
	
	ArrayList<ScoreInfo> scoreList = new ArrayList<ScoreInfo>();
	
	@Override
	public List<ScoreInfo> getScoreList() {
		return this.scoreList;
	}

	@Override
	public boolean record(int score) {
		
		if (this.scoreList.isEmpty()) { //if empty, add new ScoreInfo
			this.scoreList.add(new ScoreInfo(score));
			return true;
		}
			

		for (int index = 0; index < this.scoreList.size(); index++) { //iterates through scoreList
			if (score < this.scoreList.get(index).getScore()) { //due to iteration logic, if the score is less than the array score, it needs to be added
				this.scoreList.add(index, new ScoreInfo(score));
				return true;
			} else if (score == this.scoreList.get(index).getScore()){ //and if it's the same, just increment it
				scoreList.get(index).increment(); 
				 return false;
			}
		}
		this.scoreList.add(new ScoreInfo(score)); //if not found above, must be bigger
		return true;
	}

	@Override
	public void recordScores(int[] list) {
		for (int index = 0; index < list.length; index++) { //iterates through array, uses record to input values
			this.record(list[index]);
		}
		
	}

}
