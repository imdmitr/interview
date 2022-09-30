package com.interview.tree;

@FunctionalInterface
public interface NodeConsumer<T> {
	
	public void accept(T node);
}
