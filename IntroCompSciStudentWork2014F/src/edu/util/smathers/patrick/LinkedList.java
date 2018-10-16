package edu.util.smathers.patrick; 
import java.util.NoSuchElementException;

import edu.jenks.dist.util.*;

public class LinkedList<E> implements AbstractLinkedList<E>{
    Element first; 
    Element last;
    private int size = 0;
    void adding(E obj){
        if(first == null){
            first = new Element(obj);
            last = first;
        }else if(size == 1){
            last = new Element(obj,first);
            first.after = last;
        }
        else{
            last.after = new Element(obj, last);
            last = last.after;

        }
        size++;
    }

    //extended methods:
    
    public boolean add(E e){
        adding(e);
        return true;
    }
    
    public void add(int index, E element) throws IndexOutOfBoundsException{
        if(index < 0 || index > size()){
            throw new IndexOutOfBoundsException("Index Out of Bounds When Adding at Element Index: " + index);
        }
        if(index == 0){
            addFirst(element);
            return;
        }
        if(index == size){ //adds to the end of the array
            addLast(element);
            return;
        }
        // assert first != null;
        
        Element temp = first;
        //assert temp != null;
        int i = 0;
        while(i != index){
            if(temp.after == null)
                break;
            temp = temp.after;
            i++;
        }

        Element tempBefore = temp.before;
        tempBefore.after = new Element(element, tempBefore);
        tempBefore.after.before = tempBefore;
        temp.before = tempBefore.after; //sets the orignal's before object to the new object in its place
        tempBefore.after.after = temp;
        //fixes indexes:
        /*do{
            temp.index++;
            if(temp.after == null)
                break;
            temp = temp.after;
        }while(temp.after != null);*/
        size++;
    }

    public void addFirst(E e){
        if(size == 0){
            first = new Element(e);
            last = first;
            size++;
            return;
        }
        first.before = new Element(e);
        //first.index = 1; //second element
        first.before.after = first; //sets new first element's 'after' variable to the old first element
        first = first.before;
        //fix indexes:
        /*Element temp = first.after.after;
        do{
            temp.index++;
            temp = temp.after;
        }while(temp != null);*/
        size++;
    }

    public void addLast(E e){
        adding(e);
    }

    public void clear(){
        first = null;
        last = null;
        size = 0;
    }

    public boolean contains(Object o){
        if(indexOf(o) != -1)
            return true;
        return false;
    }

    public E get(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds");
        Element temp = first;
        int i = 0;
        while(i != index){
            temp = temp.after;
            i++;
        }
        return temp.data;
    }

    public E getFirst() throws NoSuchElementException{
        if(first == null)
            throw new NoSuchElementException("Element does not exist");
        return first.data;
    }

    public E getLast() throws NoSuchElementException{
        if(last == null)
           throw new NoSuchElementException("Element does not exist");
        return last.data;
    }

    public int indexOf(Object o){
        int i = 0;
        Element temp = first;
        while(temp != null){
            if(o==null ? get(i)==null : o.equals(get(i)))
                return i;
            temp = temp.after;
            i++;
        }
        return -1;
    }

    public int lastIndexOf(Object o){
        int i = size - 1;
        Element temp = last;
        do{
            if(o==null ? get(i)==null : o.equals(get(i)))
                return i;
            temp = temp.before;
            i--;
        }while(temp.before != null);
        return -1;
    }

    public E remove() throws NoSuchElementException{
        if(first == null)
            throw new NoSuchElementException("No elements exist to remove");
        first.after.before = null;
        E original = first.data;
        first = first.after;
        //fix indexes:
        /*Element temp = first;
        do{
            temp.index--;
        }while(temp.after != null);*/
        if(size > 0)
        size--;
        return original;
    }

    public E remove(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds.");
        Element temp = first;
        int i = 0;
        while(i != index){
            i++;
            temp = temp.after;
        }
        if(temp.before != null && temp.after != null){ //specified index is not the first and/or last index
            temp.before.after = temp.after;
            temp.after.before = temp.before;
            size--;
        }
        else if(temp.before == null && temp.after == null){
            clear();
        }
        else if(temp.before == null){
            temp.after.before = null;
            first = temp.after;
            size--;
        }
        else if(temp.after == null){
            temp.before.after = null;
            last = temp.before;
            size--;
        }

        
        return temp.data;
    }

    public boolean remove(Object o){
        if(size == 0)
           return false;
        Element temp = first;
        int i = 0;
        do{
            if(o==null ? get(i)==null : o.equals(get(i))){
                remove(i);
                return true;
            }
            temp = temp.after;
            i++;
        }while(temp != null);
        return false;
    }

    public E removeLast() throws NoSuchElementException{
        if(last == null)
            throw new NoSuchElementException("No elements exist to remove.");
        Element original = last;
        last.before.after = null;
        last = last.before;
        size--;
        return (E)original.data;
        
    }

    public E set(int index, E element) throws IndexOutOfBoundsException{
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds.");
        Element temp = first;
        int i = 0;
        while(i != index){
            temp = temp.after;
            i++;
        }
        E original = (E)temp.data;
        temp.data = element;
        return original;
    }

    public int size(){
        return size;
    }

    public String toString(){
        String returnStr = "LinkedList(" + size + ")" + " [";
        Element temp = first;
        for(int i = 0; i < size(); i++){
            returnStr += temp.data + ", ";
            temp = temp.after;
        }
        /*while(temp != null){
            returnStr += temp.data + ", ";
            temp = temp.after;
        }*/
        return returnStr + "]";
    }

    public class Element{
        E data;
        Element before;
        Element after;
        //int index;
        public Element(E obj){ //first object
            data = obj;
            //index = 0;
        }
        public Element(E obj, Element b){//used after one element already exists unless adding to front
            data = obj;
            before = b;
            // index = before.index + 1;
        }
    }
}