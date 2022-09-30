package com.interview.list;

/**
 * 
 * LIFO
 * 
 * @author imdmi
 *
 * @param <T>
 */
public class Stack<T> {
	Node<T> head = null;
	
	public void push(T data) {
		Node<T> newhead = new Node<>();
		newhead.data = data;
		newhead.next = head;
		
		head = newhead;
	}
	
	public T pop() {
		if (head == null) {
			return null;
		}
		
		T rez = head.data;
		head = head.next;
		return rez;
	}
}
