package com.interview.list;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class AllListTest {

	@Test
	void testStack() {
		Stack<Integer> stack = new Stack<>();
		
		for (int i = 0; i < 10; i++) {
			stack.push(i);
		}
		
		for (int i = 9; i >= 0; i--) {
			Integer pop = stack.pop();
			assertEquals(i, (int) pop);
		}
			
	}

	@Test
	void testQueue() {
		Queue<Integer> queue = new Queue<>();
		
		for (int i = 0; i < 10; i++) {
			queue.add(i);
		}
		
		for (int i = 0; i < 10; i++) {
			assertEquals(i, (int) queue.remove());
		}
	
	}
	
	@Test
	public void testLinkedList() {
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		
		list.iterate();
		
		Node<Integer> reverse = list.reverse();
		
		System.out.println("reverse linked list");
		Node<Integer> t = reverse;
		int v = 9;
		while (t != null) {
			System.out.println(t.data);
			assertEquals(v--, (int) t.data);
			t = t.next;
		}
	}

	/**
	 * 
	 */
	@Test
	public void testLinkedListRemove() {
		LinkedList<Integer> list = new LinkedList<>();
		Node<Integer> n5 = null;
		for (int i = 0; i < 10; i++) {
			Node<Integer> add = list.add(i);
			if (i == 5) {
				n5 = add;
			}
		}
		
		list.removeNthNode(n5);
		list.removeNthFromEnd(list.head, 2);
		
		list.iterate();
		
	}

}
