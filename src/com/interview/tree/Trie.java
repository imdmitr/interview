package com.interview.tree;

import java.util.ArrayList;
import java.util.List;

public class Trie<T> {
	
	private TrieNode<T> root = new TrieNode<>();
	
	public TrieNode<T> insert(String word) {
		TrieNode<T> node = root;
		
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int index = c - 'a';
			if (node.children[index] == null) {
				node.children[index] = new TrieNode<>();
				node.children[index].symbol = c;
			}
			node = node.children[index];
		}
		return node;
	}
	
	public List<String> findPrefix(String prefix) {
		List<String> rez = new ArrayList<String>();
		
		TrieNode<T> node = root;
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < prefix.length(); i++) {
			char c = prefix.charAt(i);
			int index = c - 'a';
			TrieNode<T> tmp = node.children[index];
			if (tmp != null) {
				sb.append(tmp.symbol);
				node = tmp;
			} else {
				return rez;
			}
		}
		
		if (node != null  && node.data != null) {
			rez.add(sb.toString());
		}
		
		addAllWords(node, sb.toString(), rez);

		return rez;
		
	}

	private void addAllWords(TrieNode<T> node, String start, List<String> rez) {
		if (node == null) {
			return;
		}
		
		for (TrieNode<T> child : node.children) {
			if (child != null) {
				String tmp = start + child.symbol;
				if (child.data != null) {
					rez.add(tmp);
				} else {
					addAllWords(child, tmp, rez);
				}
			}
		}
		
	}
	
	

}
