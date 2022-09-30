package com.interview.list;

/**
 * FIFO 
 * 
 * @author imdmi
 *
 * @param <T>
 */
public class Queue<T> {

	DoubleNode<T> head;
	DoubleNode<T> tail;
	
	/**
	 * Add to head
	 * 
	 * @param data
	 */
	public void add(T data) {
		DoubleNode<T> newhead = new DoubleNode<>();
		newhead.data = data;
		newhead.next = head;
		
		if (head != null) {
			head.previous = newhead;
		}
		head = newhead;
		if (tail == null) {
			tail = newhead;
		}
	}
	
	/**
	 * Remove from tail
	 * 
	 * @return
	 */
	public T remove() {
		if (tail == null) {
			return null;
		}
		
		T rez = tail.data;
		
		tail = tail.previous;
		
		return rez;
	}
	
}
 