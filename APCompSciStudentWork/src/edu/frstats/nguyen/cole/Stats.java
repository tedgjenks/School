package edu.frstats.nguyen.cole;
import java.util.*;

import edu.frstats.jenks.dist.ScoreInfo;
import edu.frstats.jenks.dist.StatsRecorder;
public class Stats implements StatsRecorder{
    private List<ScoreInfo> scoreList=new ArrayList<ScoreInfo>();
    public Stats(){

    }
    public Stats(List<ScoreInfo> newList){
        scoreList=newList;
    }
    public void printScoreList(){
        for(int index=0;index<scoreList.size();index++){
            System.out.print(scoreList.get(index).getScore()+"\t");
        }
        System.out.println();
    }
    public boolean record(int score){
        int count=0;
        int index1=0;
        if(scoreList.isEmpty()){
            ScoreInfo newScore = new ScoreInfo(score);
            scoreList.add(newScore);
            return true;
        }
        if(scoreList.get(0).getScore()>score){
            ScoreInfo newScore = new ScoreInfo(score);
            scoreList.add(0,newScore);
            return true;
        }
        for(int
index=0;index<scoreList.size()&&count==0&&scoreList.get(index).getScore()<=score;index++){
            if(scoreList.get(index).getScore()==score){
                count=1;
                scoreList.get(index).increment();
                return false;
            }
            index1=index;
        }
        if(count==0){
            ScoreInfo newScore=new ScoreInfo(score);
            scoreList.add(index1+1,newScore);
            return true;
        }
        return false;
    }
    public void recordScores(int[] stuScores){
        for(int index=0;index<stuScores.length;index++)
            System.out.println(record(stuScores[index]));
    }
    @Override
    public List<ScoreInfo> getScoreList() {
        // TODO Auto-generated method stub
        return scoreList;
    }

}