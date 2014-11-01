package edu.frstats.jenks;

public class StatsTest {

	public static void main(String[] args) {
		int[] scores = {20, 30, 40, 50, 60, 70, 80, 90, 100, 10, 100, 10, 50};
		Stats stats = new Stats();
		stats.recordScores(scores);
		System.out.println(stats);
	}

}
