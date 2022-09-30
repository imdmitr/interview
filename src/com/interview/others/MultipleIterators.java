package com.interview.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class MultipleIterators {

	static class CombineIterator<T> implements Iterator<T> { 

		List<T> items = null;
		private Iterator<T> combinedIterator;
		
		public CombineIterator(Iterator<T> first, Iterator<T> second) {
			LinkedList<Iterator<T>> queue = new LinkedList<>();
			if (first != null ) {
				queue.offer(first);
			}
			if (second != null ) {
				queue.offer(second);
			}
			
			List<T> items = new ArrayList<>();
			
			while (!queue.isEmpty()) {
				Iterator<T> currentIter = queue.pollFirst();
				
				if (currentIter.hasNext()) {
					items.add(currentIter.next());
					queue.offer(currentIter);
				}
			}
			
			combinedIterator = items.iterator();
			
		}

		@Override
		public boolean hasNext() {
			return combinedIterator.hasNext();
		}

		@Override
		public T next() {
			return combinedIterator.next();
		}
	
		
	}
	
	static class CombineIterator2<T> implements Iterator<T> { 

		Iterator<T> current;
		Iterator<T> other;
		
		
		public CombineIterator2(Iterator<T> first, Iterator<T> second) {
			current = first;
			other = second;
			
			if (current == null || !current.hasNext()) {
				current = other;
				other = null;
			}
			
		}

		@Override
		public boolean hasNext() {
			return current != null && current.hasNext();
		}

		@Override
		public T next() {
			T rez = current.next();
			if (other != null && other.hasNext()) {
				Iterator<T> tmp = current;
				current = other;
				other = tmp;				
			}
			return rez;
		}
	
		
	}	
	
	public static void main(String[] args) {
		List<String> asList = Arrays.asList(new String[] {"a", "b", "c", "d"});
		List<String> second = Arrays.asList(new String[] {"1", "2", "3", "4", "5", "6"});
		
		
//		Iterator<String> iter = new CombineIterator<String>(asList.iterator(), second.iterator());
//		
//		Iterator<String> iter = new CombineIterator2<String>(asList.iterator(), second.iterator());
		Iterator<String> iter = new CombineIterator2<String>(second.iterator(), asList.iterator());
		
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		
	}
}
