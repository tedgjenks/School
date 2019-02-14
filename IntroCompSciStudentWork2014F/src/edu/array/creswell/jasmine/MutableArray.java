package edu.array.creswell.jasmine;
import edu.jenks.dist.array.*;
/**
* Write a description of class MutableArray here.
*
* @author jizmen
* @version .0
*/
public class MutableArray implements List
{
   public static void main(String[] args){
       MutableArray test = new MutableArray(2);
       test.clear();
       System.out.println(test);
       test.add(1);
       System.out.println(test);
       test.add(2);
       System.out.println(test);
       test.add(3);
       System.out.println(test);
       test.add(4);
       System.out.println(test);
       test.add(5);
       System.out.println(test);
       test.add(6);
       System.out.println(test);
       test.add(0,0);
       System.out.println(test);
       System.out.println(test.values);
       test.remove(3);
       System.out.println(test);
    }
    public String toString(){
        String elements= "";
        for (int i=0; i<array.length; i++) {
            elements += array[i];
        }    
        return elements;
    }
   
   private Object[] array;
   private int values=0;
   private Object[] temp;
   private Object tempValue;
   public MutableArray() {
       this(10);
   }
   public MutableArray(int capacity) {
       array= new Object[capacity];
   }
   public boolean add(Object element) {
       add(values, element);
       
       /*
       if (values < array.length) {
           array[values]= element;
           values++;
       } else {
           temp= new Object[array.length*2];
           for (int i=0; i<array.length; i++) {
              temp[i]= array[i]; 
           }
           temp[values]= element;
           array=temp;
        }
        */ 
        
        
       return true ;
   }
   public void add(int index,Object element) {
       if (index<0 || index > values) {
           throw new IndexOutOfBoundsException();
        }
       if (values+1 > array.length) {
           temp= new Object[values*2];
           for (int i=0; i<array.length; i++) {
              temp[i]= array[i]; 
           }
           array=temp;
       }
       for (int i=array.length-2; i>=index; i--) {
           tempValue=array[i];
           array[i+1]=tempValue;
       }   
       array[index]= element;
       values++;
        
   }
   public void clear() {
       values=0;
   }
   public Object get(int index) {
        if (index<0 || index > values-1) {
           throw new IndexOutOfBoundsException();
        }
       return array[index];
   }
   public boolean isEmpty() {
       if (values==0) {
           return true;
       } else{
       return false;
       }
   }
   public Object remove(int index) {
       if (index<0 || index > values-1) {
           throw new IndexOutOfBoundsException();
        }
       Object ele = array[index];
       for (int i=index; i<array.length-1; i++) {
           array[i]= array[i+1];
        }
        values--;
        return ele;   
       
   }
   public Object set(int index,Object element) {
        if (index<0 || index > values-1) {
           throw new IndexOutOfBoundsException();
        }
       Object ele = array[index];
       array[index]=element;
       return ele;
   }
   public int size() {
       return values;
   }
   public Object[] toArray() {
       temp= new Object[values];
       for (int i=0; i<values; i++)
            temp[i]=array[i];
       return temp;
   }
   
   public Object[] getBackingArray() {
       return array;
   }
}
