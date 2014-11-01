package edu.frstats.shearer.courtney;

import java.util.ArrayList;
import java.util.List;

import edu.frstats.jenks.dist.ScoreInfo;
import edu.frstats.jenks.dist.StatsRecorder;

public class Stats
extends java.lang.Object
implements StatsRecorder{
	private ArrayList<ScoreInfo> scoreList = new ArrayList<ScoreInfo>();
 	public Stats(){
		
	}

	public boolean record(int score){
		for(int i = 0; i < scoreList.size(); i++){
			if(score == scoreList.get(i).getScore()){
				scoreList.get(i).increment();
				return false;
			}
			else{
				if(score < scoreList.get(i).getScore()){
					ScoreInfo newScore = new ScoreInfo(score);
					scoreList.add(i,newScore);
					return true;
				}
			}
			
		}
		return false;
		
	}
	public void recordScores(int[] stuScores){
		for( int index = 0; index < stuScores.length; index++){
			record(stuScores[index]);
		}
		
	}
	public List<ScoreInfo> getScoreList(){
		return scoreList;
	}

}
