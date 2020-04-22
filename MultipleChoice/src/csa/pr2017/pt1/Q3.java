package csa.pr2017.pt1;

public class Q3 {

	public static void main(String[] args) {
		new Q3().mystery(3);
	}
	
	public void mystery(int n) {
		int k;
		for(k = 0; k < n; k++) {
			mystery(k);
			System.out.print(n);
		}
	}

}
