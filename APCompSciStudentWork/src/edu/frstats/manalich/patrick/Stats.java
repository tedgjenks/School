package edu.frstats.manalich.patrick;

import java.util.ArrayList;
import java.util.List;

import edu.frstats.jenks.dist.*;

public class Stats implements StatsRecorder { 
	private ArrayList<ScoreInfo> scoreList = new ArrayList<ScoreInfo>();

	public boolean record(int score){
		boolean result = false; 
		boolean check = false; //keeps account for if the score has been added in or not


		System.out.println("Size: " + scoreList.size());
		

		for(int index = scoreList.size() - 1 ; index >= 0 && !result; index--){
			if (score == scoreList.get(index).getScore()){
				scoreList.get(index).increment();
				check = true;
			}
			else{
				if( score > scoreList.get(index).getScore()){
					ScoreInfo si = new ScoreInfo(score);
					scoreList.add(index + 1,si);
					result = true;
					check = true;
				}
			}
		}
		
		if(scoreList.size() == 0){
			result = true;
			ScoreInfo si = new ScoreInfo(score);
			scoreList.add(si);
			check = true;
		}
		
		if(!check){
			ScoreInfo si = new ScoreInfo(score);
			scoreList.add(0,si);
			check = true;
			result = true;
			
		}
		
		System.out.println(getScoreList());
		return result;
		
	}
	

	@Override
	public List<ScoreInfo> getScoreList() {
		return scoreList;
	}

	@Override
	public void recordScores(int[] arg0) {
		for(int index = 0; index < arg0.length;index++){
			record(arg0[index]);
		}
	}


}
