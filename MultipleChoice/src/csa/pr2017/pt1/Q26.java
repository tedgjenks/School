package csa.pr2017.pt1;

public class Q26 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Point implements Comparable {
	
	private int x, y;

	@Override
	public int compareTo(Object o) {
		boolean b = x == ((Point)o).x && y == ((Point)o).y;
		return 0;
	}
	
}
