/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.util.bagwell.brady;
import edu.jenks.dist.util.AbstractLinkedList;
import java.util.NoSuchElementException;

//FIX: Remove

/**
 *
 * @author bagwbr05
 * @param <E>
 */
public class LinkedList<E> implements AbstractLinkedList<E>{
    
    public class Element { //DONE
        
        private E current; //The current element (of type E)
        private Element next; //The next element (of type Element)
        
        public Element(E currentElement) {
            current = currentElement;
        }
        
        private Element getNextElement() {
            return next;
        }
        
        private void setNextElement(Element nextElement) {
            next = nextElement;
        }
        
        private E getCurrentElement() {
            return current;
        }
        
        private void setCurrentElement(E currentElement) {
            current = currentElement;
        }
    }
    
    Element elementList; //The whole list of elements
    private int size = 0; //The size of elementList
    
    /*
    @Override
    public boolean add(E e) { //DONE
        Element elementIndex = elementList;
        if(size == 0) { //Creates the new element if size = 0
            elementList = new Element(e); 
            size++;
            return true;
        }
        else {
            do {
                elementIndex = elementIndex.getNextElement();
            } while(elementIndex.getNextElement() != null);
        }
        elementIndex.setNextElement(new Element(e));
        size++;
        return true;
    }
    
    @Override
    public void	add(int index, E element) { //DONE
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if(index == size){ //If the purpose is to add to the end
            add(element);
        }
        else{
            int counter = 0;
            Element elementIndex = elementList;
            while (counter != index){
                elementIndex = elementIndex.getNextElement();
                counter++;
            }
            E cElement = elementIndex.getCurrentElement();
            Element nElement = elementIndex.getNextElement();
            Element newElement = new Element(cElement); //Element consists of the old current and line of next indexes
            newElement.setNextElement(nElement);
            elementIndex.setCurrentElement(element); //adds the next element
            elementIndex.setNextElement(newElement); //shifts the original elements 
            size++;
        }
    }
    */
    
    @Override
    public boolean add(E e) { //DONE
        if(size == 0) { //Creates the new element if size = 0
            elementList = new Element(e); 
        }
        else {
            Element elementIndex = elementList;
            while(true){ //Searches until there is a blank space to put the new element
                if(elementIndex.getNextElement() == null){
                    elementIndex.setNextElement(new Element(e));
                    size++;
                    return true;
                }
                elementIndex = elementIndex.getNextElement();
            }
        }
        size++;
        return true;
    }
    
    @Override
    public void	add(int index, E element) { //DONE
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if(index == size){ //If the purpose is to add to the end
            add(element);
            return;
        }
        int counter = 0;
        Element elementIndex = elementList;
        while(true) { //Searches until it reaches the correct index
            if(counter == index) {
                E cElement = elementIndex.getCurrentElement();
                Element nElement = elementIndex.getNextElement();
                
                //Element consists of the old current and line of next indexes
                Element newElement = new Element(cElement);
                newElement.setNextElement(nElement);
                
                elementIndex.setCurrentElement(element); //adds the next element
                elementIndex.setNextElement(newElement); //shifts the original elements 
                size++;
                return;
            }
            //System.out.println(elementIndex.getCurrentElement());
            elementIndex = elementIndex.getNextElement();
            counter++;
        }
    }
    
    @Override
    public void	addFirst(E e) { //DONE
        add(0, e);
    }
    
    @Override
    public void	addLast(E e) { //DONE
        add(e);
    }
    
    @Override
    public void	clear() { //DONE
        elementList.setCurrentElement(null);
        elementList.setNextElement(null);
        size = 0;
    }
    
    @Override
    public boolean contains(Object o) { //DONE
        Element elementIndex = elementList;
        while(true) {
            System.out.println(elementIndex.getCurrentElement());
            if(o == null && elementIndex.getCurrentElement() == null)
                return true;
            else if (elementIndex.getCurrentElement() != null && elementIndex.getCurrentElement().equals(o))
                return true;
//            if(o == null ? elementIndex.getCurrentElement() == null : elementIndex.getCurrentElement().equals(o)) {
//                return true;
//            }
            if(elementIndex.getNextElement() == null) {
                return false;
            }
            else {
                elementIndex = elementIndex.getNextElement();
            }
        }
    }
    
    @Override
    public E get(int index) { //DONE
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        int counter = 0;
        Element elementIndex = elementList;
        while(true) {
            if(counter == index) {
                //System.out.println(elementIndex.getCurrentElement());
                return elementIndex.getCurrentElement();
            }
            elementIndex = elementIndex.getNextElement();
            counter++;
        }
    }
    
    @Override
    public E getFirst() { //DONE
        if(size == 0){
            throw new NoSuchElementException();
        }
        return get(0);
    }
    
    @Override
    public E getLast() { //DONE
        if(size == 0){
            throw new NoSuchElementException();
        }
        return get(size - 1);
    }
    
    @Override
    public int indexOf(Object o) { //DONE
        Element elementIndex = elementList;
        int counter = 0;
        while(true) {
            if(o == null && elementIndex.getCurrentElement() == null)
                return counter;
            else if (elementIndex.getCurrentElement() != null && elementIndex.getCurrentElement().equals(o))
                return counter;
//            if(o == null ? elementIndex.getCurrentElement() == null : elementIndex.getCurrentElement().equals(o)) {
//                return counter;
//            }
            if(elementIndex.getNextElement() == null) {
                return -1;
            }
            elementIndex = elementIndex.getNextElement();
            counter++;
        }
    }
    
