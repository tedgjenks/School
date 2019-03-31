package csa.albert.arrays;

public class ArrayArithmeticUsingTwoArraysOfIntegers {

	public static void main(String[] args) {
		int[] nums1 = {1, 2, 3, 4};
		int[] nums2 = {1, 2, 3, 4};
		int total = 0, j = nums2.length - 1;
		for(int i = 0; i < nums1.length; i++) {
			total += nums1[i]*nums2[j];
			System.out.print(total);
			j--;
		}
	}

}
