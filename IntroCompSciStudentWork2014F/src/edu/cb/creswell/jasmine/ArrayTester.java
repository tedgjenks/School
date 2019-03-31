package edu.cb.creswell.jasmine;
import edu.jenks.dist.cb.*;


public class ArrayTester implements TestableArray
{
    public static void main(String[] args) {
        ArrayTester at = new ArrayTester();
        int[] test={0,1,1,2};
        int[] test2= {0,0,2,2};
        System.out.println(at.hasAllValues(test,test2));
        System.out.println(at.containsDuplicates(test));
        
        
    }    
    public ArrayTester() {
    }
    public int[] getColumn(int[][] arr2D,int c) {
        int [] arr= new int[arr2D.length];
        for(int i=0; i<arr2D.length; i++) {
            arr[i]=arr2D[i][c];
        }
        return arr;
    }
    public boolean hasAllValues(int[] arr1,int[] arr2) {
        boolean allValues=true;
        int count=0;
        for (int num=0; num < arr1.length; num++) {
            for (int i=0; i<arr2.length; i++) {
                if(arr1[num] == arr2[i]) {
                    count++;
                }
            }
            if (count==0) {
                allValues=false;
            }
            count=0;
            
        }
        return allValues;
       
    }
    public boolean containsDuplicates(int[] arr) {
        boolean dups=false;
        int count=0;
        for (int num=0; num < arr.length; num++) {
            for (int i=0; i<arr.length; i++) {
                if(arr[num] == arr[i]) {
                    count++;
                }
            }
            if (count>1) {
                dups=true;
            }
            count=0;
            
        }
        return dups;
       
    }
    
    public boolean isLatin(int[][] square) {
       if (containsDuplicates(square[0])==true) {
           return false;
       }
       for (int row=0; row<square.length; row++) {
           if(!hasAllValues(square[0],square[row])) {
               return false;
            }
        }
       for (int col=0; col<square.length; col++) {
           if (!hasAllValues(square[0], (getColumn(square,col)))) {
              return false;
            }
        }
        return true;
        
    }
}
