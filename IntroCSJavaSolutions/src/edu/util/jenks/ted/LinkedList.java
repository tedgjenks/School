package edu.util.jenks.ted;

import java.util.NoSuchElementException;

import edu.jenks.dist.util.AbstractLinkedList;

public class LinkedList<E> implements AbstractLinkedList<E> {
	private Node head, tail;
	private int size;

	@Override
	public boolean add(E e) {
		add(size, e);
		return true;
	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		checkIndexOutOfBounds(index, true);
		Node newNode = new Node(element);
		if(head == null)
			head = tail = newNode;
		else if(index == 0) {
			newNode.nextNode = head;
			head.previousNode = newNode;
			head = newNode;
		} else if(index == size) {
			newNode.previousNode = tail;
			tail.nextNode = newNode;
			tail = newNode;
		} else {
			Node curNode = tail;
			for(int nodeCount = size - 1; nodeCount > index; nodeCount--, curNode = curNode.previousNode);
			Node next = curNode;
			Node prev = curNode.previousNode;
			newNode.previousNode = prev;
			newNode.nextNode = next;
			prev.nextNode = newNode;
			next.previousNode = newNode;
		}
		size++;
	}

	@Override
	public void addFirst(E e) {
		add(0, e);
	}

	@Override
	public void addLast(E e) {
		add(e);
	}

	@Override
	public void clear() {
		head = tail = null;
		size = 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		checkIndexOutOfBounds(index, false);
		return getNode(index).nodeValue;
	}

	@Override
	public E getFirst() throws NoSuchElementException {
		if(head == null)
			throw new NoSuchElementException();
		return head.nodeValue;
	}

	@Override
	public E getLast() throws NoSuchElementException {
		if(tail == null)
			throw new NoSuchElementException();
		return tail.nodeValue;
	}

	@Override
	public int indexOf(Object o) {
		int foundIndex = -1;
		Node curNode = head;
		for(int curIndex = 0; curIndex < size && foundIndex < 0; curIndex++, curNode = curNode.nextNode) {
			E nodeValue = curNode.nodeValue;
			if(nodeValue == null ? o == null : nodeValue.equals(o))
				foundIndex = curIndex;
		}
		return foundIndex;
	}

	@Override
	public int lastIndexOf(Object o) {
		int foundIndex = -1;
		Node curNode = tail;
		for(int curIndex = size - 1; curIndex >= 0 && foundIndex < 0; curIndex--, curNode = curNode.previousNode) {
			E nodeValue = curNode.nodeValue;
			if(nodeValue == null ? o == null : nodeValue.equals(o))
				foundIndex = curIndex;
		}
		return foundIndex;
	}

	@Override
	public E remove() throws NoSuchElementException {
		if(head == null)
			throw new NoSuchElementException();
		return remove(0);
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		checkIndexOutOfBounds(index, false);
		E removedValue = null;
		if(index == 0) {
			removedValue = head.nodeValue;
			head = head.nextNode;
			if(head != null)
				head.previousNode = null;
		} else if(index == size - 1) {
			removedValue = tail.nodeValue;
			tail = tail.previousNode;
			if(tail != null)
				tail.nextNode = null;
		} else {
			Node removeMe = getNode(index);
			Node prev = removeMe.previousNode;
			Node next = removeMe.nextNode;
			prev.nextNode = next;
			next.previousNode = prev;
			removedValue = removeMe.nodeValue;
		}
		size--;
		fixHeadTailAfterRemove();
		return removedValue;
	}

	@Override
	public boolean remove(Object o) {
		boolean removed = false;
		int index = indexOf(o);
		if(index >= 0) {
			remove(index);
			removed = true;
		}
		return removed;
	}

	@Override
	public E removeLast() throws NoSuchElementException {
		if(tail == null)
			throw new NoSuchElementException();
		return remove(size - 1);
	}

	@Override
	public E set(int index, E element) throws IndexOutOfBoundsException {
		checkIndexOutOfBounds(index, false);
		Node node = getNode(index);
		E prevElement = node.nodeValue;
		node.nodeValue = element;
		return prevElement;
	}

	@Override
	public int size() {
		return size;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(20 + 10 * size);
		sb.append("LinkedList").append("(").append(size).append(") ").append("[");
		Node currentNode = head;
		for(int nodeCount = 0; nodeCount < size; nodeCount++, currentNode = currentNode.nextNode)
			sb.append(currentNode.nodeValue == null ? null : currentNode.nodeValue.toString()).append(", ");
		sb.append("]");
		return sb.toString();
	}
	
	private Node getNode(int index) throws IndexOutOfBoundsException {
		Node curNode = tail;
		for(int curNodeIndex = size - 1; curNodeIndex > index; curNodeIndex--, curNode = curNode.previousNode);
		return curNode;
	}

	private void checkIndexOutOfBounds(int index, boolean indexCanEqualSize) throws IndexOutOfBoundsException {
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index " + index + ", size " + size);
		if(!indexCanEqualSize && index == size)
			throw new IndexOutOfBoundsException("Index " + index + ", size " + size);
	}
	
	private void fixHeadTailAfterRemove() {
		if(size == 0)
			head = tail = null;
		else if(size == 1) {
			if(head == null)
				head = tail;
			if(tail == null)
				tail = head;
		}
	}

	private class Node {
		public Node previousNode, nextNode;
		public E nodeValue;
		public Node(E nodeValue) {
			this.nodeValue = nodeValue;
		}
	}

}
