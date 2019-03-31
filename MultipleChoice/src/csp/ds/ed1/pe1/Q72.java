package csp.ds.ed1.pe1;
import java.util.*;
import static java.lang.System.out;

public class Q72 {

	private static final Random GEN = new Random(System.currentTimeMillis());
	private static final int NUM_ELEMENTS = (int)Math.pow(10, 6);
	
	public static void main(String[] args) {
		out.println("A...");
		ansA();
		out.println("\nB...");
		ansB();
		out.println("\nD...");
		ansD();
	}
	
	public static void ansA() {
		int[] rndList = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		for(int i = 0; i < NUM_ELEMENTS; i++) {
			int rnd = GEN.nextInt(10) + 1;
			rndList[rnd - 1] = rndList[rnd - 1] + 1;
		}
		
		for(Integer i : rndList)
			out.print(i + " ");
	}
	
	public static void ansB() {
		int sum = 0;
		for(int i = 0; i < NUM_ELEMENTS; i++) {
			sum = 0;
			int rnd = GEN.nextInt(10) + 1;
			sum += rnd;
		}
		double mean = (double)sum / NUM_ELEMENTS;
		out.println(mean);
	}
	
	public static void ansD() {
		int sum = 0;
		for(int i = 0; i < NUM_ELEMENTS; i++) {
			int rnd = GEN.nextInt(10) + 1;
			sum += rnd;
		}
		double mean = sum / NUM_ELEMENTS;
		out.println(mean);
	}

}
