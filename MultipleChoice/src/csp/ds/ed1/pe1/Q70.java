package csp.ds.ed1.pe1;

import static java.lang.System.out;

public class Q70 {

	public static void main(String[] args) {
		out.println("Mystery...");
		mystery(3);
		out.println("\nA...");
		ansA();
		out.println("\nB...");
		ansB();
		out.println("\nC...");
		ansC();
		out.println("\nD...");
		//ansD();
	}
	
	private static void ansD() {
		for(int n = 3; n > 0; n--) {
			n = 3;
			out.print("Hello ");
		}
	}
	
	private static void ansB() {
		int n = 3;
		while(n >= 0) {
			out.print("Hello ");
			n--;
		}
	}
	
	private static void ansC() {
		for(int n = 3; n > 0; n--) {
			out.print("Hello ");
		}
	}
	
	private static void mystery(int n) {
		out.print("Hello ");
		if(n > 0)
			mystery(n - 1);
	}
	
	private static void ansA() {
		int n = 3;
		while(n != 0) {
			out.print("Hello ");
			n--;
		}
	}

}
