package edu.array.salter.bella;
import edu.jenks.dist.array.*;
/**
 * Write a description of class MutableArray here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MutableArray implements List
{
    // instance variables - replace the example below with your own
    Object[] array;
    int size = 0;
    public static void main(String[] args) {
        System.out.println("Begin test of MutableArray");
        
        
        MutableArray test = new MutableArray(4);
        test.add(3);
        System.out.println("Value at index 0:" + test.get(0));
        assert test.get(0).equals(3) : "Error: number not added and/or in wrong index in empty array";
        MutableArray test2 = new MutableArray(5);
        test2.add(1);
        test2.add(1,2);
        System.out.println("Size:" + test2.size());
        System.out.println("Value at index 0:" + test2.get(0));
        System.out.println("Value at index 1:" + test2.get(1));
        assert test2.get(1).equals(2) : "Error: number not added and/or in wrong index in array with values";
        //System.out.println(test.toString());
        //boolean empty = test.isEmpty();
        //System.out.println(empty);
        //Object[] testArray3;
        //testArray3 = new Object[test2.size()];
        //testArray3 = test2.remove(1);
        //System.out.println("Array after index 1 removed:" + test2.get(0) + test2.get(1));
        
        System.out.println("End test of MutableArray with no errors");
    }
    /**
     * Constructor for objects of class MutableArray
     */
    public MutableArray(){
        this(10);
    }
    public MutableArray(int capacity){
        array = new Object[capacity];
    }
    public boolean add(Object element){
        int newIndex = size();
        if(isEmpty()) {
            add(0, element);
        } else{
            add(newIndex, element);
        }
        return true;
    }
    public void add(int index,Object element) throws IndexOutOfBoundsException {
        int capacity = array.length;
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Error: index out of bounds");
        }
        if(isEmpty()) {
            array[index] = element;
            size++;
        }else{
            if(size == capacity || index >= capacity) {
                Object[] newArray = new Object[capacity * 2];
                for(int c = 0; c < size; c++) {
                newArray[c] = array[c];
               }
               array = newArray;
            } 
            //Object elementAtIndex = array[index];
            //array[index] = element;
            Object elementAtI = 0;
            for(int i = size - 1; i >= index; i--) {
                elementAtI = array[i];
                array[i + 1] = elementAtI;
            }
            array[index] = element;
            size++;
        
        }
    }
    public void clear() {
        for(int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        size = 0;
    }
    public Object get(int index) throws IndexOutOfBoundsException {
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }else{ 
            return array[index];
        }
    }
    public boolean isEmpty() {
        boolean empty = true;
        if(size > 0) {
            empty = false;
        }
        return empty;
    }
    public Object remove(int index) throws IndexOutOfBoundsException {
        Object elementAtI = 0;
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Error: index out of bounds");
        }
        for(int i = index + 1; i < size; i++) {
                elementAtI = array[i];
                array[i - 1] = elementAtI;
        }
        size--;
        return array[index];
    }
    public Object set(int index,Object element) throws IndexOutOfBoundsException{
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Error: index out of bounds");
        }
        Object elementAtIndex = array[index];
        array[index] = element;
        return elementAtIndex;
        
    }
    public int size() {
        return size;
    }
    public Object[] toArray() {
        Object[] newArray;
        newArray = new Object[size];
        for(int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    public Object[] getBackingArray() {
        return array;
    }
    public String toString(){
        int capacity = array.length;
        String string = new String("");
        for(Object element:array) {
            string += element;
            string += ", ";
        }
        return string;
    }
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    
}
