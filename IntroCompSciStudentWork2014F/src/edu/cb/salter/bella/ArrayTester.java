package edu.cb.salter.bella;
import edu.jenks.dist.cb.*;
public class ArrayTester implements TestableArray
{
    public static void main(String[] args) {
        ArrayTester tester = new ArrayTester();
        /** begin test of getColumn */
        int[][] tester1 = { {1, 1, 1 }, {2, 3, 2}, {3, 2, 2}};
        int[] tester1Col0 = tester.getColumn(tester1, 0);
        assert tester1Col0[0] == 1 : "Error: first term in column incorrect in test of getColumn";
        assert tester1Col0[1] == 2 : "Error: second term in column incorrect in test of getColumn";
        assert tester1Col0[2] == 3 : "Error: third term in column incorrect in test of getColumn";
        
        /** begin test of hasAllValues */
        int[] tester1Col1 = tester.getColumn(tester1, 1);
        boolean test2HasVals = tester.hasAllValues(tester1Col0, tester1Col1);
        assert test2HasVals == true : "Error: test of hasAllValues returning false in a true case";
        int[] tester1Col2 = tester.getColumn(tester1, 2);
        boolean test2HasVals2 = tester.hasAllValues(tester1Col0, tester1Col2);
        assert test2HasVals2 == false : "Error: test of hasAllValues returning true in a false case";
        
        /** begin test of containsDuplicates */
        boolean testerCol0Dupes = tester.containsDuplicates(tester1Col0);
        assert testerCol0Dupes == false : "Error: test of containsDuplicates returning true in false case";
        boolean testerCol2Dupes = tester.containsDuplicates(tester1Col2);
        assert testerCol2Dupes : "Error: test of containsDuplicates returning false in true case";
     
        /** begin test of getRow */
        int[] tester1Row0 = tester.getRow(tester1, 0);
        assert tester1Row0[0] == 1 : "Error: first term in row incorrect in test of getRow";
        assert tester1Row0[1] == 1 : "Error: second term in row incorrect in test of getRow";
        assert tester1Row0[2] == 1 : "Error: third term in row incorrect in test of getRow";
        
        /** begin test of isLatin */
        int[][] latinSquare = {{0, 1, 2},{2, 1, 0}, {2, 0, 1}};
        assert tester.isLatin(latinSquare) == false : "Error: isLatin returning true in false case";
        System.out.println("End test of ArrayTester with no errors");
    }
    public ArrayTester()
    {
        
    }
    public int[] getColumn(int[][] arr2D, int c) {
        int numRows = arr2D.length;
        int[] col = new int[numRows];
        for(int r = 0; r < numRows; r++) {
            col[r] = arr2D[r][c];
        }
        return col;
    }
    public boolean hasAllValues(int[] arr1, int[] arr2) {
        boolean hasVals = true;
        int countNumSame = 0;
        if(arr1.length != arr2.length) {
            return false;
        }
        for(int i = 0; i < arr1.length; i++) {
            for(int c = 0; c < arr1.length; c++) {
                if(arr1[i] == arr2[c]) {
                    countNumSame++;
                    break;
                }
            }
        }
        hasVals = countNumSame == arr1.length;
        return hasVals;
    }
    public boolean containsDuplicates(int[] arr) {
       for(int i = 0; i < arr.length - 1; i++) {
           for(int c = i + 1; c < arr.length; c++) {
               if(arr[i] == arr[c]) {
                   return true;
                }
            }
        }
        return false;
    }
    public boolean isLatin(int[][] square) {
        int[] firstRow = getRow(square,0);
        int numCols = square[0].length;
        int numRows = square.length;
        if(containsDuplicates(firstRow)) {
            return false;
        }
        for(int r = 1; r < numRows; r++) {
            if(!(hasAllValues(getRow(square, r), firstRow))) {
                return false;
            }
        }
        for(int c = 0; c < numCols; c++) {
           if(!hasAllValues(getColumn(square, c), firstRow) || containsDuplicates(getColumn(square,c))) {
                return false;
           }
        }
        return true;
    }
    public int[] getRow(int[][] arr2D, int r) {
        int numCols = arr2D[0].length;
        int[] row = new int[numCols];
        for(int c = 0; c < numCols; c++) {
            row[c] = arr2D[r][c];
        }
        return row;
    }
}
