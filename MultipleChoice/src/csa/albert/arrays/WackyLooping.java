package csa.albert.arrays;

public class WackyLooping {

	public static void main(String[] args) {
		int age[] = {20, 10, 80, 50};
		printArray(age);
		System.out.println();
		for(int i = age.length - 1; i >= 0; i--) {
			for(int j = 0; j < age.length; j++) {
				int temp = age[i];
				age[i] = age[j] - 2;
				age[j] = temp + 2;
			}
			System.out.print("i = " + i + ": ");
			printArray(age);
			System.out.println();
		}
	}
	
	static void printArray(int[] age) {
		for(int i = 0; i < age.length; i++)
			System.out.print(age[i] + " ");
	}
}
