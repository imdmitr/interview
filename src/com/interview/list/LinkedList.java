package com.interview.list;

public class LinkedList<T> {
	Node<T> head;
	Node<T> tail;
	
	/**
	 * Add to tail
	 * 
	 * @param value
	 * @return
	 */
	public Node<T> add(T value) {
		Node<T> n = new Node<>();
		n.data = value;
		
		if (tail != null) {
			tail.next = n;
			tail = n;
		}
		
		if (head == null) {
			head = n;
			tail = n;
		}
		return n;
	}
	
	public void iterate() {
		Node<T> t = head;
		while (t != null) {
			System.out.println(t.data);
			t = t.next;
		}
	}

	
	public Node<T> reverse() {
		Node<T> current = head;
		Node<T> prev = null;
		Node<T> next = null;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		
		return prev;
	}
	
	/**
	 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/solution/
	 * 
	 * @param head
	 * @param n
	 * @return
	 */
	public Node<T> removeNthFromEnd(Node<T> head, int n) {
		Node<T> dummy = new Node<>();
	    dummy.next = head;
	    Node<T> first = dummy;
	    Node<T> second = dummy;
	    // Advances first pointer so that the gap between first and second is n nodes apart
	    for (int i = 1; i <= n + 1; i++) {
	        first = first.next;
	    }
	    // Move first to the end, maintaining the gap
	    while (first != null) {
	        first = first.next;
	        second = second.next;
	    }
	    second.next = second.next.next;
	    return dummy.next;
	}
	
	/**
	 * https://leetcode.com/explore/featured/card/top-interview-questions-easy/93/linked-list/553/
	 * @param nodeToRemove
	 */
	public void removeNthNode(Node<T> nodeToRemove) {
		//we dont have head
		nodeToRemove.data = nodeToRemove.next.data;
		nodeToRemove.next = nodeToRemove.next.next;		
	}

	
}
