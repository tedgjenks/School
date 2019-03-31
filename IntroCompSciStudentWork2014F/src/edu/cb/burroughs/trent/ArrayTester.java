package edu.cb.burroughs.trent;
import edu.jenks.dist.cb.*;

public class ArrayTester implements TestableArray{
    
    public ArrayTester(){
        super();
    }
    
    public int[] getColumn(int[][] arr2D,int c){
        
        int[] list = new int[(arr2D.length)];
        
        for(int i = 0; i < arr2D.length; i++){
            list[i] = arr2D[i][c];
        }
        return list;
    }
    
    public boolean hasAllValues(int[] arr1,int[] arr2){
       
        for(int i = 0; i < arr1.length; i++){
            for(int j = 0; j < arr2.length; j++){
                if(arr1[i] == arr2[j]){
                    break;
                }
                if(j == arr2.length - 1 && arr1[i] != arr2[j]){
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean containsDuplicates(int[] arr){
        
        for(int i = 0; i < arr.length; i ++){
            for(int j = i+1; j < arr.length; j++){
                if(arr[i] == arr[j]){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isLatin(int[][] square){
        
        for(int o = 1; o < square.length; o++){
            if( hasAllValues(getColumn(square, o), getColumn(square, o-1)) == false ){
                return false;
            }
        }
        for(int i = 0; i < square.length; i++)
            if(containsDuplicates(square[i]))
                return false;
        for(int j = 0; j < square[0].length; j++)
            if(containsDuplicates(getColumn(square, j)))
                return false;
        
        return true;
    }
}