    @Override
    public int lastIndexOf(Object o) { //DONE
        Element elementIndex = elementList;
        int counter = 0;
        int lastValue = -1;
        while(true) {
            if(o == null && elementIndex.getCurrentElement() == null)
                lastValue = counter;
            else if (elementIndex.getCurrentElement() != null && elementIndex.getCurrentElement().equals(o))
                lastValue = counter;
//            if(o == null ? elementIndex.getCurrentElement() == null : elementIndex.getCurrentElement().equals(o)) {
//                lastValue = counter;
//            }
            if(elementIndex.getNextElement() == null) {
                return lastValue;
            }
            elementIndex = elementIndex.getNextElement();
            counter++;
        }
    }
    
    //System.out.println(elementIndex.getCurrentElement() + " counter");
    
    @Override
    public E remove() { //DONE
        if(size == 0)
            throw new NoSuchElementException();
        return remove(0);
    }
    
    @Override
    public E remove(int index) { //FIX THIS
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        int counter = 0;
        Element elementIndex = elementList;
        while(true) {
            if(counter == index) {
                E eElement = elementIndex.getCurrentElement();
                if(elementIndex.getNextElement() != null){
                    //System.out.println("Debug: " + elementIndex.getNextElement().getCurrentElement());
                    //System.out.println("Debug: " + elementIndex.getNextElement().getNextElement().getCurrentElement());
                    Element newElement = new Element(elementIndex.getNextElement().getCurrentElement());
                    newElement.setNextElement(elementIndex.getNextElement().getNextElement());
                    elementIndex.setCurrentElement(newElement.getCurrentElement());
                    elementIndex.setNextElement(newElement.getNextElement());
                }
                //else{
                //    elementIndex.setCurrentElement(null);
                //    elementIndex.setNextElement(null);
                //}
                size--;
                return eElement;
            }
            elementIndex = elementIndex.getNextElement();
            counter++;
            if(index == size - 1 && counter == index - 1) {
                //System.out.println("Hit");
                E temp = elementIndex.getNextElement().getCurrentElement();
                elementIndex.setNextElement(null);
                size--;
                return temp;
            }
        }
    }
    
    @Override
    public boolean remove(Object o) { //DONE
        boolean hasValue = contains(o);
        if(hasValue){
            remove(indexOf(o));
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public E removeLast() { //DONE
        if(size == 0)
            throw new NoSuchElementException();
        return remove(size - 1);
    }
    
    @Override
    public E set(int index, E element) { //DONE
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        int counter = 0;
        Element elementIndex = elementList;
        while(true) {
            if(counter == index) {
                E cElement = elementIndex.getCurrentElement();
                elementIndex.setCurrentElement(element);
                return cElement;
            }
            elementIndex = elementIndex.getNextElement();
            counter++;
        }
    }
    
    @Override
    public int size() { //DONE
        return size;
    }
    
    @Override
    public String toString() { //DONE
        String printOut = "LinkedList(" + size + ")" + " [";
        for(int i = 0; i < size; i++) {
            //System.out.println(i);
            printOut += get(i) + ", ";
        }
        return printOut + "]";
    }
    
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList();
        list.add("E30");
        list.add("E40");
        list.add("E50");
        list.add("E20");
        list.add("E30");
        list.add("E40");
        list.add("E10");
        list.add("E20");
        list.add("E30");
        list.add("E40");
        list.add("E50");
        System.out.println(list.removeLast());
//        list.remove("E70");
//        list.remove(null);
//        list.add(3, null);
//        list.remove(null);
        System.out.println(list);
//        list.add("Test 1");
//        list.add("Test 2");
//        list.add("Test 1");
//        list.add("Test 3");
//        list.add("Test 4");
//        list.add("Test 5");
//        list.add("Test 1");
//        System.out.println(list);
//        list.remove("Test 1");
//        System.out.println(list);
        //System.out.println(list.get(6));
        /*
        list.add("Element 1"); //5
        list.add(0,"Element 2"); //2
        list.add(1,"Element 3"); //4
        list.add(0,"Element 0"); //1
        list.add(2,"Element 0.5"); //3
        list.add("Element 4"); //6
        list.add("Element 5"); //7
        list.add("Element 4"); //8
        list.addFirst("YOLO"); //0
        list.addLast("YOLO 2"); //9
        list.add(10, "YOLO 3"); //10
        list.addLast(null);
        System.out.println(list.contains("Element 0.5"));
        System.out.println(list.contains("Element 6"));
        System.out.println(list.contains("Element 3"));
        System.out.println(list.contains("Element 0"));
        System.out.println(list.contains("Element 5"));
        System.out.println(list.contains("Element 1"));
        System.out.println(list.contains("Element 2"));
        System.out.println(list.contains("Element 3"));
        System.out.println(list.contains("YOLO 3"));
        System.out.println(list.contains(null));
        System.out.println(list.lastIndexOf(null));
        System.out.println(list);
        System.out.println(list.remove("Element 1"));
        list.add("Element 1");
        list.clear();
        System.out.println(list);
        list.remove();
        */
        //list.removeLast();
        //list.add(0,"Element 0");
        //list.add(1,"Element 0.5");
        //list.add("Element 4");
        //list.add("Element 5");
        //System.out.println(list.removeLast());
        //System.out.println(list.removeLast());
        //System.out.println(list.removeLast());
        //System.out.println(list.removeLast());
        //list.add("Element This");
        //list.addLast("Element This 2");
        //System.out.println(list.removeLast());
        //System.out.println(list);
        //System.out.println(list.indexOf("Element 6"));
        //System.out.println(list.contains("Element 5"));
        //list.add(1, "Element 1.5");
        //list.set(5, "New Element 5");
        //list.removeLast();
        //System.out.println("Replaced: " + list.set(0, "New Element 1"));
        //System.out.println(list.get(0));
        //System.out.println(list.get(1));
        //System.out.println(list.get(2));
    }
}
