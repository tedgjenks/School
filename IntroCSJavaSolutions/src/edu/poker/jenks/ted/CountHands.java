package edu.poker.jenks.ted;

public class CountHands {

	public CountHands() {
	}

	public static void main(String[] args) {
		System.out.println(countFullHouse());
	}
	
	public static int countFullHouse() {
		final int maxValue = 13;
		int count = 0;
		for(int triple = 1; triple <= maxValue; triple++) {
			for(int pair = 1; pair <= maxValue; pair++) {
				if(triple != pair) {
					count += 6; // 6 ways to create each pair
				}
			}
		}
		count *= 4; // 4 ways to create each
		return count;
	}
}
