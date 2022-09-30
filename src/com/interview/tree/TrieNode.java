package com.interview.tree;

import java.util.Arrays;

public class TrieNode<T> {
	public static final int ALPHABET_SIZE = 26;
	char symbol;
	public T data;
	TrieNode[] children = new TrieNode[ALPHABET_SIZE];
	@Override
	public String toString() {
		return "TrieNode [symbol=" + symbol + ", children=" + Arrays.toString(children) + "]";
	}	
	
	
}
