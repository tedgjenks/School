package csa.ds.ed9.pe1;

public class Q37 {

	public static void main(String[] args) {
		System.out.println(spain(4));

	}
	
	public static int spain(int n) {
		if(n == 0 || n == 1)
			return n;
		else
			return n + spain(n - 1) + spain(n - 2);
	}

}
