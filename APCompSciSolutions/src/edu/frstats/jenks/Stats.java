/**
 * 
 */
package edu.frstats.jenks;

import java.util.ArrayList;
import java.util.List;

import edu.frstats.jenks.dist.ScoreInfo;
import edu.frstats.jenks.dist.StatsRecorder;

/**
 * @author Ted Jenks
 *
 */
public class Stats implements StatsRecorder {
	private List<ScoreInfo> scoreList = new ArrayList<ScoreInfo>();

	/* (non-Javadoc)
	 * @see edu.frstats.jenks.dist.StatsRecorder#record(int)
	 */
	@Override
	public boolean record(int score) {
		boolean newScoreAdded = false, scoreFound = false;
		for(int index = scoreList.size() - 1; index >= 0 && !scoreFound && !newScoreAdded; index--) {
			ScoreInfo scoreInfo = scoreList.get(index);
			int scoreAtIndex = scoreInfo.getScore();
			if(score == scoreAtIndex) {
				scoreInfo.increment();
				scoreFound = true;
			} else if(score > scoreAtIndex) {
				scoreList.add(index + 1, new ScoreInfo(score));
				newScoreAdded = true;
			}
		}
		if(!scoreFound && !newScoreAdded) {
			scoreList.add(0, new ScoreInfo(score));
			newScoreAdded = true;
		}
		return newScoreAdded;
	}

	/* (non-Javadoc)
	 * @see edu.frstats.jenks.dist.StatsRecorder#recordScores(int[])
	 */
	@Override
	public void recordScores(int[] stuScores) {
		for(int index = stuScores.length - 1; index >= 0; index--)
			record(stuScores[index]);
	}

	/* (non-Javadoc)
	 * @see edu.frstats.jenks.dist.StatsRecorder#getScoreList()
	 */
	@Override
	public List<ScoreInfo> getScoreList() {
		return scoreList;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(scoreList.size() * 20);
		for(ScoreInfo scoreInfo : scoreList)
			sb.append(scoreInfo).append(" | ");
		return sb.toString();
	}

}
