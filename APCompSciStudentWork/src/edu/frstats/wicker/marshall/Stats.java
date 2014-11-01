package edu.frstats.wicker.marshall;

import java.util.ArrayList;
import java.util.List;

import edu.frstats.jenks.dist.StatsRecorder;
import edu.frstats.jenks.dist.ScoreInfo;;

public class Stats implements StatsRecorder{
	private ArrayList<ScoreInfo> scoreList = new ArrayList<ScoreInfo>();

	public boolean record(int score){
		ScoreInfo si = new ScoreInfo(score);
		boolean returnVal = false;
		boolean shouldStop = false;
		boolean hasBeenHandled = false;
		if(scoreList.size() == 0){
			shouldStop = true;
			scoreList.add(si);
			returnVal = true;
		}else{
			for(int i = scoreList.size() - 1 ; !shouldStop && i >= 0 ; i--){
				if(scoreList.get(i).getScore() == si.getScore()){
					scoreList.get(i).increment();
					shouldStop = true;
					hasBeenHandled = true;
				}
				else{
					if(i != 0 && si.getScore() > scoreList.get(i-1).getScore()){
						scoreList.add(i + 1, si);
						shouldStop = true;
						returnVal = true;
						hasBeenHandled = true;
					}	
				}
			}
			if (!hasBeenHandled){
				scoreList.add(0 , si);
			}
			if(scoreList.size() == 1 && !shouldStop){
				scoreList.add(1 , si);
				returnVal = true;
			}
		}
		return returnVal;
	}
	public void recordScores(int[] stuScores){
		for(int i = 0 ; i < stuScores.length ; i++){
			this.record(stuScores[i]);
		}
	}
	public List<ScoreInfo> getScoreList() {
		return scoreList;
	}
}
